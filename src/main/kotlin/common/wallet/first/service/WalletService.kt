package common.wallet.first.service

import common.wallet.first.FirstApplication
import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.DeleteStatus
import common.wallet.first.enum.WalletSubscriberType
import common.wallet.first.mapper.RecordMapper
import common.wallet.first.mapper.UserMapper
import common.wallet.first.mapper.WalletMapper
import common.wallet.first.repository.RecordRepository
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import java.util.*
import kotlin.streams.toList

@Service
class WalletService @Autowired constructor(
    private val walletRepository: WalletRepository,
    private val userRepository: UserRepository,
    private val walletMapper: WalletMapper,
    private val recordMapper: RecordMapper,
    private val recordService: RecordService
) {


    fun getWalletsByOwner(owner : User) : List<Wallet> {
        return walletRepository.findAllByOwner(owner)
    }

    fun getWalletsByAdmin(admin : User) : List<Wallet> {
        return walletRepository.findAllByAdminsIn(admin)
    }

    fun getWalletsByUser(user: User): List<Wallet> {
        return walletRepository.findAllByUsersIn(user)
    }

    fun getAllWalletsByUser(id: String): List<Wallet> {
        var user = userRepository.findById(id)
        var wallets = mutableListOf<Wallet>()
        wallets.addAll(getWalletsByOwner(user.get()))
        wallets.addAll(getWalletsByAdmin(user.get()))
        wallets.addAll(getWalletsByUser(user.get()))
        return wallets
    }

    fun createNewWallet(walletCreateDto: WalletCreateDto): WalletDto {
        var wallet = walletMapper.toEntity(walletCreateDto)
        walletRepository.save(wallet)
        return walletMapper.toDto(wallet)
    }

    fun getAllRecordsInWallet(id: String): List<RecordDto> {
        var wallet = walletRepository.findById(id).get()
        return recordService.getRecordsByWallet(wallet)
            .stream().map { recordMapper.toDto(it) }.toList()
    }

    fun getTotalSumOfWallet(id: String): Double {
        val wallet = walletRepository.findById(id).get()
        val sum = recordService.getRecordsByWallet(wallet)
            .stream().map { it.sum }.reduce { sum, element -> sum + element }
        return sum.orElse(0.0)
    }

    fun getTotalSumOfWalletsForUser(user: User): Double {
        var sum = 0.0
        sum += walletRepository.findAllByOwner(user).stream()
            .mapToDouble { getTotalSumOfWallet(it.id.toString()) }.sum()
        sum += walletRepository.findAllByUsersIn(user).stream()
            .mapToDouble { getTotalSumOfWallet(it.id.toString()) }.sum()
        sum += walletRepository.findAllByAdminsIn(user).stream()
            .mapToDouble { getTotalSumOfWallet(it.id.toString()) }.sum()
        return sum
    }

    fun deleteWallet(userId: String, walletId: String): String {
        var user = userRepository.findById(userId).get()
        val wallet = walletRepository.findById(walletId).get()
        if (wallet.owner.id.equals(user.id)) {
            walletRepository.deleteById(walletId)
            return DeleteStatus.OK.name
        }
        return DeleteStatus.NO_PERMISSION.name
    }

    fun getWalletsSubscriptions(user: User): List<WalletSubscriptionDto> {
        var walletsList = getAllWalletsByUser(user.id.toString())
        return walletsList.stream()
            .map { walletMapper.toSubscriptionDto(it, getUserType(user, it), getTotalSumOfWallet(it.id.toString())) }
            .toList()
    }

    fun getUserType(user: User, wallet: Wallet): WalletSubscriberType {
        when {
            wallet.owner.id.equals(user.id) -> {
                return WalletSubscriberType.OWNER
            }
            wallet.admins.contains(user) -> {
                return WalletSubscriberType.ADMIN
            }
            wallet.users.contains(user) -> {
                return  WalletSubscriberType.USER
            }
        }
        return WalletSubscriberType.UNRECOGNIZED
    }
}
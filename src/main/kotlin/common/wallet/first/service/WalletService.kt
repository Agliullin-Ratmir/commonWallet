package common.wallet.first.service

import common.wallet.first.dto.EditWalletDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.AnswerStatus
import common.wallet.first.enum.WalletSubscriberType
import common.wallet.first.mapper.RecordMapper
import common.wallet.first.mapper.WalletMapper
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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
        return walletRepository.findAllByOwnerUuid(owner.uuid)
    }

    fun getWalletsByAdmin(admin : User) : List<Wallet> {
        return walletRepository.findAllByAdminsUuidIn(admin.uuid)
    }

    fun getWalletsByUser(user: User): List<Wallet> {
        return walletRepository.findAllByUsersUuidIn(user.uuid)
    }

    fun getAllWalletsByUser(uuid: String): List<Wallet> {
        var user = userRepository.findByUuid(uuid)
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

    fun getAllRecordsInWallet(walletUuid: String, userUuid: String): List<RecordDto> {
        var wallet = walletRepository.findByUuid(walletUuid).orElseThrow()
        if (!WalletSubscriberType.UNRECOGNIZED.equals(getUserType(userUuid, wallet))) {
            return recordService.getRecordsByWallet(wallet)
                .stream().map { recordMapper.toDto(it) }.toList()
        }
        return emptyList()
    }

    fun getTotalSumOfWallet(walletUuid: String, userUuid: String): Double {
        val wallet = walletRepository.findByUuid(walletUuid).get()
        if (!WalletSubscriberType.UNRECOGNIZED.equals(getUserType(userUuid, wallet))) {
            val sum = recordService.getRecordsByWallet(wallet)
                .stream().map { it.sum }.reduce { sum, element -> sum + element }
            return sum.orElse(0.0)
        }
        return 0.0
    }

    fun getTotalSumOfWalletsForUser(user: User): Double {
        var sum = 0.0
        sum += walletRepository.findAllByOwnerUuid(user.uuid).stream()
            .mapToDouble { getTotalSumOfWallet(it.uuid, user.uuid) }.sum()
        sum += walletRepository.findAllByUsersUuidIn(user.uuid).stream()
            .mapToDouble { getTotalSumOfWallet(it.uuid, user.uuid) }.sum()
        sum += walletRepository.findAllByAdminsUuidIn(user.uuid).stream()
            .mapToDouble { getTotalSumOfWallet(it.uuid, user.uuid) }.sum()
        return sum
    }

    fun deleteWallet(userUuid: String, walletUuid: String): String {
        val wallet = walletRepository.findByUuid(walletUuid).get()
        if (wallet.ownerUuid.equals(userUuid)) {
            walletRepository.deleteByUuid(walletUuid)
            return AnswerStatus.OK.name
        }
        return AnswerStatus.NO_PERMISSION.name
    }

    fun getWalletsSubscriptions(userUuid: String): List<WalletSubscriptionDto> {
        var walletsList = getAllWalletsByUser(userUuid)
        return walletsList.stream()
            .map { walletMapper.toSubscriptionDto(it, getUserType(userUuid, it), getTotalSumOfWallet(it.uuid, userUuid)) }
            .toList()
    }

    fun getUserType(userUuid: String, wallet: Wallet): WalletSubscriberType {
        when {
            wallet.ownerUuid.equals(userUuid) -> {
                return WalletSubscriberType.OWNER
            }
            wallet.adminsUuid.contains(userUuid) -> {
                return WalletSubscriberType.ADMIN
            }
            wallet.usersUuid.contains(userUuid) -> {
                return  WalletSubscriberType.USER
            }
        }
        return WalletSubscriberType.UNRECOGNIZED
    }

    fun unsubscribeOfWallet(userUuid: String, walletUuid: String): String {
        val wallet = walletRepository.findByUuid(walletUuid).orElseThrow()
        if (wallet.adminsUuid.contains(userUuid)) {
            wallet.adminsUuid.remove(userUuid)
        } else if (wallet.usersUuid.contains(userUuid)) {
            wallet.usersUuid.remove(userUuid)
        } else {
            return AnswerStatus.NO_PERMISSION.name
        }
        walletRepository.save(wallet)
        return AnswerStatus.OK.name
    }

    fun editWalletInfo(userUuid: String, dto: EditWalletDto): String {
        var user = userRepository.findByUuid(userUuid).orElseThrow()
        val wallet = walletRepository.findByUuid(dto.uuid).orElseThrow()
        if (wallet.usersUuid.contains(userUuid) ||
            WalletSubscriberType.UNRECOGNIZED.equals(getUserType(userUuid, wallet))
        ) {
            return AnswerStatus.NO_PERMISSION.name
        }
        walletMapper.toEntity(dto, wallet)
        walletRepository.save(wallet)
        return AnswerStatus.OK.name
    }
}
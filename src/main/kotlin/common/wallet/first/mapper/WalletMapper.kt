package common.wallet.first.mapper

import common.wallet.first.dto.UserCreateDto
import common.wallet.first.dto.UserDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.WalletSubscriberType
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WalletMapper @Autowired constructor(
    private val walletRepository: WalletRepository,
    private val userRepository: UserRepository) {

    fun toDto(wallet: Wallet) : WalletDto {
        return WalletDto(id = wallet.id,
        uuid = wallet.uuid,
        title = wallet.title,
        description = wallet.description,
        owner = wallet.owner,
        admins = wallet.admins,
        users = wallet.users,
        records = wallet.records)
    }

    fun toSubscriptionDto(wallet: Wallet, userType: WalletSubscriberType,
                            totalSum: Double): WalletSubscriptionDto {
        return WalletSubscriptionDto(id = wallet.id,
            uuid = wallet.uuid,
            title = wallet.title,
            description = wallet.description,
            subsType = userType,
            totalSum = totalSum)
    }

    fun toEntity(wallet: WalletCreateDto): Wallet {
        return Wallet(
            title = wallet.title,
            description = wallet.description,
            owner = userRepository.findById(wallet.ownerId.toString()).get()
        )
    }
}
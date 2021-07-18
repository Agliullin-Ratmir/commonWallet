package common.wallet.first.mapper

import common.wallet.first.dto.EditWalletDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.WalletSubscriberType
import common.wallet.first.repository.RecordRepository
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WalletMapper @Autowired constructor(
    private val recordRepository: RecordRepository,
    private val userRepository: UserRepository) {

    fun toDto(wallet: Wallet) : WalletDto {
        return WalletDto(id = wallet.id,
        uuid = wallet.uuid,
        title = wallet.title,
        description = wallet.description,
        owner = userRepository.findByUuid(wallet.ownerUuid).orElseThrow(),
        admins = userRepository.findByUuidIsIn(wallet.adminsUuid),
        users = userRepository.findByUuidIsIn(wallet.usersUuid),
        records = recordRepository.findByUuidIsIn(wallet.recordsUuid))
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
            ownerUuid = wallet.ownerUuid
        )
    }

    fun toEntity(dto: EditWalletDto, wallet: Wallet) {
        wallet.description = dto.description?:wallet.description
        wallet.title = dto.title?:wallet.title
    }
}
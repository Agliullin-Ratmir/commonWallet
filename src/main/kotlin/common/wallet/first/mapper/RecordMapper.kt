package common.wallet.first.mapper

import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.entity.Record
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecordMapper @Autowired constructor(
    private val walletRepository: WalletRepository,
    private val userRepository: UserRepository
) {

    fun toDto(record: Record): RecordDto {
        return RecordDto(
            id = record.id,
            uuid = record.uuid,
            user = record.user,
            title = record.title,
            sum = record.sum,
            details = record.details,
            wallet = record.wallet
        )
    }

    fun toEntity(record: RecordCreateDto): Record {
        val user = userRepository.findByUuid(record.userUuid.toString())
        val wallet = walletRepository.findByUuid(record.walletUuid.toString())
        return Record(
            user = user.get(),
            wallet = wallet.get(),
            title = record.title,
            details = record.details,
            sum = record.sum
        )
    }
}
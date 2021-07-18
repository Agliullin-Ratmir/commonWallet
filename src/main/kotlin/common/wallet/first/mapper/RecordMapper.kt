package common.wallet.first.mapper

import common.wallet.first.dto.EditRecordDto
import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.entity.Record
import common.wallet.first.entity.Wallet
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
            user = userRepository.findByUuid(record.userUuid).orElseThrow(),
            title = record.title,
            sum = record.sum,
            details = record.details,
            wallet = walletRepository.findByUuid(record.walletUuid).orElseThrow()
        )
    }

    fun toEntity(dto: EditRecordDto, record: Record) {
        record.details = dto.details?:record.details
        record.title = dto.title?:record.title
        record.sum = dto.sum?:record.sum
    }

    fun toEntity(record: RecordCreateDto): Record {
        val user = userRepository.findByUuid(record.userUuid.toString())
        val wallet = walletRepository.findByUuid(record.walletUuid.toString())
        return Record(
            userUuid = record.userUuid,
            walletUuid = record.walletUuid,
            title = record.title,
            details = record.details,
            sum = record.sum
        )
    }
}
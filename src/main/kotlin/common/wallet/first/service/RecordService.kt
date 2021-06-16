package common.wallet.first.service

import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.entity.Record
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.DeleteStatus
import common.wallet.first.mapper.RecordMapper
import common.wallet.first.repository.RecordRepository
import common.wallet.first.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecordService @Autowired constructor(
    private val recordRepository: RecordRepository,
    private val userRepository: UserRepository,
    private val recordMapper: RecordMapper,) {

    fun getRecordsByWallet(wallet: Wallet) : List<Record> {
        return recordRepository.findAllByWallet(wallet)
    }

    fun getRecordByUuid(uuid: String): RecordDto? {
        var record: Record? = recordRepository.findByUuid(uuid)
            .orElse(null)
        return record?.let { recordMapper.toDto(it) }
    }

    fun createNewRecord(recordCreateDto: RecordCreateDto): RecordDto {
        var record = recordMapper.toEntity(recordCreateDto)
        recordRepository.save(record)
        return recordMapper.toDto(record)
    }

    fun deleteRecord(userUuid: String, recordUuid: String): String {
        var user = userRepository.findByUuid(userUuid)
        var record = recordRepository.findByUuid(recordUuid).get()
        if (record.wallet.admins.contains(user)) {
            recordRepository.deleteByUuid(recordUuid)
            return DeleteStatus.OK.name
        }
        return DeleteStatus.NO_PERMISSION.name
    }
}
package common.wallet.first.service

import common.wallet.first.FirstApplication
import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.entity.Record
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.DeleteStatus
import common.wallet.first.mapper.RecordMapper
import common.wallet.first.repository.RecordRepository
import common.wallet.first.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service

@Service
class RecordService @Autowired constructor(
    private val recordRepository: RecordRepository,
    private val userRepository: UserRepository,
    private val recordMapper: RecordMapper,) {

    fun getRecordsByWallet(wallet: Wallet) : List<Record> {
        return recordRepository.findAllByWallet(wallet)
    }

    fun getRecordById(id: String): RecordDto? {
        var record: Record? = recordRepository.findById(id)
            .orElse(null)
        return record?.let { recordMapper.toDto(it) }
    }

    fun createNewRecord(recordCreateDto: RecordCreateDto): RecordDto {
        var record = recordMapper.toEntity(recordCreateDto)
        recordRepository.save(record)
        return recordMapper.toDto(record)
    }

    fun deleteRecord(userId: String, recordId: String): String {
        var user = userRepository.findById(userId)
        var record = recordRepository.findById(recordId).get()
        if (record.wallet.admins.contains(user)) {
            recordRepository.deleteById(recordId)
            return DeleteStatus.OK.name
        }
        return DeleteStatus.NO_PERMISSION.name
    }
}
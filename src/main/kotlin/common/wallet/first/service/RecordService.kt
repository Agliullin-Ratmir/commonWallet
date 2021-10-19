package common.wallet.first.service

import common.wallet.first.dto.EditRecordDto
import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.entity.Record
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.AnswerStatus
import common.wallet.first.enum.WalletSubscriberType
import common.wallet.first.mapper.RecordMapper
import common.wallet.first.repository.RecordRepository
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecordService @Autowired constructor(
    private val recordRepository: RecordRepository,
    private val walletRepository: WalletRepository,
    private val userRepository: UserRepository,
    private val recordMapper: RecordMapper,) {

    fun getRecordsByWallet(wallet: Wallet) : List<Record> {
        return recordRepository.findAllByWalletUuid(wallet.uuid)
    }

    fun getRecordByUuid(recordUuid: String, userUuid: String): RecordDto? {
        var record = recordRepository.findByUuid(recordUuid).orElseThrow()
        var wallet = walletRepository.findByUuid(record.walletUuid).orElseThrow()
        val user = userRepository.findByUuid(userUuid).orElseThrow()
        if (!WalletSubscriberType.UNRECOGNIZED.equals(getUserType(user, wallet))) {
            return record?.let { recordMapper.toDto(it) }
        }
        return null
    }

    fun createNewRecord(recordCreateDto: RecordCreateDto): RecordDto? {
        var user = userRepository.findByUuid(recordCreateDto.userUuid).orElseThrow()
        val wallet = walletRepository.findByUuid(recordCreateDto.walletUuid).orElseThrow()
        if (WalletSubscriberType.UNRECOGNIZED.equals(getUserType(user, wallet))) {
            return null
        }
        var record = recordMapper.toEntity(recordCreateDto)
        recordRepository.save(record)
        wallet.recordsUuid.add(record.uuid)
        walletRepository.save(wallet)
        return recordMapper.toDto(record)
    }

    fun deleteRecord(userUuid: String?, recordUuid: String?): String {
        var record = recordRepository.findByUuid(recordUuid!!).orElseThrow()
        var wallet = walletRepository.findByUuid(record.walletUuid).orElseThrow()
        if (wallet.adminsUuid.contains(userUuid) ||
            (wallet.ownerUuid.equals(userUuid))) {
            recordRepository.deleteByUuid(recordUuid)
            wallet.recordsUuid.remove(recordUuid)
            walletRepository.save(wallet)
            return AnswerStatus.OK.name
        }
        return AnswerStatus.NO_PERMISSION.name
    }

    fun editRecord(userUuid: String, dto: EditRecordDto): String {
        var record = recordRepository.findByUuid(dto.uuid).orElseThrow()
        var wallet = walletRepository.findByUuid(record.walletUuid).orElseThrow()
        if (wallet.adminsUuid.contains(userUuid) ||
            (wallet.ownerUuid.equals(userUuid))
            || record.userUuid.equals(userUuid)) {
            recordMapper.toEntity(dto, record)
            recordRepository.save(record)
            return AnswerStatus.OK.name
        }
        return AnswerStatus.NO_PERMISSION.name
    }

    fun getUserType(user: User, wallet: Wallet): WalletSubscriberType {
        when {
            wallet.ownerUuid.equals(user.uuid) -> {
                return WalletSubscriberType.OWNER
            }
            wallet.adminsUuid.contains(user.uuid) -> {
                return WalletSubscriberType.ADMIN
            }
            wallet.usersUuid.contains(user.uuid) -> {
                return  WalletSubscriberType.USER
            }
        }
        return WalletSubscriberType.UNRECOGNIZED
    }
}
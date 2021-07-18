package common.wallet.first.service

import common.wallet.first.dto.InviteCodeDto
import common.wallet.first.entity.InviteCode
import common.wallet.first.enum.AnswerStatus
import common.wallet.first.enum.WalletSubscriberType
import common.wallet.first.mapper.InviteCodeMapper
import common.wallet.first.repository.InviteCodeRepository
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.streams.toList

@Service
class InviteCodeService @Autowired constructor(
    private val walletRepository: WalletRepository,
    private val walletService: WalletService,
    private val inviteCodeRepository: InviteCodeRepository,
    private val inviteCodeMapper: InviteCodeMapper) {

    val MILL_SECONDS_IN_DAY = 3600 * 24 * 1000

    fun addNewInviteCode(walletUuid : String, userStatus: String,
                         userUuid : String): InviteCodeDto {
        val wallet = walletRepository.findByUuid(walletUuid).orElseThrow()
        var code = InviteCode()
        code.userStatus = WalletSubscriberType.valueOf(userStatus)
        code.walletUuid = walletUuid
        if ((wallet.usersUuid.contains(userUuid) && WalletSubscriberType.ADMIN.name.equals(userStatus))
            || WalletSubscriberType.UNRECOGNIZED.equals(walletService.getUserType(userUuid, wallet))) {
            return InviteCodeDto("", AnswerStatus.NO_PERMISSION)
        }
        code.expiredDate = Instant.now().toEpochMilli() + MILL_SECONDS_IN_DAY.toLong()
        code.content = getRandomString(15)
        inviteCodeRepository.save(code)
        return inviteCodeMapper.toDto(code)
    }

    fun subscribeToWallet(inviteCode: String, userUuid: String): Boolean {
        val inviteCodeEntity = inviteCodeRepository.findByContent(inviteCode)
            .orElseThrow()
        var wallet = walletRepository.findByUuid(inviteCodeEntity.walletUuid)
            .orElseThrow()
        if (inviteCodeEntity.expiredDate < Instant.now().toEpochMilli()) {
            return false
        }
        if (WalletSubscriberType.ADMIN.equals(inviteCodeEntity.userStatus)) {
            wallet.adminsUuid.add(userUuid)
        } else {
            wallet.usersUuid.add(userUuid)
        }
        walletRepository.save(wallet)
        return true
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}
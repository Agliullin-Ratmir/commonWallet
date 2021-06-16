package common.wallet.first.service

import common.wallet.first.dto.InviteCodeDto
import common.wallet.first.entity.InviteCode
import common.wallet.first.mapper.InviteCodeMapper
import common.wallet.first.repository.InviteCodeRepository
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InviteCodeService @Autowired constructor(
    private val walletRepository: WalletRepository,
    private val inviteCodeRepository: InviteCodeRepository,
    private val inviteCodeMapper: InviteCodeMapper) {

    val MILL_SECONDS_IN_DAY = 3600 * 24 * 1000

    fun addNewInviteCode(walletUuid : String): InviteCodeDto {
        var wallet = walletRepository.findByUuid(walletUuid).orElseThrow()
        var code = InviteCode()
        code.wallet = wallet
        code.expiredDate = System.currentTimeMillis() + MILL_SECONDS_IN_DAY.toLong()
        code.content = getRandomString(15)
        inviteCodeRepository.save(code)
        return inviteCodeMapper.toDto(code)
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}
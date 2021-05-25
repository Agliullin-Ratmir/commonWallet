package common.wallet.first.mapper

import common.wallet.first.dto.InviteCodeDto
import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.UserCreateDto
import common.wallet.first.dto.UserDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.entity.InviteCode
import common.wallet.first.entity.Record
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import common.wallet.first.repository.RecordRepository
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InviteCodeMapper {

    fun toDto(code: InviteCode): InviteCodeDto {
        return InviteCodeDto(
            content = code.content!!
        )
    }
}
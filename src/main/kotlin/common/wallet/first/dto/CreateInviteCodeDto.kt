package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import org.bson.types.ObjectId
import java.util.UUID

data class CreateInviteCodeDto(
    @JsonProperty("walletUuid") var walletUuid: String,
    @JsonProperty("userUuid") var userUuid: String,
    @JsonProperty("userStatus") var userStatus: String
)
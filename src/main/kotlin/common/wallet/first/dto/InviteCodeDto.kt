package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.AnswerStatus
import org.bson.types.ObjectId
import java.util.UUID

data class InviteCodeDto(
    @JsonProperty("content") var content: String,
    @JsonProperty("status") var status: AnswerStatus
)
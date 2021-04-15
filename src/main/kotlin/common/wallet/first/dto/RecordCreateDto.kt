package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import org.bson.types.ObjectId
import java.util.*

data class RecordCreateDto(
    @JsonProperty("userId") var userId: ObjectId,
    @JsonProperty("title") var title: String,
    @JsonProperty("sum") var sum: Double,
    @JsonProperty("details") var details: String,
    @JsonProperty("walletId") var walletId: ObjectId
)
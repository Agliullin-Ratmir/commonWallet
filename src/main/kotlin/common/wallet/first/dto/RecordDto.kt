package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import org.bson.types.ObjectId

data class RecordDto(
    @JsonProperty("id") var id: ObjectId,
    @JsonProperty("uuid") var uuid: String,
    @JsonProperty("userName") var userName: String,
    @JsonProperty("userUuid") var userUuid: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("sum") var sum: Double,
    @JsonProperty("details") var details: String,
    @JsonProperty("wallet") var wallet: Wallet
)
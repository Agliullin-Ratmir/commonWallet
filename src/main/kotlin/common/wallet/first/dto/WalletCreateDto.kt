package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

data class WalletCreateDto(
    @JsonProperty("ownerId") var ownerId: ObjectId,
    @JsonProperty("title") var title: String,
    @JsonProperty("description") var description: String
)

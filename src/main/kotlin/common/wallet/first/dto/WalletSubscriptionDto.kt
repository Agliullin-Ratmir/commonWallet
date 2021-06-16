package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.first.enum.WalletSubscriberType
import org.bson.types.ObjectId
import java.util.*

data class WalletSubscriptionDto (
    @JsonProperty("id") var id: ObjectId,
    @JsonProperty("uuid") var uuid: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("description") var description: String,
    @JsonProperty("totalSum") var totalSum: Double,
    @JsonProperty("subsType") var subsType: WalletSubscriberType
)
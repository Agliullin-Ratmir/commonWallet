package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.first.entity.Record
import common.wallet.first.entity.User
import org.bson.types.ObjectId
import java.util.*

data class WalletDto(
    @JsonProperty("id") var id: ObjectId,
    @JsonProperty("uuid") var uuid: String,
    @JsonProperty("title") var title: String,
    @JsonProperty("description") var description: String,
    @JsonProperty("owner") var owner: User,
    @JsonProperty("admins") var admins: MutableList<User>,
    @JsonProperty("users") var users: MutableList<User>,
    @JsonProperty("records") var records: MutableList<Record>
)
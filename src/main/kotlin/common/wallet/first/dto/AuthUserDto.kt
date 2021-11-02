package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.util.*

data class AuthUserDto(
    @JsonProperty("uuid") var uuid: String,
    @JsonProperty("nickName") var nickName: String,
    @JsonProperty("pinCode") var pinCode: String?
)
package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.util.*

data class UserDto(
    @JsonProperty("id") var id: ObjectId,
    @JsonProperty("uuid") var uuid: UUID,
    @JsonProperty("firstName") var firstName: String,
    @JsonProperty("lastName") var lastName: String,
    @JsonProperty("createdDate") var createdDate: LocalDateTime
)
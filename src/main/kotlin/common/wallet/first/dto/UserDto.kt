package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty
import common.wallet.first.enum.AnswerStatus
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.util.*

data class UserDto(
    @JsonProperty("id") var id: ObjectId?,
    @JsonProperty("uuid") var uuid: String?,
    @JsonProperty("nickName") var nickName: String?,
    @JsonProperty("status") var status: AnswerStatus,
    @JsonProperty("createdDate") var createdDate: LocalDateTime?
)
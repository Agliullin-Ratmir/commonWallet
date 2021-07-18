package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty

class EditRecordDto(
    @JsonProperty("uuid") var uuid: String,
    @JsonProperty("title") var title: String?,
    @JsonProperty("sum") var sum: Double?,
    @JsonProperty("details") var details: String?
)
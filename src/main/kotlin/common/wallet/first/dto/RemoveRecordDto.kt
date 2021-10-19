package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty

class RemoveRecordDto(
    @JsonProperty("userUuid") var userUuid: String,
    @JsonProperty("recordUuid") var recordUuid: String?
)
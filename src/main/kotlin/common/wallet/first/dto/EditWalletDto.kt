package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class EditWalletDto(
    @JsonProperty("uuid") var uuid: String,
    @JsonProperty("title") var title: String?,
    @JsonProperty("description") var description: String?
)
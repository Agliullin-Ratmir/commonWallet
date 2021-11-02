package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserCreateDto(
    @JsonProperty("nickName") var nickName: String,
    @JsonProperty("pinCode") var pinCode: String
)

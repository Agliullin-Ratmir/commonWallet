package common.wallet.first.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserCreateDto(
    @JsonProperty("firstName") var firstName: String,
    @JsonProperty("lastName") var lastName: String
)

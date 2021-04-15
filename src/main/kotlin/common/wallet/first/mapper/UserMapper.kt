package common.wallet.first.mapper

import common.wallet.first.dto.UserCreateDto
import common.wallet.first.dto.UserDto
import common.wallet.first.entity.User
import common.wallet.first.repository.UserRepository
import common.wallet.first.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserMapper @Autowired constructor(
    private val walletRepository: WalletRepository,
    private val userRepository: UserRepository) {

    fun toDto(user: User): UserDto {
        return UserDto(
            id = user.id,
            uuid = user.uuid,
            firstName = user.firstName,
            lastName = user.lastName,
            createdDate = user.createdDate
        )
    }

    fun toEntity(user : UserCreateDto) : User {
        return User(firstName = user.firstName,
        lastName = user.lastName)
    }
}
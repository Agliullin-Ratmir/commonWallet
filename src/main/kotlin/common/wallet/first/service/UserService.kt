package common.wallet.first.service

import common.wallet.first.FirstApplication
import common.wallet.first.dto.UserCreateDto
import common.wallet.first.dto.UserDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.entity.User
import common.wallet.first.mapper.RecordMapper
import common.wallet.first.mapper.UserMapper
import common.wallet.first.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository : UserRepository,
    private val walletService: WalletService,
    private val userMapper : UserMapper) {


    fun saveUser() {
        var user = User();
        user.firstName = "Name"
        user.lastName = "Last Name"
        userRepository.save(user)
    }

    fun saveUser(firstName: String, lastName: String) {
        var user = User();
        user.firstName = firstName
        user.lastName = lastName
        userRepository.save(user)
    }

    fun getUserById(id: String): User {
        return userRepository.findById(id).get()
    }

    fun getUserByFirstName(firstName: String): User {
        return userRepository.findByFirstName(firstName).get()
    }

    fun createNewUser(userCreateDto: UserCreateDto): UserDto {
        var user = userMapper.toEntity(userCreateDto)
        userRepository.save(user)
        return userMapper.toDto(user)
    }

    fun getTotalSumForUser(id: String): Double {
        val user = userRepository.findById(id).get()
        return walletService.getTotalSumOfWalletsForUser(user)
    }

    fun getWalletsSubscriptions(id: String): List<WalletSubscriptionDto> {
        val user = userRepository.findById(id).get()
        return walletService.getWalletsSubscriptions(user)
    }
}
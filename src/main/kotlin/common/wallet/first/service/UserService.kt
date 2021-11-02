package common.wallet.first.service

import common.wallet.first.dto.AuthUserDto
import common.wallet.first.dto.UserCreateDto
import common.wallet.first.dto.UserDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.entity.User
import common.wallet.first.enum.AnswerStatus
import common.wallet.first.mapper.UserMapper
import common.wallet.first.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService @Autowired constructor(
    private val userRepository : UserRepository,
    private val walletService: WalletService,
    private val userMapper : UserMapper) {


    fun saveUser() {
        var user = User();
        user.nickName = "Name"
        user.pinCode = "Last Name"
        userRepository.save(user)
    }

    fun saveUser(firstName: String, lastName: String) {
        var user = User();
        user.nickName = firstName
        user.pinCode = lastName
        userRepository.save(user)
    }

    fun isNickNameAllowed(nickName: String): AnswerStatus {
        if (userRepository.existsUserByNickName(nickName)) {
            return AnswerStatus.NO_PERMISSION
        }
        return AnswerStatus.OK
    }

    fun authenticateUser(authUserDto: AuthUserDto): AnswerStatus {
        val user = userRepository.findByNickNameAndUuid(authUserDto.nickName, authUserDto.uuid)
        if (user.isPresent) {
            return AnswerStatus.OK
        }
        return AnswerStatus.NO_PERMISSION
    }

    fun createNewUser(userCreateDto: UserCreateDto): UserDto {
        if (userRepository.existsUserByNickName(userCreateDto.nickName)) {
            return userMapper.toNoPermissionDto()
        }
        var user = userMapper.toEntity(userCreateDto)
        userRepository.save(user)
        return userMapper.toDto(user)
    }

    fun getTotalSumForUser(uuid : String): Double {
        val user = userRepository.findByUuid(uuid).get()
        return walletService.getTotalSumOfWalletsForUser(user)
    }

    fun getWalletsSubscriptions(uuid : String): List<WalletSubscriptionDto> {
        return walletService.getWalletsSubscriptions(uuid)
    }
}
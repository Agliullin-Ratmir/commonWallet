package common.wallet.first.controller

import common.wallet.first.dto.AuthUserDto
import common.wallet.first.dto.UserCreateDto
import common.wallet.first.dto.UserDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.enum.AnswerStatus
import common.wallet.first.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController @Autowired constructor(
    private val userService : UserService){

    @GetMapping
    fun getUser() : String {
        userService.saveUser();
        return "ok";
    }

    @PostMapping
    fun createNewUser(@RequestBody userCreateDto: UserCreateDto) : UserDto {
        return userService.createNewUser(userCreateDto)
    }

    @PostMapping("auth")
    fun authUser(@RequestBody authUserDto: AuthUserDto) : AnswerStatus {
        return userService.authenticateUser(authUserDto)
    }

//    @GetMapping("find")
//    fun isNickNameAllowed(@RequestParam nickName : String) : AnswerStatus {
//        return userService.isNickNameAllowed(nickName)
//    }

    @GetMapping("totalSum")
    fun getTotalSum(@RequestParam uuid : String) : Double {
        return userService.getTotalSumForUser(uuid)
    }

    @GetMapping("subscriptions")
    fun getWalletsSubscriptions(@RequestParam uuid : String): List<WalletSubscriptionDto> {
        return userService.getWalletsSubscriptions(uuid)
    }
}
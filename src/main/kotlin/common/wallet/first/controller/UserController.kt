package common.wallet.first.controller

import common.wallet.first.dto.UserCreateDto
import common.wallet.first.dto.UserDto
import common.wallet.first.dto.WalletSubscriptionDto
import common.wallet.first.entity.User
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

    @GetMapping("find")
    fun saveUser(@RequestParam firstName : String) : String {
        return userService.getUserByFirstName(firstName).lastName
    }

    @GetMapping("totalSum")
    fun getTotalSum(@RequestParam uuid : String) : Double {
        return userService.getTotalSumForUser(uuid)
    }

    @GetMapping("subscriptions")
    fun getWalletsSubscriptions(@RequestParam uuid : String): List<WalletSubscriptionDto> {
        return userService.getWalletsSubscriptions(uuid)
    }
}
package common.wallet.first.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @GetMapping("get")
    fun get() : String {
        return "Hi";
    }
}
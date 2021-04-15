package common.wallet.first.controller

import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.entity.Wallet
import common.wallet.first.service.UserService
import common.wallet.first.service.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("wallet")
class WalletController @Autowired constructor(
    private val walletService: WalletService) {

    @PostMapping("new")
    fun createNewWallet(@RequestBody walletCreateDto: WalletCreateDto): WalletDto {
        return walletService.createNewWallet(walletCreateDto)
    }

    @GetMapping("recordsOfWallet")
    fun createNewWallet(@RequestParam id : String): List<RecordDto> {
        return walletService.getAllRecordsInWallet(id)
    }

    @GetMapping("totalSum")
    fun getTotalSumOfWallet(@RequestParam id : String): Double {
        return walletService.getTotalSumOfWallet(id)
    }

    @GetMapping("deleteWallet")
    fun deleteWallet(@RequestParam userId : String, @RequestParam walletId : String): String {
        return walletService.deleteWallet(userId, walletId)
    }
}
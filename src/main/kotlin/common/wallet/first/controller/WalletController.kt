package common.wallet.first.controller

import common.wallet.first.dto.InviteCodeDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.entity.Wallet
import common.wallet.first.service.InviteCodeService
import common.wallet.first.service.UserService
import common.wallet.first.service.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("wallet")
class WalletController @Autowired constructor(
    private val walletService: WalletService,
    private val inviteCodeService: InviteCodeService
) {

    @PostMapping("new")
    fun createNewWallet(@RequestBody walletCreateDto: WalletCreateDto): WalletDto {
        return walletService.createNewWallet(walletCreateDto)
    }

    @GetMapping("recordsOfWallet")
    fun createNewWallet(@RequestParam uuid : String): List<RecordDto> {
        return walletService.getAllRecordsInWallet(uuid)
    }

    @GetMapping("totalSum")
    fun getTotalSumOfWallet(@RequestParam uuid : String): Double {
        return walletService.getTotalSumOfWallet(uuid)
    }

    @GetMapping("deleteWallet")
    fun deleteWallet(@RequestParam userUuid : String, @RequestParam walletUuid : String): String {
        return walletService.deleteWallet(userUuid, walletUuid)
    }

    @PutMapping("subscribe")
    fun subscribeToWallet(@RequestParam walletUuid : String): InviteCodeDto {
        return inviteCodeService.addNewInviteCode(walletUuid)
    }
}
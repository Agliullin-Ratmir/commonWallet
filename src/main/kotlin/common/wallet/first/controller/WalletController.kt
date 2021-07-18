package common.wallet.first.controller

import common.wallet.first.dto.CreateInviteCodeDto
import common.wallet.first.dto.EditWalletDto
import common.wallet.first.dto.InviteCodeDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.SubscribeDto
import common.wallet.first.dto.UnsubscribeDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.dto.WalletDto
import common.wallet.first.entity.Wallet
import common.wallet.first.enum.WalletSubscriberType
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

    @PostMapping("recordsOfWallet")
    fun getRecordsOfWallet(@RequestParam walletUuid : String, @RequestBody userUuid: String): List<RecordDto> {
        return walletService.getAllRecordsInWallet(walletUuid, userUuid)
    }

    @PostMapping("totalSum")
    fun getTotalSumOfWallet(@RequestParam walletUuid : String, @RequestBody userUuid: String): Double {
        return walletService.getTotalSumOfWallet(walletUuid, userUuid)
    }

    @GetMapping("deleteWallet")
    fun deleteWallet(@RequestParam userUuid : String, @RequestParam walletUuid : String): String {
        return walletService.deleteWallet(userUuid, walletUuid)
    }

    @PostMapping("getInviteCode")
    fun getInviteCode(@RequestBody item: CreateInviteCodeDto): InviteCodeDto {
        return inviteCodeService.addNewInviteCode(
            item.walletUuid, item.userStatus,
            item.userUuid
        )
    }

    @PutMapping("subscribe")
    fun subscribeToWallet(@RequestBody dto: SubscribeDto): Boolean {
        return inviteCodeService.subscribeToWallet(dto.inviteCode, dto.userUuid)
    }

    @PutMapping("unsubscribe")
    fun unsubscribeOfWallet(@RequestBody dto: UnsubscribeDto): String {
        return walletService.unsubscribeOfWallet(dto.userUuid, dto.walletUuid)
    }

    @PutMapping("edit")
    fun editWallet(@RequestParam userUuid : String, @RequestBody dto: EditWalletDto): String {
        return walletService.editWalletInfo(userUuid, dto)
    }
}
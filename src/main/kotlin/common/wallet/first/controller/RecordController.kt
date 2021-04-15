package common.wallet.first.controller

import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.dto.WalletCreateDto
import common.wallet.first.entity.Record
import common.wallet.first.entity.Wallet
import common.wallet.first.service.RecordService
import common.wallet.first.service.UserService
import common.wallet.first.service.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("record")
class RecordController @Autowired constructor(
    private val recordService: RecordService) {

    @PostMapping("new")
    fun createNewRecord(@RequestBody recordCreateDto: RecordCreateDto) : RecordDto {
        return recordService.createNewRecord(recordCreateDto)
    }

    @PutMapping("deleteRecord")
    fun createNewRecord(@RequestBody userId: String, @RequestBody recordId: String) : String {
        return recordService.deleteRecord(userId, recordId)
    }
}
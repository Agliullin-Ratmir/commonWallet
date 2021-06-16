package common.wallet.first.controller

import common.wallet.first.dto.RecordCreateDto
import common.wallet.first.dto.RecordDto
import common.wallet.first.service.RecordService
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
    fun createNewRecord(@RequestBody userUuid: String, @RequestBody recordUuid: String) : String {
        return recordService.deleteRecord(userUuid, recordUuid)
    }

    @GetMapping("get")
    fun getRecordInfo(@RequestParam uuid : String) : RecordDto? {
        return recordService.getRecordByUuid(uuid)
    }
}
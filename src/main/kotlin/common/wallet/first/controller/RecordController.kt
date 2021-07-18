package common.wallet.first.controller

import common.wallet.first.dto.EditRecordDto
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
    fun createNewRecord(@RequestBody recordCreateDto: RecordCreateDto) : RecordDto? {
        return recordService.createNewRecord(recordCreateDto)
    }

    @PutMapping("deleteRecord")
    fun deleteRecord(@RequestBody userUuid: String, @RequestBody recordUuid: String) : String {
        return recordService.deleteRecord(userUuid, recordUuid)
    }

    @PostMapping("get")
    fun getRecordInfo(@RequestParam recordUuid : String, @RequestBody userUuid: String) : RecordDto? {
        return recordService.getRecordByUuid(recordUuid, userUuid)
    }

    @PutMapping("edit")
    fun editRecord(@RequestParam userUuid: String, @RequestBody dto: EditRecordDto) : String {
        return recordService.editRecord(userUuid, dto)
    }
}
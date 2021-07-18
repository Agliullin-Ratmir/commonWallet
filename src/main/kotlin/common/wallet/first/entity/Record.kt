package common.wallet.first.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class Record (
    @Id
    var id: ObjectId = ObjectId.get(),
    @Indexed(unique = true)
    var uuid: String = UUID.randomUUID().toString(),
    var userUuid: String = "",
    var title: String = "",
    var sum: Double = 0.0,
    var details: String = "",
    var walletUuid: String = ""
)
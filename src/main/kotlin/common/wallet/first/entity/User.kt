package common.wallet.first.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class User (
    @Id
    var id: ObjectId = ObjectId.get(),
    var uuid: String = UUID.randomUUID().toString(),
    var firstName: String = "",
    var lastName: String = "",
    var createdDate: LocalDateTime = LocalDateTime.now()
)
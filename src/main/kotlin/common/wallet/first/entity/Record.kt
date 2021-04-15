package common.wallet.first.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class Record (
    @Id
    var id: ObjectId = ObjectId.get(),
    var uuid: UUID = UUID.randomUUID(),
    var user: User = User(),
    var title: String = "",
    var sum: Double = 0.0,
    var details: String = "",
    var wallet: Wallet = Wallet(),
)
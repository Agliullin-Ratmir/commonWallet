package common.wallet.first.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class InviteCode (
    @Id
    var id: ObjectId = ObjectId.get(),
    var uuid: UUID = UUID.randomUUID(),
    var content: String? = null,
    var wallet: Wallet = Wallet(),
    var expiredDate: Long = System.currentTimeMillis(),
)
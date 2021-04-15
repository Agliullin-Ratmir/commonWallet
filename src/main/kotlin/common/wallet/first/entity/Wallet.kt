package common.wallet.first.entity

import common.wallet.first.enum.WalletSubscriberType
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class Wallet (
    @Id
    var id: ObjectId = ObjectId.get(),
    var uuid: UUID = UUID.randomUUID(),
    var title: String = "",
    var description: String = "",
    var owner: User = User(),
    var admins: MutableList<User> = mutableListOf<User>(),
    var users: MutableList<User> = mutableListOf<User>(),
    var records: MutableList<Record> = mutableListOf<Record>()
)
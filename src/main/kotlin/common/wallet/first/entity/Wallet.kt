package common.wallet.first.entity

import common.wallet.first.enum.WalletSubscriberType
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class Wallet (
    @Id
    var id: ObjectId = ObjectId.get(),
    @Indexed(unique = true)
    var uuid: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var ownerUuid: String = "",
    var adminsUuid: MutableList<String> = mutableListOf<String>(),
    var usersUuid: MutableList<String> = mutableListOf<String>(),
    var recordsUuid: MutableList<String> = mutableListOf<String>()
)
package common.wallet.first.entity

import common.wallet.first.enum.AnswerStatus
import common.wallet.first.enum.WalletSubscriberType
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class InviteCode (
    @Id
    var id: ObjectId = ObjectId.get(),
    @Indexed(unique = true)
    var uuid: String = UUID.randomUUID().toString(),
    var content: String? = null,
    var userStatus: WalletSubscriberType = WalletSubscriberType.UNRECOGNIZED,
    var walletUuid: String = "",
    var expiredDate: Long = System.currentTimeMillis(),
    var answerStatus: AnswerStatus = AnswerStatus.OK
)
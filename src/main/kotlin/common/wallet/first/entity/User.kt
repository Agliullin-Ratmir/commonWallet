package common.wallet.first.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
class User (
    @Id
    var id: ObjectId = ObjectId.get(),
    @Indexed(unique = true)
    var uuid: String = UUID.randomUUID().toString(),
    var nickName: String = "",
    var pinCode: String = "",
    var createdDate: LocalDateTime = LocalDateTime.now()
) {
    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as User

        if (!this.uuid.equals(other.uuid)) return false
        if (!this.nickName.equals(other.nickName)) return false
        if (!this.pinCode.equals(other.pinCode)) return false

        return true
    }

    override fun toString(): String = "uuid: " + this.uuid

    override fun hashCode(): Int{
        return this.hashCode()
    }
}
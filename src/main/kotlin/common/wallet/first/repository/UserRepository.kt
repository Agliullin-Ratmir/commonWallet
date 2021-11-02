package common.wallet.first.repository

import common.wallet.first.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    override fun findById(id: String): Optional<User>
    fun findByUuid(uuid: String): Optional<User>
    fun findByUuidIsIn(uuids: MutableList<String>): MutableList<User>
    fun findByNickNameAndUuid(nickName: String, uuid: String): Optional<User>
    fun existsUserByNickName(nickName: String): Boolean
}
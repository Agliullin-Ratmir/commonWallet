package common.wallet.first.repository

import common.wallet.first.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    override fun findById(id: String): Optional<User>
    fun findByFirstName(firstName: String): Optional<User>
}
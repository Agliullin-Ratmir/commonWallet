package common.wallet.first.repository

import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WalletRepository : MongoRepository<Wallet, String> {
    override fun findById(id: String): Optional<Wallet>
    fun deleteByUuid(uuid: String)
    fun findByUuid(uuid: String): Optional<Wallet>
    fun findAllByOwnerUuid(ownerUuid: String): List<Wallet>
    fun findAllByAdminsUuidIn(adminUuid: String): List<Wallet>
    fun findAllByUsersUuidIn(userUuid: String): List<Wallet>
}
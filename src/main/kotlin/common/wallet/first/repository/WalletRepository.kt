package common.wallet.first.repository

import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WalletRepository : MongoRepository<Wallet, String> {
    override fun findById(id: String): Optional<Wallet>
    fun findAllByOwner(user: User): List<Wallet>
    fun findAllByAdminsIn(user: User): List<Wallet>
    fun findAllByUsersIn(user: User): List<Wallet>
}
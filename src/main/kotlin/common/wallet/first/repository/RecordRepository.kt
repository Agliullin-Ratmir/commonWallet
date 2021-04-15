package common.wallet.first.repository

import common.wallet.first.entity.Record
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RecordRepository : MongoRepository<Record, String> {

    fun findAllByWallet(wallet: Wallet) : List<Record>
}
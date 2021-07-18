package common.wallet.first.repository

import common.wallet.first.entity.Record
import common.wallet.first.entity.User
import common.wallet.first.entity.Wallet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RecordRepository : MongoRepository<Record, String> {
    fun findByUuid(uuid: String): Optional<Record>
    fun findByUuidIsIn(uuids: MutableList<String>): MutableList<Record>
    fun deleteByUuid(uuid: String)
    fun findAllByWalletUuid(walletUuid: String) : List<Record>
}
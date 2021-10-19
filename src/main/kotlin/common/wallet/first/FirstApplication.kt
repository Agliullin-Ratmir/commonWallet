package common.wallet.first

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories("common.wallet.first.repository")
open class FirstApplication

fun main(args: Array<String>) {
    runApplication<FirstApplication>(*args)
}

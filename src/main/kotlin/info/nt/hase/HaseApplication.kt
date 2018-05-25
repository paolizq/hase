package info.nt.hase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HaseApplication

fun main(args: Array<String>) {
    runApplication<HaseApplication>(*args)
}

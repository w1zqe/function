import java.util.*

fun main() {
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЫЪЫЭЮЯ"
    val tableSize = alphabet.length

    println("Введите исходное сообщение:")
    val message = readLine()?.uppercase()?.replace("\\s+".toRegex(), "") ?: ""

    println("Введите ключ:")
    val key = readLine()?.uppercase()?.replace("\\s+".toRegex(), "") ?: ""

    println("Использовать типовую таблицу или сгенерировать случайную? (Введите 'типовая' или 'случайная'):")
    val tableType = readLine()?.lowercase() ?: "типовая"

    val table = if (tableType == "типовая") {
        typeTable(alphabet)
    } else {
        randomTable(alphabet)
    }

    // Подготовка ключа
    val repeatedKey = key.repeat((message.length / key.length) + 1).take(message.length)

    // Шифрование сообщения
    val encryptedMessage = StringBuilder()
    for (i in message.indices) {
        val messageChar = message[i]
        val keyChar = repeatedKey[i]
        val row = alphabet.indexOf(keyChar)
        val col = alphabet.indexOf(messageChar)
        val encryptedChar = table[row][col]
        encryptedMessage.append(encryptedChar)
    }

    println("Исходное сообщение:")
    println(message)
    println("Ключ (повторяется):")
    println(repeatedKey)
    println("Зашифрованное сообщение:")
    println(encryptedMessage)

    println("Шифровальная таблица:")
    printTable(table)
}

fun typeTable(alphabet: String): Array<String> {
    val table = Array(alphabet.length) { CharArray(alphabet.length) }
    for (i in alphabet.indices) {
        for (j in alphabet.indices) {
            table[i][j] = alphabet[(i + j) % alphabet.length]
        }
    }
    return table.map { String(it) }.toTypedArray()
}

fun randomTable(alphabet: String): Array<String> {
    val random = Random()
    val shuffledAlphabet = alphabet.toList().shuffled(random).joinToString("")
    val table = Array(alphabet.length) { CharArray(alphabet.length) }
    for (i in alphabet.indices) {
        for (j in alphabet.indices) {
            table[i][j] = shuffledAlphabet[(i + j) % shuffledAlphabet.length]
        }
    }
    return table.map { String(it) }.toTypedArray()
}

fun printTable(table: Array<String>) {
    for (row in table) {
        println(row)
    }
}
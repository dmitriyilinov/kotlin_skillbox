package corutines

import kotlinx.coroutines.*

/* async это еще один билдер корутин. В отличие от launch этот билдер может принимать некоторый результат.
Т.е. допустим у вас есть функции которая будет возвращать какое либо значение. У async есть объект Deferred (это
то же самое что и Job у launch, на самом деле Deferred наследуется от Job, поэтому ему доступен весь его функционал),
и этот объект Deferred принимает полученный результат. Обратиться и вызвать этот результат можно через метод await().

Ещё следует отметить что метод await() не только вызывает полученное
значение, но и является в дополнение методом join(). Т.е. нельзя ведь вызвать данные если корутина их еще не посчитала,
поэтому при применении этого метода, программа будет ждать получения результата в моменте его вызова.

Немного запутанно, давайте смотреть на примере.

Допустим у нас есть три функции. Которые выбирают случайное имя, фамилию и действие. Результат этих функций мы сохраняем
в Deferred. И потом печатаем общий результат.
 */


suspend fun main() {

   coroutineScope {

        println("Запускаем корутины и ждём результат...")

        val randomName: Deferred<String> = async {
            randomName()
        }

        val randomSurname: Deferred<String> = async {
            randomSurname()
        }

        val randomAction: Deferred<String> = async {
            randomAction()
        }

        // как только выполнятся корутины, мы получим результат
        val name = randomName.await()
        val surname = randomSurname.await()
        val action = randomAction.await()

        println("$name $surname $action")

    }
}

suspend fun randomName(): String {
    delay(2000)
    val listName = listOf<String>("Анатолий", "Юрий", "Пётр", "Сергей", "Андрей")
    return listName.random()
}

suspend fun randomSurname(): String {
    delay(1000)
    val listSurname = listOf<String>("Бубликов", "Рыжов", "Самохвалов", "Новосельцев", "Калугин")
    return listSurname.random()
}

suspend fun randomAction(): String {
    delay(3000)
    val listAction = listOf<String>("спит", "ест", "делает отчёт", "курит", "отдыхает")
    return listAction.random()
}
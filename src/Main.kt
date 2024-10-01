import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


suspend fun main() = coroutineScope {
    val personList: MutableList<Person> = mutableListOf()
    val myPerson = PersonManager(personList)
    val resultList: MutableMap<Person, Int> = mutableMapOf()

    println("Программа работы с базой данных сотрудников")
    delay(1000L)
    while (true){
        println("Добавить сотрудника \n 1.Да \n 2.Нет")
        val inputMain = readln()
        when(inputMain){
            "1" -> {

                val myPerson = PersonManager(personList)
                val person = myPerson.addPerson()
                println("Сотрудник ${person.name} успешно добавлен")
            }
            "2" -> {
                val job = launch {
                    val person = personList.last()
                    person.addPassword(resultList)
                    readDataPersonList(resultList)
                }
                if (readln() == "0"){

                    launch {
                        job.cancelAndJoin()
                        println("Завершение полной работы")
                    }
                }
                println("Завершение работы")
                break
            }
        }
    }




}

suspend infix fun Person.addPassword(mapPerson: MutableMap<Person,Int>){
    val pass = (100000..999999).random()
    mapPerson[this] = pass
    delay(500L)
    println("Пароль успешно создан")
}

suspend fun readDataPersonList(mapPerson: MutableMap<Person, Int>){
    for (i in mapPerson){
        println("Сотрудник ${i.key.name}; пароль: ${i.value}")
        delay(1000L)
    }
}





data class Person(val name: String,val salary: Int)

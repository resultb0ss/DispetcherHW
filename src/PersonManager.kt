class PersonManager(var personList: MutableList<Person>) {


    fun addPerson():Person{
        println("Введите имя сотрудника")
        val inputName = readln()
        println("Введите зарплату сотрудника")
        val inputSalary = readln()
        val person = Person(inputName,inputSalary.toInt())
        personList.add(person)

        return person
    }

}
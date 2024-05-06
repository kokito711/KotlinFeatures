package com.example.KotlinFeatures.generics.reified


//Reified prevents to type is erased at runtime --> Allows to check the reified type in runtime (inside the function)

inline fun <reified T> getAllByType(list: List<Any>): List<T> {
    val newList: MutableList<T> = mutableListOf()
    for (elem in list) {
        if (elem is T) {
            newList.add(elem)
        }
    }

    return newList
}

fun main(args: Array<String>) {
    val listOfCars: List<Any> = listOf(
        Volkswagen("WVWGV71K28W170393"),
        Audi("WAUZZZ8E33A039937"),
        Seat("WSSJ4B9MXXM066697"),
        Volkswagen("WVWZZZZ31KE325406"),
        Seat("WSSLZ7AJ0BM100048"),
        Seat("WSS4T7AJ0FM310340"))


    println(getAllByType<Audi>(listOfCars))
    println(getAllByType<Volkswagen>(listOfCars))
    println(getAllByType<Seat>(listOfCars))
}

data class Volkswagen(val vin: String)
data class Audi(val vin: String)
data class Seat(val vin: String)
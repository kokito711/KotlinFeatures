package com.example.KotlinFeatures.generics.covarianza

import com.example.KotlinFeatures.generics.contravarianza.Car

fun main(args: Array<String>) {
    //THIS WORKS
    val listOfCars: List<Volkswagen> = listOf(Volkswagen("WVWGV71K28W170393"), Volkswagen("WVWZZZZ31KE325406"))
    printVehicles(listOfCars)
    //THIS DOES NOT
    val listOfMutable: MutableList<Volkswagen> = mutableListOf(Volkswagen("WVWGV71K28W170393"), Volkswagen("WVWZZZZ31KE325406"))
    //printVehiclesMutable(listOfMutable)

    //WHY?? --> List of "Car" is literally a List of Car, so we are extending CAR which contains subtypes, meanwhile the mutable list is a subtype of list, so we are not extending car, but List
    val concesionario = Concesionario<Volkswagen>(listOf(Volkswagen("WVWGV71K28W170393"), Volkswagen("WVWZZZZ31KE325406")))
    vehiculosExposicion(concesionario)


}

fun printVehicles(list: List<Car>) {
    println("List of vehicles:")
    for (car in list) {
        println(car)
    }
}

fun printVehiclesMutable(list: MutableList<Car>) {
    for (car in list) {
        println(car)
    }
}

fun vehiculosExposicion(cochesVolkswagen: Concesionario<Volkswagen>) {

    printVehicle(cochesVolkswagen)
}

fun printVehicle(concesionario: Concesionario<Car>) {
    println("List of vehicles with out covariance:")
    for(car in concesionario.cars) {
        println(car)
    }
}

//class Concesionario<T: Car>(val cars: List<T>) {
class Concesionario<out T: Car>(val cars: List<T>){
// OUT Covariance does not allow to add more types more than the defined

    fun selectCar(i: Int): T = cars[i]
    //Error because is OUT covariance --> It does not allow because it might be multiple implementations of Car
    fun addCar(car : @UnsafeVariance T) {}
}

//What if we want a mutable variable?
//class Concesionario<out T: Car>(var cars: List<T>){// -->
/*class Concesionario<out T: Car>(private var cars: List<T>){
// OUT Covariance does not allow to add more types more than the defined

    fun selectCar(i: Int): T = cars[i]
    fun addCar(car : T) {}
}*/




class Volkswagen(val vin: String) : Car()
data class Audi(val vin: String): Car()
data class Seat(val vin: String): Car()

open class Car()
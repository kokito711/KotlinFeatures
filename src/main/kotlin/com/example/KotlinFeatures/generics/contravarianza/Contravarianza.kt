package com.example.KotlinFeatures.generics.contravarianza

//Accept instances of superclasses instead of only subclasses
fun main(args: Array<String>) {
    val listOfCars: List<Volkswagen> = listOf(Volkswagen("WVWGV71K28W170393"), Volkswagen("WVWZZZZ31KE325406"))
    val vendedorVW= object : Vendedor<Volkswagen> {
        override fun vender(car: Volkswagen) {
            print("Selling car $car")
        }
    }
    val concesionarioVW = Concesionario<Volkswagen>(listOfCars, vendedorVW)
    concesionarioVW.venderCoche(0)

    val vendedorAudi = object : Vendedor<Audi> {
        override fun vender(car: Audi) {
            print("Selling car $car")
        }
    }
    val listOfAudi: List<Audi> = listOf(Audi("WVWGV71K28W170393"), Audi("WVWZZZZ31KE325406"))
    val concesionarioAudi = Concesionario<Audi>(listOfAudi, vendedorAudi)
    concesionarioAudi.venderCoche(0)

    //THIS IS REPETITIVE --> Both vendors are doing the same

    val vendedor = object : VendedorContravarianza<Car> {
        override fun vender(car: Car) {
            print("Selling car $car")
        }
    }

    //This makes error because we are expecting T and Car is not T. Audi is T
    //val concesionarioAudi2 = Concesionario<Audi>(listOfAudi, vendedor)

    val concesionarioAudi2 = Concesionario2<Audi>(listOfAudi, vendedor)
    concesionarioAudi2.venderCoche(0)
}

class Concesionario<T: Car>(val cars: List<T>, val vendedor: Vendedor<T>){

    fun selectCar(i: Int): T = cars[i]
    fun venderCoche(i: Int) = vendedor.vender(selectCar(i))
}

class Concesionario2<T: Car>(val cars: List<T>, val vendedor: VendedorContravarianza<T>){

    fun selectCar(i: Int): T = cars[i]
    fun venderCoche(i: Int) = vendedor.vender(selectCar(i))
}


data class Volkswagen(val vin: String) : Car()
data class Audi(val vin: String): Car()
data class Seat(val vin: String): Car()

open class Car

interface Vendedor<T> {
    fun vender(car: T)
}
//with contravariance
interface VendedorContravarianza<in T> {
    fun vender(car: T)

    //This does not work bc contravariance only allows to write, the opposite of variance
    fun select():T
}
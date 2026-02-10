package org.example

import java.util.ArrayList
import kotlin.math.max

//2.1 Базовый класс транспортного средства (Vehicle)
abstract class Vehicle(
    open val vinVehicle: String,
    open val modelVehicle: String,
    open val yearVehicle: Int,
    open var mileageVehicle: Int = 0,
    open var statusVehicle: Boolean = true
)

//2.2 Специализированные типы транспортных средств
//2.2.1 Легковой автомобиль (Car)
data class Car(
    override val maxPassengers: Int,
    override var currentPassengers: Int = 0,
    override val vinVehicle: String,
    override val modelVehicle: String,
    override val yearVehicle: Int,
    override var mileageVehicle: Int = 0,
    override var statusVehicle: Boolean = true
) : PassengerTransport, Vehicle(vinVehicle, modelVehicle, yearVehicle, mileageVehicle, statusVehicle)

//2.2.2 Автобус (Bus)
data class Bus(
    override val maxPassengers: Int,
    override var currentPassengers: Int = 0,
    override val vinVehicle: String,
    override val modelVehicle: String,
    override val yearVehicle: Int,
    override var mileageVehicle: Int = 0,
    override var statusVehicle: Boolean = true
) : PassengerTransport, Vehicle(vinVehicle, modelVehicle, yearVehicle, mileageVehicle, statusVehicle)

//2.2.3 Грузовик (Truck)
data class Track(
    override val maxLoadCapacity: Double,
    override var currentLoad: Double = 0.0,
    override val vinVehicle: String,
    override val modelVehicle: String,
    override val yearVehicle: Int,
    override var mileageVehicle: Int = 0,
    override var statusVehicle: Boolean = true
) : CargoTransport, Vehicle(vinVehicle, modelVehicle, yearVehicle, mileageVehicle, statusVehicle)

//2.3.1 Интерфейс пассажирского транспорта (PassengerTransport)
interface PassengerTransport {
    val maxPassengers: Int // максимальное количество пассажиров
    var currentPassengers: Int // текущее количество пассажиров

    // метод для посадки пассажиров. Проверяет, не превысит ли посадка максимальную вместимость
    //В случае превышения выбрасывает исключение.Возвращает новое количество пассажиров
    fun board(count: Int): Int {
        try {
            if ((currentPassengers + count) > maxPassengers) {
                throw VehicleException("Превышена вместимость ТС")
            } else {
                this.currentPassengers += count
            }
        } catch (e: RuntimeException) {
            throw VehicleException("Ошибка выполнения ${e.message}")
        }
        return currentPassengers.also { println("Количество пассажиров в ТС: ${currentPassengers}") }
    }

    //метод для высадки пассажиров. В случае попытки высадить больше чем есть выбрасывает исключение
    //Возвращает оставшееся количество пассажиров. Уменьшает текущее количество пассажиров
    fun unboard(count: Int): Int {
        try {
            if ((currentPassengers - count) < 0) {
                throw VehicleException("Нельзя высадить пассажиров больше чем есть")
            } else this.currentPassengers -= count
        } catch (e: RuntimeException) {
            throw VehicleException("Ошибка выполнения ${e.message}")
        }
        return currentPassengers.also { println("Количество пассажиров в ТС: ${currentPassengers}") }
    }

    //Может перевозить пассажиров
    fun movePassengers(inCount: Int) {
        println("Начало посадки")
        this.board(inCount)
        println("Начал маршрут")
        println("Завершил маршрут")
        this.unboard(inCount)
    }
}

//2.3.2 Интерфейс грузового транспорта (CargoTransport)
interface CargoTransport {
    val maxLoadCapacity: Double // максимальная грузоподъемность
    var currentLoad: Double // текущий вес груза

    //метод для загрузки груза. Проверяет, не превысит ли загрузка максимальную грузоподъемность
    //В случае превышения выбрасывает исключение. Возвращает новый вес груза
    fun load(weight: Double): Double {
        try {
            if (currentLoad + weight > maxLoadCapacity) {
                throw VehicleException("Нельзя загрузить больше ${maxLoadCapacity}")
            } else currentLoad += weight
        } catch (e: RuntimeException) {
            throw VehicleException("Ошибка выполнения ${e.message}")
        }
        return currentLoad.also { println("Текущий вес грузового транспорта: ${currentLoad}") }
    }

    //метод для полной разгрузки груза. Возвращает вес выгруженного груза. Обнуляет текущий вес груза
    fun unload(): Double {
        var unloadedLoad = currentLoad
        currentLoad = 0.0
        try {
        } catch (e: RuntimeException) {
            throw VehicleException("Ошибка выполнения ${e.message}")
        }
        return unloadedLoad.also { println("Текущий вес грузового транспорта: ${currentLoad}") }
    }

    //Может перевозить грузы
    fun movePassengers(inLoad: Double) {
        println("Начало загрузки")
        this.load(inLoad)
        println("Начал маршрут")
        println("Завершил маршрут")
        this.unload()
    }
}

//2.4 Пользовательское исключение
//Создать класс VehicleException, который наследуется от RuntimeException и используется для обработки ошибок, связанных с транспортными средствами (перегруз, переполнение и т.д.)
class VehicleException(override val message: String) : RuntimeException(message)

//2.5 Класс автопарка (VehicleFleet)
class VehicleFleet<T>(val vehicleName: String, var vehicles: MutableList<T>) {
    //для добавления транспортного средства
    fun addVehicle(newVehicle: T) {
        this.vehicles.add(newVehicle)
    }

    // для удаления транспортного средства
    fun removeVehicle(removeVehicle: T) {
        this.vehicles.remove(removeVehicle)
    }

    //для получения неизменяемого списка всех транспортных средств
    fun getAllVehicles(): MutableList<T> {
        val constVehcles = this.vehicles
        return constVehcles
    }

    //для расчета суммарной максимальной грузоподъемности всех грузовиков в автопарке
    fun getAllTrucksMaxLoad(): Double {
        return this.vehicles.filterIsInstance<CargoTransport>().sumOf { it.maxLoadCapacity }
    }

    //для получения списка всего пассажирского транспорта в автопарке
    fun getAllPassengerTransport(): List<PassengerTransport> {
        return this.vehicles.filterIsInstance<PassengerTransport>()
    }
}


fun main() {
    var listVehicleFleets: MutableList<VehicleFleet<Vehicle>> = mutableListOf(
        VehicleFleet(
            "firstVehicle", mutableListOf(
                Car(
                    maxPassengers = 4,
                    currentPassengers = 4,
                    vinVehicle = "XXX",
                    modelVehicle = "bestmodelXXX",
                    yearVehicle = 1900,
                    mileageVehicle = 2000
                ),
                Car(
                    maxPassengers = 6,
                    currentPassengers = 5,
                    vinVehicle = "ZZZ",
                    modelVehicle = "modelZZZ",
                    yearVehicle = 1900,
                    mileageVehicle = 2000
                ),
                Bus(
                    maxPassengers = 26,
                    currentPassengers = 10,
                    vinVehicle = "RRRRZZZ",
                    modelVehicle = "BusmodelRRRRZZZ",
                    yearVehicle = 1900,
                    mileageVehicle = 2000,
                    statusVehicle = false
                ),
                Track(
                    maxLoadCapacity = 260.0,
                    currentLoad = 100.0,
                    vinVehicle = "PPPRRRRZZZ",
                    modelVehicle = "BusmodelPPPRRRRZZZ",
                    yearVehicle = 1966,
                    mileageVehicle = 2000,
                    statusVehicle = false

                ),
                Track(
                    maxLoadCapacity = 360.0,
                    currentLoad = 10.0,
                    vinVehicle = "JJJJPPPRRRRZZZ",
                    modelVehicle = "BusmodelJJJPPPRRRRZZZ",
                    yearVehicle = 1988,
                    mileageVehicle = 2000
                ),
                Track(
                    maxLoadCapacity = 1360.0,
                    currentLoad = 1000.0,
                    vinVehicle = "JGGGPPPRRRRZZZ",
                    modelVehicle = "BusmodelJGGGPPRRRRZZZ",
                    yearVehicle = 1986,
                    mileageVehicle = 200000
                )
            )
        ),
        VehicleFleet(
            "secondVehicle", mutableListOf(
                Car(
                    maxPassengers = 4,
                    currentPassengers = 1,
                    vinVehicle = "YYYY",
                    modelVehicle = "goodmodelYYYY",
                    yearVehicle = 2026,
                    mileageVehicle = 0,
                    statusVehicle = false
                ),
                Car(
                    maxPassengers = 6,
                    currentPassengers = 2,
                    vinVehicle = "KKK",
                    modelVehicle = "modelKKK",
                    yearVehicle = 2009,
                    mileageVehicle = 20000
                ),
                Bus(
                    maxPassengers = 26,
                    currentPassengers = 10,
                    vinVehicle = "FFFFZZZ",
                    modelVehicle = "BusmodelFFFZZZ",
                    yearVehicle = 1900,
                    mileageVehicle = 2000
                ),
                Track(
                    maxLoadCapacity = 2260.0,
                    currentLoad = 1200.0,
                    vinVehicle = "YYPPPRRRRZZZ",
                    modelVehicle = "BusmodelYYPPPRRRRZZZ",
                    yearVehicle = 1971,
                    mileageVehicle = 223000,
                    statusVehicle = false
                ),
                Track(
                    maxLoadCapacity = 3460.0,
                    currentLoad = 410.0,
                    vinVehicle = "JJJJEEEPPPRRRRZZZ",
                    modelVehicle = "BusmodelJJEEJPPPRRRRZZZ",
                    yearVehicle = 2008,
                    mileageVehicle = 8000,
                    statusVehicle = false
                ),
                Track(
                    maxLoadCapacity = 11360.0,
                    currentLoad = 1000.0,
                    vinVehicle = "JGGGPPPRJHGRRRZZZ",
                    modelVehicle = "BusmodelJGGGPGDFPRRRRZZZ",
                    yearVehicle = 1956,
                    mileageVehicle = 700000
                )
            )
        )
    )

    println("Демонстрация: Поиск самого нового транспортного средства во всех автопарках")
    println(listVehicleFleets.findNewestVehicle())

    println("Демонстрация: Поиск самого вместительного пассажирского транспорта")
    println(listVehicleFleets.findMaxPassengersVehicle())

    println("Демонстрация: Посадка пассажиров без переполнения")
    listVehicleFleets[1].vehicles.filterIsInstance<Bus>().first().board(1)

    //println("Демонстрация: Посадка пассажиров с переполнением")
    // listVehicleFleets[0].vehicles.filterIsInstance<Car>().first().board(1)

    //высадка пассажиров без переполнения
    println("Демонстрация: Высадка пассажиров без переполнения")
    listVehicleFleets[0].vehicles.filterIsInstance<Car>().first().unboard(1)

    //высадка пассажиров с переполнением
    //println("Демонстрация: Высадка пассажиров с переполнением")
    //listVehicleFleets[1].vehicles.filterIsInstance<Bus>().first().unboard(40)

    //Поиск исправного грузового транспорта во втором автопарке и попытаться загрузить в него больше предусмотренного
    //println("Демонстрация: Поиск исправного грузового транспорта во втором автопарке и попытаться загрузить в него больше предусмотренного")
    //listVehicleFleets[1].vehicles.filterIsInstance<Track>().filter { it.statusVehicle }.first().load(100000.0)

}

//находит самое новое транспортное средство (с максимальным годом выпуска) среди всех автопарков
fun <T : Vehicle> MutableList<VehicleFleet<T>>.findNewestVehicle(): Vehicle {
    return this.flatMap { it.vehicles }.maxBy { it.yearVehicle }
}

// находит самый вместительный пассажирский транспорт (с максимальным количеством пассажиров) среди всех автопарков
fun <T : Vehicle> MutableList<VehicleFleet<T>>.findMaxPassengersVehicle(): PassengerTransport {
    return this.flatMap { it.vehicles }.filterIsInstance<PassengerTransport>().maxBy { it.maxPassengers }
}

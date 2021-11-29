import Entities.ECategoryProduct
import Entities.EProduct
import Interfaces.*
import Model.Product
import kotlin.reflect.KFunction
import kotlin.reflect.typeOf

open class Menu {
    fun printlnTablemenu(List: List<Pair<String, CheckItemsMenu>>) {
        var str = ""
        while (str != "Exit") {
            println("Выберите таблицу которую хотите использовать")
            List.forEach { println(it.first) }
            println("2.Exit")
            var getTable = readLine()?.toIntOrNull()
            if (getTable != null) {
                var takeTable = List[getTable].second
                    println("Выберите функцию которую хотите использовать")
                    if (takeTable != null) {
                        takeTable.listfunction.forEach { println(it.first) }
                        var getFunction = readLine()?.toIntOrNull()
                        if (getFunction != null) {
                            takeTable.listfunction[getFunction].second.invoke()
                        }
                }
            } else if (getTable == 3) str = "Exit"
        }
    }
}
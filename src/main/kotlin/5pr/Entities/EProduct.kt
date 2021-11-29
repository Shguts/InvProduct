package Entities

import CheckItemsMenu
import Interfaces.*
import Model.CategoryProduct
import Model.Product
import Validation
import java.lang.reflect.Field
import java.util.*

data class EProduct(var categoryProduct: ECategoryProduct,var Listbroadcast:ListFunctionProductclass,var EProductInputbroadcast:EProductInput,var list:MutableList<Pair<String,(Product)->Comparable<Any>>>,var searchlist:MutableList<Pair<String,(Product)->String>>):CheckItemsMenu(){

    override fun Info() {
        Listbroadcast.ProductsList.forEach { println("${it.Id} Название товара : ${it.NameProduct} , Цена товара: ${it.priceProduct} , Описание: ${it.descriptionProduct}, Категория: ${it.IdCategory}")}
    }

    override fun Add():Unit {
        var getobg = EProductInputbroadcast.Input(categoryProduct,Validation())
        if (getobg!=null) Listbroadcast.Add(getobg)

    }

    override fun Delete():Unit {
        var getdeleteobg = CheckIndex()
        if (getdeleteobg!=null)
        Listbroadcast.Delete(getdeleteobg)
    }

    override fun Edit():Unit {
        var geteditobg = CheckIndex()
        var newnameobg = EProductInputbroadcast.Input(categoryProduct,Validation())
        if (newnameobg!=null) Listbroadcast.Edit(Pair(newnameobg,geteditobg))
    }

    override fun CheckIndex():UUID? {
        Info()
        println("Выберите какой элемент над каким элементом списка вы хотите сделать действие")
        var getindex = readLine()?.toIntOrNull()
        if (getindex!=null) {
            var result: UUID? = null
            var ee = Listbroadcast.ProductsList.filterIndexed{ index,_ ->(index==getindex)}
            result = ee.first().Id
            return if (result!=null) result else null
        }
        return null
    }
    override fun Sort() {
        list.forEach{ println(it.first)}
        var getLine2 = readLine()!!.toIntOrNull()
        if (getLine2!=null) {
            println("Выберите тип сортировки \n 1.По возрастанию \n 2.По убыванию")
            var getLine = readLine()!!.toIntOrNull()
            when (getLine) {
                1 -> Listbroadcast.ProductsList.sortBy(list[getLine2].second)
                2 -> Listbroadcast.ProductsList.sortByDescending(list[getLine2].second)
            }
            Info()
        }
    }

    override fun Search() {
        println("Выберите элемент поиска")
        searchlist.forEach { println(it.first) }
        var getindex = readLine()?.toIntOrNull()
        if (getindex!=null) {
            var getstr = readLine()
            if (getstr!=null) {
                var takeProp = searchlist[getindex].second
                println(Listbroadcast.ProductsList.filter{ takeProp(it).contains(getstr) }.toString())
            }
        }
    }
}

class ListFunctionProductclass(var ProductsList:MutableList<Product> = mutableListOf()) {
        //var ProductsList:MutableList<Product> = mutableListOf()

        fun Add(Product: Product) {
            ProductsList.add(Product)
        }
        fun Delete(id: UUID?) {
            var getobjdelete = ProductsList.find { it.Id == id }
            ProductsList.remove(getobjdelete)
        }
        fun Edit(Editbleproduct: Pair<Product?, UUID?>): Product? {
            if (Editbleproduct.first != null && Editbleproduct.second != null) {
                var getchekobg = ProductsList.find { it.Id == Editbleproduct.second }
                getchekobg?.NameProduct = (Editbleproduct.first)!!.NameProduct
                getchekobg?.priceProduct = (Editbleproduct.first)!!.priceProduct
                getchekobg?.descriptionProduct = (Editbleproduct.first)!!.descriptionProduct
                getchekobg?.IdCategory = (Editbleproduct.first)!!.IdCategory
            }
            return null
        }
}

class EProductInput(){
    fun Input(categoryProduct:ECategoryProduct,Validation:Validation):Product? {
        if (categoryProduct.ListFunctionCategoryProductclass.CategoryProductList!=null) {
            println("Введите Продукт")
            var Nameproduct = readLine()
            println("Введите Цену")
            var Price = readLine()
            println("Введите описание продукта")
            var description = readLine()
            println("Выберите категорию:")
            categoryProduct.ListFunctionCategoryProductclass.CategoryProductList.forEach { println("${it.NameCategory}: ${it.Id}") }
            var categoryNum = UUID.fromString(readLine())
            return if (Validation.CheckString(Nameproduct) && Validation.CheckString(description) && Validation.Chekvar(
                    Price
                ) && categoryProduct.ListFunctionCategoryProductclass.CategoryProductList.find { it.Id == (categoryNum) } != null && categoryNum!=null
            ) {
                if (Nameproduct != null && Price != null && description != null) {
                    Product(
                        Nameproduct,
                        Price.toDouble(),
                        description,
                        (categoryProduct.ListFunctionCategoryProductclass.CategoryProductList.find { it.Id == (categoryNum) })!!.NameCategory
                    )
                } else null
            } else null
        } else return null
    }
}



package Entities

import CheckItemsMenu
import Interfaces.*
import Model.CategoryProduct
import Model.Product
import java.util.*

open class ECategoryProduct(var ListFunctionCategoryProductclass:ListFunctionCategoryProductclass,var ECategoryProductInputbroadcast:ECategoryProductInput,var list:MutableList<Pair<String,(CategoryProduct)->Comparable<Any>>>,var searchlist:MutableList<Pair<String,(CategoryProduct)->String>>):CheckItemsMenu(){

    override fun Info() {
        ListFunctionCategoryProductclass.CategoryProductList.forEach { println("${it.Id} Название категории : ${it.NameCategory}")}
        println("==============================================================")
    }

    override fun Add() {
        var getobg = ECategoryProductInputbroadcast.Input()
        if (getobg!=null) ListFunctionCategoryProductclass.Add(getobg,ListFunctionCategoryProductclass.CategoryProductList)

    }

    override fun Delete() {
        var getdeleteobg = CheckIndex()
        if (getdeleteobg!=null)
            ListFunctionCategoryProductclass.Delete(getdeleteobg,ListFunctionCategoryProductclass.CategoryProductList)
    }

    override fun Edit() {
        var geteditobg = CheckIndex()
        var newnameobg = ECategoryProductInputbroadcast.Input()
        if (newnameobg!=null) ListFunctionCategoryProductclass.Edit(Pair(newnameobg,geteditobg),ListFunctionCategoryProductclass.CategoryProductList)
    }

    override fun CheckIndex():UUID? {
        Info()
        println("Выберите какой элемент над каким элементом списка вы хотите сделать действие")
        var getindex = readLine()?.toIntOrNull()
        if (getindex!=null) {
            var result: UUID? = null
            ListFunctionCategoryProductclass.CategoryProductList.forEachIndexed { index, id ->
                if (index == getindex) {
                    result = id.Id
                    println(result)
                }
            }
            return result
        }
        return null
    }
    override fun Sort() {
        println("Выберите тип сортировки \n 1.По возрастанию \n 2.По убыванию")
        var getLine = readLine()!!.toIntOrNull()
        when (getLine) {
            1 -> ListFunctionCategoryProductclass.CategoryProductList.sortBy(list[0].second)
            2 -> ListFunctionCategoryProductclass.CategoryProductList.sortByDescending(list[0].second)
        }
        Info()
    }

    override fun Search() {
        println("Выберите элемент поиска")
        searchlist.forEach { println(it.first) }
        var getindex = readLine()?.toIntOrNull()
        if (getindex!=null) {
            var getstr = readLine()
            if (getstr!=null) {
                var takeProp = searchlist[getindex].second
                println(ListFunctionCategoryProductclass.CategoryProductList.filter{ takeProp(it).contains(getstr) }.toString())
            }
        }
    }
}

class ListFunctionCategoryProductclass {
        var CategoryProductList:MutableList<CategoryProduct> = mutableListOf()
        fun Add(CategoryProduct: CategoryProduct, CategoryProductList: MutableList<CategoryProduct>) {
            CategoryProductList.add(CategoryProduct)
        }

        fun Delete(id: UUID?, CategoryProductList: MutableList<CategoryProduct>) {
            var getobjdelete = CategoryProductList.find { it.Id == id }
            CategoryProductList.remove(getobjdelete)
        }
        fun Edit(
            EditbleCategoryProduct: Pair<CategoryProduct?, UUID?>,
            CategoryProductList: MutableList<CategoryProduct>
        ): CategoryProduct? {
            if (EditbleCategoryProduct.first != null && EditbleCategoryProduct.second != null) {
                var getchekobg = CategoryProductList.find { it.Id == EditbleCategoryProduct.second }
                getchekobg?.NameCategory = (EditbleCategoryProduct.first)!!.NameCategory
            }
            return null
        }
}

class ECategoryProductInput {
    fun Input():CategoryProduct? {
        var Nameproduct=readLine()
        var list = listOf(Nameproduct)
        if (Nameproduct!=null) {
            return CategoryProduct(Nameproduct)
        }
        else return null
    }
}



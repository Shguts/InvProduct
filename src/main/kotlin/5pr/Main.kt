import Entities.ECategoryProduct
import Entities.EProduct
import Model.CategoryProduct
import Model.Product

fun main(args: Array<String>) {
    var db = DB()
    var outkey = db.MListFunctionCategoryProductclass
    var v2 = ECategoryProduct(outkey,db.MECategoryProductInput, mutableListOf(Pair("Название") { x -> x.NameCategory as Comparable<Any> }),
        mutableListOf(Pair("Поиск по названию") { x -> x.NameCategory }))
    var v1 = EProduct(v2,db.Listbroadcast,db.EProductInputbroadcast,mutableListOf(Pair("Название продукта") { x -> x.NameProduct as Comparable<Any> },Pair("Цена Продукта") { x -> x.priceProduct as Comparable<Any> }),
        mutableListOf(Pair("Поиск по названию") { x -> x.NameProduct },Pair("Поиск по цене") { x -> x.priceProduct.toString() },Pair("Поиск по Описанию") { x -> x.descriptionProduct },Pair("Поиск по категории") { x -> x.IdCategory }))
    var MenuItem:List<Pair<String,CheckItemsMenu>> = listOf(Pair("0. Table №1",v1),Pair("1. Table №2",v2))
    var menu = Menu()
    menu.printlnTablemenu(MenuItem)
}
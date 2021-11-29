package Model

open class Product(NameProduct:String,priceProduct:Double,descriptionProduct:String,IdCategory:String):Entity() {
    var NameProduct:String = NameProduct
    var priceProduct:Double = priceProduct
    var descriptionProduct:String = descriptionProduct
    var IdCategory:String = IdCategory
}
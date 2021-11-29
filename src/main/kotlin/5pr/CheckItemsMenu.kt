import Interfaces.*

abstract class CheckItemsMenu: ISort, IInfo, ISearch, IDelete,IAdd,IEdit,ICheckIndex {
    var listfunction = mutableListOf<Pair<String,()->Unit>>()
    init {
        listfunction.add(Pair("0.Добавить") { this.Add() })
        listfunction.add(Pair("1.Удалить") { this.Delete() })
        listfunction.add(Pair("2.Редактировать") { this.Edit() })
        listfunction.add(Pair("3.Просмотр") { this.Info() })
        listfunction.add(Pair("4.Сортировка") { this.Sort() })
        listfunction.add(Pair("5.Поиск") { this.Search() })
        listfunction.add(Pair("6.Выход") { Unit })
    }
}
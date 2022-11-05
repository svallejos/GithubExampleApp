package com.bankuish.challenge.data.pagination

/*
* Esta clase no es nada del otro mundo, la uso en otros proyectos para llevar los calculos de las páginas
* Hay APIs que funcionan con el encabezado Range para la paginación (es el estandar recomendado)
* Pero la mayoría funciona con query parameters pageSize / pageNumber, o startItem / endItem
* Esta versión me permite construirla con cualquiera de los parametros, y obtener los demas
* Ejemplo, le digo el item inicial y el tamaño de página, y me calcula el numero de página y el end item.
* */
class Page<T> {

    var startItem: Int = -1  // Indice del primer item de la página en el server
    var endItem: Int  = -1// Indice del último item de la página en el server
    var maxItems: Int = -1// Total de items en el server, es -1 si se desconoce

    var items: MutableList<T> = ArrayList() // Items de la página
        set(value) {
            field.clear()
            field.addAll(value)
        }

    constructor(startItem: Int, pageSize: Int) {
        this.startItem = startItem
        this.endItem = startItem + pageSize - 1
    }

    constructor(startItem: Int, endItem: Int, maxItems: Int = -1) {
        this.startItem = startItem
        this.endItem = endItem
        this.maxItems = maxItems
    }

    constructor(items: List<T>, startItem: Int, maxItems: Int = -1) : this(startItem, startItem + items.size - 1, maxItems) {
        this.items.addAll(items)
    }

    val size: Int
        get() = if (startItem == -1) 0 else endItem - startItem + 1

    val number: Int
        get() = if (startItem == -1) 0 else startItem / size

    override fun toString(): String {
        return "${this.startItem}-${this.endItem}: ${this.items.size}"
    }

}
package com.bankuish.challenge.data

class RepositoryResponse<T>(
    val data: T? = null
    /* ¿Porque esta clase aca? */
    /* Usualmente no hago que el repositorio devuelva el dato pelado,
    * sino que lo devuelvo encapsulado en un RepositoryResponse.
    * Esta clase puede contener el dato, o si algo salio mal, el dato puede estar en null y puede haber
    * información del error ocurrido, para mostrar algo mas detallado al usuario */
)
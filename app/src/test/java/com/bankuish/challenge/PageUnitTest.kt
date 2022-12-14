package com.bankuish.challenge

import com.bankuish.challenge.data.pagination.Page
import org.junit.Assert.assertEquals
import org.junit.Test

class PageUnitTest {

    @Test
    fun startItem0AndPageSizeIsOk() {
        val page = Page<Any>(startItem = 0, pageSize = 10)
        assertEquals("El startItem es incorrecto",0, page.startItem)
        assertEquals("El endItem es incorrecto",9, page.endItem)
        assertEquals("El maxItems es incorrecto",-1, page.maxItems)
        assertEquals("El size es incorrecto",10, page.size)
        assertEquals("El número de página es incorrecto",0, page.number)
        assertEquals("El matchPage es incorrecto",true, page.matchPage)
    }

    @Test
    fun startItem1AndPageSizeIsOk() {
        val page = Page<Any>(startItem = 1, pageSize = 10)
        assertEquals("El startItem es incorrecto",1, page.startItem)
        assertEquals("El endItem es incorrecto",10, page.endItem)
        assertEquals("El maxItems es incorrecto",-1, page.maxItems)
        assertEquals("El size es incorrecto",10, page.size)
        assertEquals("El matchPage es incorrecto",false, page.matchPage)
    }

    @Test
    fun startItem10AndPageSizeIsOk() {
        val page = Page<Any>(startItem = 10, pageSize = 10)
        assertEquals("El startItem es incorrecto",10, page.startItem)
        assertEquals("El endItem es incorrecto",19, page.endItem)
        assertEquals("El maxItems es incorrecto",-1, page.maxItems)
        assertEquals("El size es incorrecto",10, page.size)
        assertEquals("El número de página es incorrecto",1, page.number)
        assertEquals("El matchPage es incorrecto",true, page.matchPage)
    }

    @Test
    fun startItem10ListSize5IsOk() {
        val items = listOf(1,2,3,4,5)
        val page = Page<Any>(items = items, startItem = 10)

        assertEquals("El startItem es incorrecto",10, page.startItem)
        assertEquals("El endItem es incorrecto",14, page.endItem)
        assertEquals("El maxItems es incorrecto",-1, page.maxItems)
        assertEquals("El size es incorrecto",5, page.size)
        assertEquals("El matchPage es incorrecto",true, page.matchPage)
        assertEquals("El número de página es incorrecto",2, page.number)
    }

}
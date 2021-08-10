package com.example.orders.mapper

import com.example.orders.network.ingridients.Ingridients
import com.example.orders.parseResource
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString

internal class IngridientsMapperTest {
    private val ingridients: Ingridients = parseResource("ingridients.json")
    private lateinit var mapper: IngridientsMapper

    @BeforeEach
    fun setUp() {
        mapper = IngridientsMapperImpl()
    }

    @Test
    fun `when search string not found`(){
        val searchString = "Any Data which is empty"
        val list1= mapper.invoke(ingridients.data, searchString, 1)
        assertTrue(list1.isEmpty())
        val list2= mapper.invoke(ingridients.data, searchString, 2)
        assertTrue(list2.isEmpty())
        val list3= mapper.invoke(ingridients.data, searchString, 3)
        assertTrue(list3.isEmpty())
    }

    @Test
    fun `when search string is found`(){
        val searchString = "rice"
        val list1= mapper.invoke(ingridients.data, searchString, 1)
        assertTrue(list1.isNotEmpty())
        val list2= mapper.invoke(ingridients.data, searchString, 2)
        assertTrue(list2.isNotEmpty())
        val list3= mapper.invoke(ingridients.data, searchString, 3)
        assertTrue(list3.isNotEmpty())
    }
}
package com.example.orders.ext

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Calendar

class DateTest {
    @Test
    fun `string to long`(){
        val value = 1628503200000L
        val actual = "2021-08-09T15:30+00Z".toLongDate()
        assertEquals(value, actual)
    }

    @Test
    fun `calculate difference in minutes`(){
        val calender = Calendar.getInstance()
        calender.add(Calendar.MINUTE, 10)
        val difference = calculateDifferenceInMinutes(calender.timeInMillis)
        assertEquals(difference, 10)
    }

    @Test
    fun `Full string to time string`(){
        val actual = "2021-08-09T15:30+00Z"
        val value = "03:30 PM"
        val expected = actual.toTime()
        assertEquals(value, expected)
    }
}
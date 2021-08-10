package com.example.orders

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.mockito.Mockito
import java.io.InputStreamReader

/**
 * Reads JSON object and converts it to object
 */
inline fun <reified T> parseResource(path: String, gson: Gson = Gson()): T {
    val obj = object : TypeToken<T>() {}.type

    return gson.fromJson(
        InputStreamReader(T::class.java.classLoader!!.getResourceAsStream(path)),
        obj
    )
}

/**
 * Create mocks of a type casted Generic Java object
 */
inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

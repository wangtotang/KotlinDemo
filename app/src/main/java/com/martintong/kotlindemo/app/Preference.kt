package com.martintong.kotlindemo.app

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
🐶 @author  Martin_Tong
🐶 @date    2017/6/16
🐶
🐶 @apiNote
 */
class Preference<T>(val context: Context) : ReadWriteProperty<Any?, T> {

    var spName: String? = null
    var key: String? = null
    var defaultValue: T? = null
    val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(spName ?: "default", Context.MODE_PRIVATE)
    }

    constructor(context: Context, vararg str: String) : this(context) {
        when {
            str.size == 1 -> spName = str[0]
            str.size == 2 -> {
                spName = str[0]
                key = str[1]
            }
        }
    }

    constructor(context: Context, vararg str: String, default: T) : this(context) {
        when {
            str.size == 1 -> spName = str[0]
            str.size == 2 -> {
                spName = str[0]
                key = str[1]
            }
        }
        defaultValue = default
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(key ?: property.name, property.returnType.toString())
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(key ?: property.name, value)
    }

    fun delete(vararg key: String): Unit {
        when (key.size) {
            0 -> prefs.edit().clear().commit()
            else -> for (i in 0 until key.size) prefs.edit().remove(key[i]).commit()
        }
    }

    private fun findPreference(name: String, type: String): T = with(prefs) {
        val res: Any = when (type) {
            "kotlin.Long" -> getLong(name, defaultValue as? Long ?: 0L)
            "kotlin.String" -> getString(name, defaultValue as? String ?: "")
            "kotlin.Int" -> getInt(name, defaultValue as? Int ?: 0)
            "kotlin.Boolean" -> getBoolean(name, defaultValue as? Boolean ?: false)
            "kotlin.Float" -> getFloat(name, defaultValue as? Float ?: 0f)
            else -> throw IllegalArgumentException("The data can not be saved")
        }
        res as T
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("The data can not be saved")
        }.commit()
    }
}
package com.example.mynewsapp.data.local

import androidx.room.TypeConverter
import com.example.mynewsapp.data.remote.entity.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(string: String): Source {
        return Source(string, string)
    }
}
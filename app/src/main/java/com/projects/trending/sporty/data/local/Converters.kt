package com.projects.trending.sporty.data.local

import androidx.room.TypeConverter
import com.projects.trending.sporty.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}
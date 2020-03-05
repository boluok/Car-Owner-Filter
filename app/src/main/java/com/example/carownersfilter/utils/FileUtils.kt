package com.example.carownersfilter.utils

import android.content.Context
import java.io.File

object FileUtils {



    fun getFile(context: Context,filePath:String):File{
        val path = context.filesDir.absolutePath
        val file = File("$path/${"decagon/car_ownsers_data.csv"}")
        return file
    }
}
//"$path/decagon/car_ownsers_data.csv"
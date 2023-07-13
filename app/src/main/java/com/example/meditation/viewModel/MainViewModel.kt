package com.example.meditation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainViewModel(): ViewModel() {
    var progress: Int = 0

    fun saveProgressToFile (context: Context) {
        val fileName = "data.txt"

        try {
            val fileOutputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val outputStreamWriter = OutputStreamWriter(fileOutputStream)
            outputStreamWriter.write(progress.toString())
            outputStreamWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readProgressFromFile(context: Context) {
        val fileName = "data.txt"

        try {
            val fileInputStream: FileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val progressString = bufferedReader.readLine()
            bufferedReader.close()
            progress = progressString.toIntOrNull() ?: 0
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}


package com.castrorr.shoppingcart.features.common.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class FileUtils {

    companion object {
        private const val TAG = "FileUtils"
        fun createFile(context: Context?, fileName: String, fileExt: String): File {
            val storageDir = context?.filesDir?.absolutePath.toString() + File.separator.toString()
            val file = File(storageDir)
            if(!file.exists())
                file.mkdir()
            return File(file, "$fileName.$fileExt")
        }

        fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
            inputStream.use { input ->
                val outputStream = FileOutputStream(outputFile)
                outputStream.use { output ->
                    val buffer = ByteArray(4 * 1024)
                    while (true) {
                        val byteCount = input.read(buffer)
                        if (byteCount < 0) break
                        output.write(buffer,0, byteCount)
                    }
                    output.flush()
                    output.close()
                }
            }
        }
    }
}
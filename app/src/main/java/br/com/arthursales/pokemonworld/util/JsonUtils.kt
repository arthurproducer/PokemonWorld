package br.com.arthursales.pokemonworld.util

import android.content.Context
import br.com.arthursales.pokemonworld.model.TeamsResponse
import java.io.IOException
import com.google.gson.Gson
import java.io.InputStream

class JsonUtils {
    companion object {
        fun inputStreamToString(inputStream: InputStream): String {
            try {
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes, 0, bytes.size)
                return String(bytes)
            } catch (e: IOException) {
                return ""
            }
        }
    }
}

fun Context.getObjectFromJson(jsonFileName: String): TeamsResponse {
    val myJson =
        JsonUtils.inputStreamToString(
            this.assets.open(jsonFileName)
        )
    return Gson().fromJson(myJson, TeamsResponse::class.java)
}

@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    val inputStream = assets.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}

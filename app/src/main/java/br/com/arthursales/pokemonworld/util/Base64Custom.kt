package br.com.arthursales.pokemonworld.util

import android.util.Base64


object Base64Custom {

    fun codificarBase64(texto: String): String {
        return Base64.encodeToString(texto.toByteArray(), Base64.DEFAULT).replace(
            "(\\n|\\r)".toRegex(),
            ""
        ) // Criptografa, o replaceAll é para que não conte quebras de linhas ou caracteres indesejados
    }

    fun decodificarBase64(textocodificado: String): String {
        return String(
            Base64.decode(
                textocodificado,
                Base64.DEFAULT
            )
        ) //A String aqui serve para converter o valor de bytes, pois o método requer uma string
    }
}
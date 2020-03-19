package br.com.arthursales.pokemonworld.view.listFavoritePokemon

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import android.view.View
import br.com.arthursales.pokemonworld.model.*
import br.com.arthursales.pokemonworld.repository.SQLRepository
import br.com.arthursales.pokemonworld.sqlite.*
import kotlinx.android.synthetic.main.activity_details_pokemon.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.repository.PokemonRepository
import br.com.arthursales.pokemonworld.sqlite.PokemonSqlHelper.Companion.SQL_CREATE_ENTRIES

class ListFavoritePokemonViewModel : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val listFavoritePokemon = MutableLiveData<MutableList<PokemonDetails?>>()
    val pokemon = MutableLiveData<PokemonDetails?>()
    val messageError = MutableLiveData<String>()

    fun showSQLLite(context: Context): Cursor? {
        isLoading.value = true
        val helper = PokemonSqlHelper(context)
        var cursor: Cursor? = null
        listFavoritePokemon.value = mutableListOf()
        var sql = "SELECT * FROM $TABLE_POKEMON"
        val db = helper.readableDatabase
        try {
            cursor = db.rawQuery(sql, null)
        }catch (e : SQLiteException){
            messageError.value = e.toString()
            db.execSQL(SQL_CREATE_ENTRIES)
        }
        if(cursor != null){
        while (cursor.moveToNext()){
            pokemon.value = pokemonFromCursor(cursor)
            listFavoritePokemon.value?.add(pokemon.value)
        } }else{
            //TODO tratar lista vazia
        }
        isLoading.value = false
        cursor?.close()
        db.close()

        return cursor
    }

    private fun pokemonFromCursor(cursor: Cursor) : PokemonDetails{

        var spritesResponse =SpritesResponse()

            val id = cursor.getInt(
                cursor.getColumnIndex(COLUMN_ID))
            val name = cursor.getString(
                cursor.getColumnIndex(COLUMN_NAME))
            spritesResponse.front_default = cursor.getString(
                cursor.getColumnIndex(COLUMN_SPRITE_FRONT_DEFAULT)
            )

        return PokemonDetails(id,name,spritesResponse)
    }

}
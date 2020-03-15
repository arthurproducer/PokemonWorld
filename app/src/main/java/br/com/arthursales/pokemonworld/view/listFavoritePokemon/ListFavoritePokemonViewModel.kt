package br.com.arthursales.pokemonworld.view.listFavoritePokemon

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonDetails
import br.com.arthursales.pokemonworld.model.SpritesResponse
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_ID
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_NAME
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_SPRITE_FRONT_DEFAULT
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.COLUMN_USER_ID
import br.com.arthursales.pokemonworld.sqlite.DBPokemonWorld.TABLE_POKEMON
import br.com.arthursales.pokemonworld.sqlite.PokemonSqlHelper
import br.com.arthursales.pokemonworld.sqlite.PokemonSqlHelper.Companion.SQL_CREATE_ENTRIES

class ListFavoritePokemonViewModel : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val listFavoritePokemon = MutableLiveData<MutableList<PokemonDetails?>>()
    val pokemon = MutableLiveData<PokemonDetails?>()
    val messageError = MutableLiveData<String>()

    fun showSQLLite(id: Long?,context: Context): Cursor? {
        val helper = PokemonSqlHelper(context)
        var cursor: Cursor? = null
        var args : Array<String?>?
        listFavoritePokemon.value = mutableListOf()
        var sql = "SELECT * FROM $TABLE_POKEMON"
            sql += " WHERE $COLUMN_USER_ID LIKE ?"
            args = arrayOf(id.toString())

        val db = helper.readableDatabase
        try {
            cursor = db.rawQuery(sql, args)
            Log.i("BDSucess", sql)
            Log.i("BDSucess", cursor.toString())
        }catch (e : SQLiteException){
            db.execSQL(SQL_CREATE_ENTRIES)
            Log.i("BDError", sql)
        }
        if(cursor != null){
        while (cursor.moveToNext()){
            pokemon.value = pokemonFromCursor(cursor)
            listFavoritePokemon.value?.add(pokemon.value)
          } }else{
            //TODO tratar lista vazia
        }
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
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
        val helper = PokemonSqlHelper(context)
        var cursor: Cursor? = null
        listFavoritePokemon.value = mutableListOf()
        var sql = "SELECT * FROM $TABLE_POKEMON"
        val db = helper.readableDatabase
        try {
            cursor = db.rawQuery(sql, null)
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
            //listFavoritePokemon.value.a = mutableListOf(pokemonFromCursor(cursor))
            Log.i("FAVORITEPOKEMONin",listFavoritePokemon.value.toString())
            Log.i("FAVORITEPOKEMONNAMEin",listFavoritePokemon.value?.get(0)?.name)
        } }else{
            //TODO tratar lista vazia
        }
        cursor?.close()
        db.close()
        Log.i("FAVORITEPOKEMON",listFavoritePokemon.value.toString())
        Log.i("FAVORITEPOKEMONNAME",listFavoritePokemon.value?.get(0)?.name)

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
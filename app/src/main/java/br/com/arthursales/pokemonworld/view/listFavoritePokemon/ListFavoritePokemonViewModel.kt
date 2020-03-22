package br.com.arthursales.pokemonworld.view.listFavoritePokemon

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.PokemonDetails
import br.com.arthursales.pokemonworld.model.SpritesResponse
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_ID
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_NAME
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_SPRITE_FRONT_DEFAULT
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_USER_ID
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.TABLE_POKEMON
import br.com.arthursales.pokemonworld.sqlite.PokemonSqlHelper
import br.com.arthursales.pokemonworld.sqlite.PokemonSqlHelper.Companion.SQL_CREATE_ENTRIES
import br.com.arthursales.pokemonworld.util.SQL.SQL_LIKE
import br.com.arthursales.pokemonworld.util.SQL.SQL_WHERE

class ListFavoritePokemonViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val listFavoritePokemon = MutableLiveData<MutableList<PokemonDetails?>>()
    val pokemon = MutableLiveData<PokemonDetails?>()
    val messageError = MutableLiveData<String>()

        fun showSQLLite(id: Long?, context: Context): Cursor? {
            isLoading.value = true
            val helper = PokemonSqlHelper(context)
            var cursor: Cursor? = null
            val args: Array<String?>?
            listFavoritePokemon.value = mutableListOf()
            var sql = "SELECT * FROM $TABLE_POKEMON"
            sql += " $SQL_WHERE $COLUMN_USER_ID $SQL_LIKE ?"
            args = arrayOf(id.toString())

            val db = helper.readableDatabase
            try {
                cursor = db.rawQuery(sql, args)
            } catch (e: SQLiteException) {
                messageError.value = e.toString()
                db.execSQL(SQL_CREATE_ENTRIES)
            }
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    pokemon.value = pokemonFromCursor(cursor)
                    listFavoritePokemon.value?.add(pokemon.value)
                }
            } else {
                //TODO tratar lista vazia
                isLoading.value = false
            }
            isLoading.value = false
            cursor?.close()
            db.close()

            return cursor
        }

    private fun pokemonFromCursor(cursor: Cursor): PokemonDetails {

        val spritesResponse = SpritesResponse()

        val id = cursor.getInt(
            cursor.getColumnIndex(COLUMN_ID)
        )
        val name = cursor.getString(
            cursor.getColumnIndex(COLUMN_NAME)
        )
        spritesResponse.front_default = cursor.getString(
            cursor.getColumnIndex(COLUMN_SPRITE_FRONT_DEFAULT)
        )

        return PokemonDetails(id, name, spritesResponse)
    }
}
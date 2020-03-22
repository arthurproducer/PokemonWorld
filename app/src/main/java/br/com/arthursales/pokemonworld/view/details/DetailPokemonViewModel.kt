package br.com.arthursales.pokemonworld.view.details

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.*
import br.com.arthursales.pokemonworld.repository.PokemonRepository
import br.com.arthursales.pokemonworld.sqlite.*
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_ID
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_NAME
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_SPRITE_FRONT_DEFAULT
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_ATTACK
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_DEFENSE
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_HP
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPATTACK
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPDEFENSE
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_STATS_SPEED
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_TYPE_1
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_TYPE_2
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.COLUMN_USER_ID
import br.com.arthursales.pokemonworld.util.DBPokemonWorld.TABLE_POKEMON
import br.com.arthursales.pokemonworld.util.Constants.ATTACK
import br.com.arthursales.pokemonworld.util.Constants.DEFENSE
import br.com.arthursales.pokemonworld.util.Constants.HP
import br.com.arthursales.pokemonworld.util.Constants.SPEED
import br.com.arthursales.pokemonworld.util.Constants.SP_ATK
import br.com.arthursales.pokemonworld.util.Constants.SP_DEF
import br.com.arthursales.pokemonworld.util.Keys.PREFERENCES_USER_ID
import br.com.arthursales.pokemonworld.util.SQL
import br.com.arthursales.pokemonworld.util.SQL.SQL_LIKE
import br.com.arthursales.pokemonworld.util.SQL.SQL_WHERE
import kotlinx.android.synthetic.main.activity_details_pokemon.*
import kotlinx.android.synthetic.main.pokemon_list_item.*

class DetailPokemonViewModel(
    private val pokemonRepository: PokemonRepository,
    private val preferences: SharedPreferences
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()
    val pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonTypes = MutableLiveData<MutableList<TypesResponse>>()
    val pokemonStats = MutableLiveData<MutableList<StatsResponse>>()

    fun getPokemonDetails(id: String) {
        isLoading.value = true
        pokemonRepository.getPokemonDetails(id,
            {
                pokemonDetails.value = it
                pokemonTypes.value = it?.types?.toMutableList()
                pokemonStats.value = it?.stats?.toMutableList()
                messageResponse.value = ""
                isLoading.value = false
            }, {
                messageResponse.value = it?.message
                isLoading.value = false
            }
        )
    }

    fun insertSQLLite(id: Int, context: Context) {
        val helper = PokemonSqlHelper(context)

        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_ID, id)
            put(COLUMN_NAME, pokemonDetails.value?.name)
            put(COLUMN_USER_ID, preferences.getLong(PREFERENCES_USER_ID, 0))
            put(COLUMN_TYPE_1, getType(pokemonTypes.value, 1))
            put(COLUMN_TYPE_2, getType(pokemonTypes.value, 2))
            put(COLUMN_STATS_HP, getStats(pokemonStats.value, HP))
            put(COLUMN_STATS_ATTACK, getStats(pokemonStats.value, ATTACK))
            put(COLUMN_STATS_DEFENSE, getStats(pokemonStats.value, DEFENSE))
            put(COLUMN_STATS_SPATTACK, getStats(pokemonStats.value, SP_ATK))
            put(COLUMN_STATS_SPDEFENSE, getStats(pokemonStats.value, SP_DEF))
            put(COLUMN_STATS_SPEED, getStats(pokemonStats.value, SPEED))
            put(COLUMN_SPRITE_FRONT_DEFAULT, pokemonDetails.value?.sprites?.front_default)
        }
        db.insert(TABLE_POKEMON, null, cv)
        db.close()
    }

    fun removeFromSQLLite(vararg pokemonDetails: PokemonDetails, context: Context) {
        val helper = PokemonSqlHelper(context)
        val db = helper.writableDatabase
        for (pokemon in pokemonDetails) {
            db.delete(
                TABLE_POKEMON,
                "$COLUMN_ID = ?",
                arrayOf(pokemon.id.toString())
            )
        }
        db.close()
    }

    fun showSQLLite(id: Int, context: Context): Cursor? {
        val helper = PokemonSqlHelper(context)
        var cursor: Cursor? = null
        pokemonTypes.value = mutableListOf()
        pokemonStats.value = mutableListOf()

        var sql = "SELECT * FROM $TABLE_POKEMON"
        sql += " $SQL_WHERE $COLUMN_ID $SQL_LIKE ?"
        val args = arrayOf(id.toString())

        val db = helper.readableDatabase
        try {
            cursor = db.rawQuery(sql, args)
        } catch (e: SQLiteException) {
            messageResponse.value = e.toString()
        }
        if (cursor != null) {
            while (cursor.moveToNext()){
                pokemonDetails.value = pokemonFromCursor(cursor)
            }
        } else {
            //TODO tratar lista vazia
        }
        cursor?.close()
        db.close()

        return cursor
    }

    private fun pokemonFromCursor(cursor: Cursor) :PokemonDetails{
        val spritesResponse = SpritesResponse()
        val typesResponse = TypesResponse()

        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
        val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
        spritesResponse.front_default =
            cursor.getString(cursor.getColumnIndex(COLUMN_SPRITE_FRONT_DEFAULT))

        val pokemon = PokemonDetails(id,name,spritesResponse)


        setType(1,cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_1)))
        setType(2,cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_2)))
        pokemon.types = pokemonTypes.value

        setStats(HP, cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_HP)))
        setStats(ATTACK, cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_ATTACK)))
        setStats(DEFENSE, cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_DEFENSE)))
        setStats(SP_ATK, cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_SPATTACK)))
        setStats(SP_DEF, cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_SPDEFENSE)))
        setStats(SPEED, cursor.getInt(cursor.getColumnIndex(COLUMN_STATS_SPEED)))
        pokemon.stats = pokemonStats.value

        return pokemon

    }

    private fun setType(slot: Int, name: String) {
        val typesResponse = TypesResponse()

        if(name != ""){
            typesResponse.slot = slot
            typesResponse.type.name = name
        }

        pokemonTypes.value?.add(typesResponse)

    }

    private fun getType(listOfTypesResponse: List<TypesResponse>?, slot: Int): String? {
        var type: String?
        type = ""
        listOfTypesResponse?.forEach { typeResponse ->
            if (typeResponse.slot == slot) {
                when (typeResponse.slot) {
                    1 ->
                        type = typeResponse.type.name
                    2 -> {
                        type = typeResponse.type.name
                    }
                }
            }
        }
        return type
    }

    private fun setStats(name: String, base_stats: Int) {
        val statsResponse = StatsResponse()

        statsResponse.stat.name = name
        statsResponse.base_stat = base_stats

        pokemonStats.value?.add(statsResponse)
    }

    private fun getStats(listOfStatsResponse: List<StatsResponse>?, name: String): Int {
        var baseStats = 0
        listOfStatsResponse?.forEach { statsPokemon ->
            if (statsPokemon.stat.name.equals(name)) {
                when (statsPokemon.stat.name) {
                    HP -> {
                        baseStats = statsPokemon.base_stat
                    }
                    ATTACK -> {
                        baseStats = statsPokemon.base_stat
                    }
                    DEFENSE -> {
                        baseStats = statsPokemon.base_stat
                    }
                    SP_ATK -> {
                        baseStats = statsPokemon.base_stat
                    }
                    SP_DEF -> {
                        baseStats = statsPokemon.base_stat
                    }
                    SPEED -> {
                        baseStats = statsPokemon.base_stat
                    }
                }
            }
        }
        return baseStats
    }
}
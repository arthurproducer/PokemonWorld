package br.com.arthursales.pokemonworld.view.details

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.arthursales.pokemonworld.model.*
import br.com.arthursales.pokemonworld.repository.PokemonRepository
import br.com.arthursales.pokemonworld.sqlite.*

class DetailPokemonViewModel(
    val pokemonRepository: PokemonRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()
    val pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonTypes = MutableLiveData<List<TypesResponse>>()
    val pokemonStats = MutableLiveData<List<StatsResponse>>()

    fun getPokemonDetails(id: String) {
        isLoading.value = true
        pokemonRepository.getPokemonDetails(id,
            {
                pokemonDetails.value = it
                pokemonTypes.value = it?.types
                pokemonStats.value = it?.stats
                messageResponse.value = ""
                isLoading.value = false
            }, {
                messageResponse.value = it?.message
                isLoading.value = false
            }
        )
    }

    fun insertSQLLite(id: String,context: Context){
        val helper = PokemonSqlHelper(context)

        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_ID,id.toInt())
            put(COLUMN_NAME,pokemonDetails.value?.name)
            put(COLUMN_TYPE_1,getType(pokemonTypes.value,1))
            put(COLUMN_TYPE_2,getType(pokemonTypes.value,2))
            put(COLUMN_STATS_HP,getStats(pokemonStats.value,"hp"))
            put(COLUMN_STATS_ATTACK,getStats(pokemonStats.value,"attack"))
            put(COLUMN_STATS_DEFENSE,getStats(pokemonStats.value,"defense"))
            put(COLUMN_STATS_SPATTACK,getStats(pokemonStats.value,"special-attack"))
            put(COLUMN_STATS_SPDEFENSE,getStats(pokemonStats.value,"special-defense"))
            put(COLUMN_STATS_SPEED,getStats(pokemonStats.value,"speed"))
            put(COLUMN_SPRITE_FRONT_DEFAULT,pokemonDetails.value?.sprites?.front_default)
        }
        db.insert(TABLE_POKEMON,null,cv)
        db.close()
    }

    fun removeFromSQLLite(vararg pokemonDetails: PokemonDetails,context: Context){
        val helper = PokemonSqlHelper(context)
        val db = helper.writableDatabase
        for(pokemon in pokemonDetails){
            db.delete(
                TABLE_POKEMON,
                "$COLUMN_ID = ?",
                arrayOf(pokemon.id.toString())
            )
        }
        db.close()
    }

    fun showSQLLite(id: String,context: Context): Cursor{
        val helper = PokemonSqlHelper(context)
        var sql = "SELECT * FROM $TABLE_POKEMON"
        sql += " $COLUMN_ID = ?"
        val args = arrayOf(id)

        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql,args)

        db.close()
        return cursor
    }


    private fun getType(listOfTypesResponse: List<TypesResponse>?, slot : Int): String{
        var type = ""
        listOfTypesResponse?.forEach {typeResponse ->
        if(typeResponse.slot == slot){
                when(typeResponse.slot){
                        1 ->
                    type = typeResponse.type.name
                        2 -> {
                    type = typeResponse.type.name
                }
        } } }
        return type
    }

    private fun getStats(listOfStatsResponse: List<StatsResponse>?, name : String): Int{
        var baseStats = 0
        listOfStatsResponse?.forEach {statsPokemon ->
            if(statsPokemon.stat.name.equals(name)){
                when(statsPokemon.stat.name){
                    "hp" -> {
                        baseStats = statsPokemon.base_stat
                    }
                    "attack" -> {
                        baseStats = statsPokemon.base_stat
                    }
                    "defense" -> {
                        baseStats = statsPokemon.base_stat
                    }
                    "special-attack" ->{
                        baseStats = statsPokemon.base_stat
                    }
                    "special-defense" -> {
                        baseStats =  statsPokemon.base_stat
                    }
                    "speed" -> {
                        baseStats =  statsPokemon.base_stat
                    }
                }}}
        return baseStats
    }

    fun updatePokemon(pokemon: Pokemon) {
        isLoading.value = true
        pokemonRepository.updatePokemon(
            pokemon = pokemon,
            onComplete = {
                isLoading.value = false
                messageResponse.value = "Dados atualizados com sucesso"
            },
            onError = {
                isLoading.value = false
                messageResponse.value = it.message
            }
        )
    }
}
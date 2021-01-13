package br.com.arthursales.pokemonworld.repository

import android.content.Context
import br.com.arthursales.pokemonworld.model.PokemonTeams
import br.com.arthursales.pokemonworld.model.PokemonTeamsResponseData
import br.com.arthursales.pokemonworld.util.Constants
import br.com.arthursales.pokemonworld.util.JsonUtils
import br.com.arthursales.pokemonworld.util.getObjectFromJson
import com.google.gson.Gson


class PokemonTeamsRepositoryImpl(val context: Context) : PokemonTeamsRepository {

    override fun getPokemonTeams(): List<PokemonTeams> {
        val teamsList = context.
            getObjectFromJson(jsonFileName = Constants.JSON_TEAMS_ALPHA_SAPPHIRE)
        return teamsList.gamesListAlphaSapphire
    }
}



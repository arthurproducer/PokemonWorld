package br.com.arthursales.pokemonworld.repository

import android.content.Context
import br.com.arthursales.pokemonworld.model.PokemonTeams
import br.com.arthursales.pokemonworld.util.Constants
import br.com.arthursales.pokemonworld.util.getObjectFromJson


class PokemonTeamsRepositoryImpl(val context: Context) : PokemonTeamsRepository {

    override fun getAllPokemonTeams(): ArrayList<PokemonTeams> {
        val gamesList = context.
            getObjectFromJson(jsonFileName = Constants.JSON_TEAMS)

        //var teamsList = arrayListOf<PokemonTeams>()
//        var teamsList = arrayListOf<GamesTeamsResponse>()


//        var diamond = GamesTeamsResponse("Diamond",gamesList.games)
//        var platinum = TeamsResponse("Platinum",gamesList.games)
//        var heartgold =TeamsResponse("HeartGold",gamesList.games.listHeartGold.teams)
//        var black = TeamsResponse("Black",gamesList.games.listBlack.teams)
//        var black2 = TeamsResponse("Black 2",gamesList.games.listBlack2.teams)

//        teamsList.add(diamond)
//        teamsList.add(platinum)
//        teamsList.add(heartgold)
//        teamsList.add(black)
//        teamsList.addAll(gamesList.games)

        return gamesList.teams
    }
}



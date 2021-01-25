package br.com.arthursales.pokemonworld.view.teams

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonTeams
import com.airbnb.lottie.parser.ColorParser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_cardview_teams.view.*


class PokemonTeamsAdapter(
    private val pokemonTeams: List<PokemonTeams>,
    private val picasso: Picasso) : RecyclerView.Adapter<VHPokemonTeams>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHPokemonTeams {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_cardview_teams, parent, false)
        return VHPokemonTeams(v)
    }

    override fun getItemCount(): Int = pokemonTeams.size

    override fun onBindViewHolder(holder: VHPokemonTeams, position: Int) {
        val subAdapter = TeamPokemonAdapter(pokemonTeams = pokemonTeams[position],picasso = picasso)
        val teamPokemon = pokemonTeams[position]
        val viewPool = RecycledViewPool()
        holder.bindView(teamPokemon, picasso, viewPool, subAdapter)
    }

}

class VHPokemonTeams(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(
        pokemonTeams: PokemonTeams,
        picasso: Picasso,
        viewPool: RecycledViewPool,
        subAdapter: TeamPokemonAdapter) = with(itemView) {
        //                             imgProduct.setImageResource(product.photo)
//        picasso.load("https://pokedexdx.herokuapp.com${product.photo}").into(ivPokemon)

        //TODO Utilizar RecyclerViewPool para incluir uma recycler view dentro de outra
        val layoutManager = GridLayoutManager(
            itemView.rvTeamPokemon.context, 3)
        layoutManager.initialPrefetchItemCount = pokemonTeams.pokemons.size

        rvTeamPokemon.layoutManager = layoutManager
        rvTeamPokemon.adapter = subAdapter
        rvTeamPokemon.setRecycledViewPool(viewPool)

//        materialCardView.setCardBackgroundColor(Color.parseColor(pokemonTeams.color))

        materialCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorAlphaSapphire))
        txtTier.text = pokemonTeams.tier
        txtTeamName.text = pokemonTeams.name

    }
}


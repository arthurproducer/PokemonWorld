package br.com.arthursales.pokemonworld.view.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonTeams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon_teams.view.*

class TeamPokemonAdapter(
    private val pokemonTeams: PokemonTeams,
    private val picasso: Picasso
): RecyclerView.Adapter<TeamPokemonAdapter.VHTeamPokemon>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHTeamPokemon {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_teams,
            parent, false)
        return VHTeamPokemon(v)
    }

    override fun getItemCount(): Int = pokemonTeams.pokemons.size

    override fun onBindViewHolder(holder: VHTeamPokemon, position: Int) {
        val pokemon = pokemonTeams.pokemons[position]
        picasso.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/${pokemon.number}.png")
            .into(holder.ivPoke)

        holder.tvName.text = pokemon.name
        holder.tvPokemonNumber.text = pokemon.number.toString()
    }

    inner class VHTeamPokemon(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.tvPokemonName
        val tvPokemonNumber: TextView = itemView.tvPokemonNumber
        val ivPoke: ImageView = itemView.ivPokemon
    }
}


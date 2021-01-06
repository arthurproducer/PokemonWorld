package br.com.arthursales.pokemonworld.view.listpokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_list_item.view.*


class ListPokemonAdapter(
    private val pokemons: List<PokemonGenericResponse>,
    private val picasso: Picasso,
    private val clickListener: (PokemonGenericResponse) -> Unit
) : RecyclerView.Adapter<ListPokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(view)
    }
    override fun getItemCount(): Int {
        return pokemons.size
    }
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bindView(pokemon, picasso, clickListener)
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pokemon: PokemonGenericResponse,
                     picasso: Picasso,
                     clickListener: (PokemonGenericResponse) -> Unit) = with(itemView) {
            val number = pokemon.url.substringAfter("pokemon/").substringBefore('/')
            tvPokemonNumber.text = number
            tvPokemonName.text = pokemon.name
//            if(!pokemon.name.substringAfter('-',"").isNullOrEmpty()) {
//                number += '-' + pokemon.name.substringAfter('-')
//            }//TODO Tratar diferentes formas

            picasso.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png").into(ivPokemon)
            setOnClickListener { clickListener(pokemon) }
        }
    }
}
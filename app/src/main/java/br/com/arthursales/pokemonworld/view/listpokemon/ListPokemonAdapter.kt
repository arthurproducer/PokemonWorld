package br.com.arthursales.pokemonworld.view.listpokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonGenericResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_list_item.view.*


class ListPokemonsAdapter(
    private val listPokemon: List<PokemonGenericResponse>,
    private val picasso: Picasso,
    private val clickListener: (PokemonGenericResponse) -> Unit
) : RecyclerView.Adapter<ListPokemonsAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(view)
    }
    override fun getItemCount(): Int {
        return listPokemon.size
    }
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = listPokemon[position]
        holder.bindView(pokemon,position+1, picasso, clickListener)
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pokemon: PokemonGenericResponse,
                     position: Int,
                     picasso: Picasso,
                     clickListener: (PokemonGenericResponse) -> Unit) = with(itemView) {
            tvPokemonName.text = pokemon.name
            tvPokemonNumber.text = position.toString()
            picasso.load(pokemon.url).into(ivPokemon)

            setOnClickListener { clickListener(pokemon) }
        }
    }
}
package br.com.arthursales.pokemonworld.view.listFavoritePokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_list_item.view.*


class ListFavoritePokemonAdapter(
    private val pokemons: List<PokemonDetails?>,
    private val picasso: Picasso,
    private val clickListener: (PokemonDetails?) -> Unit
) : RecyclerView.Adapter<ListFavoritePokemonAdapter.FavoritePokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return FavoritePokemonViewHolder(view)
    }
    override fun getItemCount(): Int {
        return pokemons.size
    }
    override fun onBindViewHolder(holder: FavoritePokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bindView(pokemon, picasso, clickListener)
    }

    class FavoritePokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pokemon: PokemonDetails?,
                     picasso: Picasso,
                     clickListener: (PokemonDetails?) -> Unit) = with(itemView) {
            tvPokemonName.text = pokemon?.name
            tvPokemonNumber.text = pokemon?.id.toString()
            picasso.load(pokemon?.sprites?.front_default).into(ivPokemon)

            setOnClickListener { clickListener(pokemon) }
        }
    }
}
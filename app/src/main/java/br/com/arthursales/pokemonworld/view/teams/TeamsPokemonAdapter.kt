package br.com.arthursales.pokemonworld.view.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonTeams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_cardview_teams.view.*

class TeamsPokemonAdapter(
    private val pokemonTeams: List<PokemonTeams>,
    private val picasso: Picasso ) : RecyclerView.Adapter<VHTeamsPokemon>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHTeamsPokemon {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_cardview_teams, parent, false)
        val vh = VHTeamsPokemon(v)

        return vh
    }

    override fun getItemCount(): Int = pokemonTeams.size

    override fun onBindViewHolder(holder: VHTeamsPokemon, position: Int) {
        val lendingProduct = pokemonTeams[position]
        holder.bindView(lendingProduct, picasso)
    }

}

class VHTeamsPokemon(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    lateinit var itemLongClickListener : ItemLongClickListener

    fun bindView(
        pokemonTeams: PokemonTeams,
        picasso: Picasso ) = with(itemView) {
        //                             imgProduct.setImageResource(product.photo)
//        picasso.load("https://pokedexdx.herokuapp.com${product.photo}").into(ivPokemon)

        txtTier.text = pokemonTeams.tier
        txtTeamName.text = pokemonTeams.name

    }
}


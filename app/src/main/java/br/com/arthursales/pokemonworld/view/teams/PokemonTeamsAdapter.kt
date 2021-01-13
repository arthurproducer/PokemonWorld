package br.com.arthursales.pokemonworld.view.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonTeams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_cardview_teams.view.*


class PokemonTeamsAdapter(
    private val pokemonTeams: List<PokemonTeams>,
    private val picasso: Picasso ) : RecyclerView.Adapter<VHPokemonTeams>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHPokemonTeams {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_cardview_teams, parent, false)
        return VHPokemonTeams(v)
    }

    override fun getItemCount(): Int = pokemonTeams.size

    override fun onBindViewHolder(holder: VHPokemonTeams, position: Int) {
        val pokemonTeams = pokemonTeams[position]
        val viewPool = RecycledViewPool()
        holder.bindView(pokemonTeams, picasso, viewPool)
    }

}

class VHPokemonTeams(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    lateinit var itemLongClickListener : ItemLongClickListener

    fun bindView(
        pokemonTeams: PokemonTeams,
        picasso: Picasso,
        viewPool: RecycledViewPool) = with(itemView) {
        //                             imgProduct.setImageResource(product.photo)
//        picasso.load("https://pokedexdx.herokuapp.com${product.photo}").into(ivPokemon)

        // Create layout manager with initial prefetch item count
        // Create layout manager with initial prefetch item count
        //TODO Utilizar RecyclerViewPool para incluir uma recycler view dentro de outra
        val layoutManager = LinearLayoutManager(
            itemView.rvTeamPokemon.context,
            LinearLayoutManager.VERTICAL,
            false
        )
//        layoutManager.initialPrefetchItemCount = pokemonTeams.getSubItemList().size() //TODO get SubItem Size
//
//        // Create sub item view adapter
//        // Create sub item view adapter
//        val subItemAdapter = ListFavoritePokemonAdapter()
//
//        itemView.rvTeamPokemon.layoutManager = layoutManager;
//        itemView.rvTeamPokemon.adapter = subItemAdapter;
//        itemView.rvTeamPokemon.setRecycledViewPool(viewPool);

        txtTier.text = pokemonTeams.tier
        txtTeamName.text = pokemonTeams.name

    }
}


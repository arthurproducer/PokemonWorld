package br.com.arthursales.pokemonworld.view.teams

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonTeams
import br.com.arthursales.pokemonworld.view.listFavoritePokemon.ListFavoritePokemonAdapter
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
        val pokemonTeams = pokemonTeams[position]
        val viewPool = RecycledViewPool()
        holder.bindView(pokemonTeams, picasso, viewPool)
    }

}

class VHTeamsPokemon(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
//        val layoutManager = LinearLayoutManager(
//            itemView.rvTeamPokemon.context,
//            LinearLayoutManager.VERTICAL,
//            false
//        )
//        layoutManager.initialPrefetchItemCount = item.getSubItemList().size()
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


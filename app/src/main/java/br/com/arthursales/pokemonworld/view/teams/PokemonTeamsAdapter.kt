package br.com.arthursales.pokemonworld.view.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.PokemonTeams
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

        //TODO tratar segundo position
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

        //TODO Utilizar RecyclerViewPool para incluir uma recycler view dentro de outra
        val layoutManager = GridLayoutManager(
            itemView.rvTeamPokemon.context, 3)
        layoutManager.initialPrefetchItemCount = pokemonTeams.pokemons.size

        rvTeamPokemon.layoutManager = layoutManager
        rvTeamPokemon.adapter = subAdapter
        rvTeamPokemon.setRecycledViewPool(viewPool)

        when (pokemonTeams.game) {
            "Diamond" -> {
                imgGameLogo.setBackgroundResource(R.drawable.diamondlogo)
                materialCardView.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.colorDiamond))
                txtTeamName.setTextColor(ContextCompat.getColor(context, R.color.colorDiamondSecundary))
                txtTier.setTextColor(ContextCompat.getColor(context,R.color.colorDiamondTertiary))

            }
            "Platinum" -> {
                imgGameLogo.setBackgroundResource(R.drawable.platinumlogo)
                materialCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context, R.color.colorPlatinum))
                txtTeamName.setTextColor(ContextCompat.getColor(context, R.color.colorPlatinumSecundary))
                txtTier.setTextColor(ContextCompat.getColor(context,R.color.colorPlatinumTertiary))
            }
            "HeartGold" ->{
                imgGameLogo.setBackgroundResource(R.drawable.heartgoldlogo)
                materialCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorHeartGold))
                txtTeamName.setTextColor(ContextCompat.getColor(context, R.color.colorHeartGoldSecundary))
                txtTier.setTextColor(ContextCompat.getColor(context,R.color.colorHeartGoldTertiary))
            }
            "Black" ->{
                imgGameLogo.setBackgroundResource(R.drawable.blacklogo)
                materialCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorBlack))
                txtTeamName.setTextColor(ContextCompat.getColor(context, R.color.colorBlackSecundary))
                txtTier.setTextColor(ContextCompat.getColor(context,R.color.colorBlackPokeball))
            }
            "Black 2" -> {
                imgGameLogo.setBackgroundResource(R.drawable.black2logo)
                materialCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorBlack2))
                txtTeamName.setTextColor(ContextCompat.getColor(context, R.color.colorBlack2Secundary))
                txtTier.setTextColor(ContextCompat.getColor(context,R.color.colorBlack2Tertiary))
            }
            "Y" -> {
                imgGameLogo.setBackgroundResource(R.drawable.ylogo)
                materialCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorY))
                txtTeamName.setTextColor(ContextCompat.getColor(context, R.color.colorYSecundary))
                txtTier.setTextColor(ContextCompat.getColor(context,R.color.colorYTertiary))
            }
            "Alpha Sapphire" -> {
                imgGameLogo.setBackgroundResource(R.drawable.alphalogo)
                materialCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorAlphaSapphire))
                txtTeamName.setTextColor(ContextCompat.getColor(context, R.color.colorAlphaSapphireSecundary))
                txtTier.setTextColor(ContextCompat.getColor(context,R.color.colorAlphaSapphireTertiary))
            }
            else ->
            {
                imgGameLogo.setBackgroundResource(R.drawable.alphalogo)
                materialCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary))}
        }

            txtTier.text = pokemonTeams.tier
            txtTeamName.text = pokemonTeams.name

    }
}


package com.arthursales.smogon.view.rankTier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arthursales.smogon.R
import com.arthursales.smogon.models.SmogonResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ranking_pokemon.view.*
import kotlinx.android.synthetic.main.ranking_tabs_layout.view.*

class RankTierAdapter(
    private val pokemons: List<SmogonResponse?>,
    private val picasso: Picasso
    //private val clickListener: (SmogonResponse) -> Unit
): RecyclerView.Adapter<RankTierViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RankTierViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_ranking_pokemon, p0, false)
        return RankTierViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(p0: RankTierViewHolder, p1: Int) {
        val pokemon = pokemons[p1]
        p0.bindView(pokemon, picasso)    }

}

class RankTierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    fun bindView(pokemon: SmogonResponse?,
                 picasso: Picasso
                 //clickListener: (SmogonResponse) -> Unit
    ) = with(itemView) {
        //TODO tratar Adapter
//        previous_tab_month.text = "Outubro"
//        previous_tab_status.text = pokemon?.tier
//        selected_tab_month.text = "Novembro"
//        selected_tab_status.text = pokemon?.tier
//        next_tab_month.text = "Dezembro"
//        next_tab_status.text = pokemon?.tier

        txtThirdNumber.text = pokemon?.rank.toString()
        txtThirdName.text = pokemon?.pokemon
        txtThirdUsage.text = pokemon?.usage_pct.toString().plus("%")

        picasso.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon?.dex}.png").into(imgThirdPoke)

        //setOnClickListener { clickListener(pokemon) }
    }

}

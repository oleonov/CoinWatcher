package com.app.coinwatcher.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.coinwatcher.R
import com.app.coinwatcher.components.MyViewHolder
import com.app.coinwatcher.models.CoinCap
import kotlinx.android.synthetic.main.item_coin.view.*

/**
 * Created by Leonov Oleg, http://pandorika-it.com on 07.04.17.
 */

class CoinAdapter : RecyclerView.Adapter<MyViewHolder>()
{
    var mData: List<CoinCap>?=null
        set(value) {
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        mData?.get(position)?.let {
            coin ->
            holder.view.tvSymbol.text=coin.symbol
            holder.view.tvName.text=coin.name
            holder.view.tvPrice.text="$${coin.price_usd}"
            holder.view.tvPriceChange.text=if(coin.percent_change_24h>0) "+${coin.percent_change_24h}%" else "${coin.percent_change_24h}%"
        }
    }


    override fun getItemCount(): Int = mData?.size?:0
}

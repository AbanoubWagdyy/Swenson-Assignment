package com.swenson.ui.currenciesList.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.swenson.R
import com.swenson.data.entities.CurrencyRate
import kotlinx.android.synthetic.main.currency_list_item.view.*

class CurrencyListAdapter(
    private val mValues: MutableList<CurrencyRate>,
    private val mListener: OnItemClickListener?
) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(item: CurrencyRate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        with(holder) {
            symbol.text = holder.mItem.symbolText
            amount.text = holder.mItem.rate.toString()
            symbolIcon.setBackgroundResource(holder.mItem.symbolIcon)
        }

        with(holder.mView) {
            tag = holder.mItem
            setOnClickListener {
                mListener?.onItemClicked(holder.mItem)
            }
        }
    }

    override fun getItemCount() = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val symbolIcon: ImageView = mView.symbolIcon
        val symbol: TextView = mView.symbol
        val amount: TextView = mView.amount

        lateinit var mItem: CurrencyRate

        override fun toString(): String {
            return mItem.toString()
        }
    }
}
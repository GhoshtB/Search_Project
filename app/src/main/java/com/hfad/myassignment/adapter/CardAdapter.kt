package com.hfad.myassignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.myassignment.R
import com.hfad.myassignment.model.Data
import com.hfad.myassignment.model.LoanData
import kotlinx.android.synthetic.main.card_row.view.*

class CardAdapter(val data: List<Data>, val context: Context):RecyclerView.Adapter<CardAdapter.CardViewHolder>(){

var  i1=0
    lateinit var listener: CardListener
    public interface CardListener{
        fun onCardClick(data:Data)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardViewHolder =
            CardViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.card_row,p0,false))

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(p0: CardViewHolder, p1: Int) {
        p0.apply {
            data[p1].let {
                title.text=it.fundname
            }
        }
        p0.add_text.setOnClickListener {
            if (i1==0){
                p0.rLayout.visibility=View.VISIBLE
                p0.add_text.visibility=View.GONE
                i1=1
            }
        }





        p0.btnFund.setOnClickListener {
            if (p0.fund_edit.text.isEmpty()){
                p0.fund_edit.error="Empty"
            }else{
                listener.onCardClick(data[p1])
                if (i1==1){
                    p0.rLayout.visibility=View.GONE
                    p0.add_text.visibility=View.VISIBLE
                    i1=0
                }
            }


        }
    }

fun setCardListener(listener: CardListener ){
    this.listener=listener
}

    inner class CardViewHolder(view:View):RecyclerView.ViewHolder(view){
val add_text =view.add_text
        val title=view.title
        val rLayout=view.rLayout
        val btnFund=view.btnFund
        val fund_edit=view.fund_edit
    }
}
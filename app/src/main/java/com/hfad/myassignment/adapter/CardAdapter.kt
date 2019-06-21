package com.hfad.myassignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
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
                min_text.text=it.minsipamount.toString()
                max_text.text=it.minsipmultiple.toString()
            }
        }
        p0.add_text.setOnClickListener {
            if (i1==0){
                p0.rLayout.visibility=View.VISIBLE
                p0.add_text.visibility=View.GONE
                i1=1
            }
        }


            p0.fund_edit.addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    try {
                    if ((p0.fund_edit.text.toString().toInt()*5)==data[p1].minsipamount &&(p0.fund_edit.text.toString().toInt()*10)==data[p1].minsipamount &&(p0.fund_edit.text.toString().toInt()*15)==data[p1].minsipamount &&(p0.fund_edit.text.toString().toInt()*20)==data[p1].minsipamount  ){
                        p0.fund_edit?.error =null
                    }else {
                        p0.fund_edit.error="Empty"
                    }
                    }catch (e:NumberFormatException ){
                        e.printStackTrace()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            })





        p0.btnFund.setOnClickListener {
            if (p0.fund_edit.text.isEmpty() && p0.fund_edit.text.toString().toInt()<data[p1].minsipamount){
                p0.fund_edit.error="Empty"
            }else
//                if ((p0.fund_edit.text.toString().toInt()*5)==data[p1].minsipamount &&(p0.fund_edit.text.toString().toInt()*10)==data[p1].minsipamount &&(p0.fund_edit.text.toString().toInt()*15)==data[p1].minsipamount &&(p0.fund_edit.text.toString().toInt()*20)==data[p1].minsipamount  )
                {
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
        val max_text=view.max_text
        val min_text=view.min_text
    }
}
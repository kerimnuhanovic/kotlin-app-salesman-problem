package com.example.salesmanproblemproject


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.salesmanproblemproject.database.GradDB

class GradAdapter: RecyclerView.Adapter<GradAdapter.GradViewHolder>() {

    private var listaGradova = emptyList<GradDB>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradViewHolder {
        val layoutType=R.layout.grad_item
        val view: View=LayoutInflater.from(parent.context).inflate(layoutType,parent, false)
        return GradViewHolder(view)

    }

    override fun onBindViewHolder(holder: GradViewHolder, position: Int) {
        val trenutniGrad = listaGradova[position]

        holder.grad.text= trenutniGrad.grad
        holder.drzava.text=trenutniGrad.drzava
        holder.latituda.text = trenutniGrad.latituda.toString()
        holder.longituda.text = trenutniGrad.longituda.toString()
        holder.okvir.setOnClickListener {
            it.findNavController().navigate(ListaGradovaFragmentDirections.actionListaGradovaFragmentToGradDetaljiFragment(trenutniGrad))
        }
    }

    override fun getItemCount(): Int {
        return listaGradova.size
    }
    //moze se reci da je ova klasa ispod nas jedan item od Recycler viewa
    class GradViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var grad:TextView=itemView.findViewById(R.id.Grad)
        var drzava:TextView=itemView.findViewById(R.id.Drzava)/**/
        var latituda:TextView = itemView.findViewById(R.id.latituda)

        var longituda:TextView = itemView.findViewById(R.id.longituda)

        var okvir: View = itemView.findViewById(R.id.okvirReda)

    }

    fun setGradovi(gradovi: List<GradDB>) {
        this.listaGradova = gradovi
        notifyDataSetChanged()
    }

}
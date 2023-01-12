package br.com.alura.orgs.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.model.Produto

class ListaDeProdutosAdapter(
    private val context: Context,
    listaProdutos: List<Produto>
) : RecyclerView.Adapter<ListaDeProdutosAdapter.ViewHolder>() {

    private val listaProdutos = listaProdutos.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(produto: Produto) {
            val nome = itemView.findViewById<TextView>(R.id.produto_item_nome)
            nome.text = produto.nome
            val descricao = itemView.findViewById<TextView>(R.id.produto_item_descricao)
            descricao.text = produto.descricao
            val valor = itemView.findViewById<TextView>(R.id.produto_item_preco)
            valor.text = produto.preco.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int {
        return listaProdutos.size
    }

    fun atualiza(produtos: List<Produto>) {
        this.listaProdutos.clear()
        this.listaProdutos.addAll(produtos)
    }

}

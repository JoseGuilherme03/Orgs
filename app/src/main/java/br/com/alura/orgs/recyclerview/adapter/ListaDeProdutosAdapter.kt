package br.com.alura.orgs.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.model.Produto

class ListaDeProdutosAdapter(
    private val context: Context,
    listaProdutos: List<Produto>
) : RecyclerView.Adapter<ListaDeProdutosAdapter.ViewHolder>() {

    private val listaProdutos = listaProdutos.toMutableList()

    class ViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nome = binding.produtoItemNome
        val descricao = binding.produtoItemDescricao
        val valor = binding.produtoItemPreco

        fun vincula(produto: Produto) {
            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = produto.preco.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
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

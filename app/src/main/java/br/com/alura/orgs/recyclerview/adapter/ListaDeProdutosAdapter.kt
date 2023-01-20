package br.com.alura.orgs.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.model.Produto
import coil.load
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaDeProdutosAdapter(
    private val context: Context,
    listaProdutos: List<Produto>
) : RecyclerView.Adapter<ListaDeProdutosAdapter.ViewHolder>() {

    private val listaProdutos = listaProdutos.toMutableList()

    class ViewHolder(val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nome = binding.produtoItemNome
        private val descricao = binding.produtoItemDescricao
        private val valor = binding.produtoItemPreco


        fun vincula(produto: Produto) {
            val valorEmMoeda = formataEmMoedaBrasileira(produto.preco)
            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = valorEmMoeda
            binding.imageView.load(produto.imagem)
        }

        private fun formataEmMoedaBrasileira(valor: BigDecimal): String? {
            val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(valor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
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
        notifyDataSetChanged()
    }

}

package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.imageextension.tentaCarregarImagem
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Produto"
        configuraBotaoSalvar()
        binding.formularioProdutoImageView.setOnClickListener{
            FormularioImagemDialog(this).mostra(url) {imagem ->
                url = imagem
                binding.formularioProdutoImageView.tentaCarregarImagem(url)
            }
        }
    }

    private fun configuraBotaoSalvar() {
        val dao = ProdutosDao()
        val botaoSalvar = binding.formularioProdutoBotaoSalvar
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }


    private fun criaProduto(): Produto {
        val campoNome = binding.formularioProdutoTextNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.formularioProdutoTextDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.formularioProdutoTextValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(nome, descricao, valor, url)
    }
}
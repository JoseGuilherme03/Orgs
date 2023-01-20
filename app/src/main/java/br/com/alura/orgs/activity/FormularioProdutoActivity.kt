package br.com.alura.orgs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.databinding.DialogoAdicionaImagemBinding
import br.com.alura.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {
    private val bindingActivityFormularioProduto by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private val binding2 by lazy { DialogoAdicionaImagemBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivityFormularioProduto.root)
        configuraBotaoSalvar()
        bindingActivityFormularioProduto.formularioProdutoImageView.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(binding2.root)
                .setPositiveButton("CONFIRMAR") { _, _ ->
                }
                .setNegativeButton("CANCELAR") { _, _ ->
                }
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val dao = ProdutosDao()
        val botaoSalvar = bindingActivityFormularioProduto.formularioProdutoBotaoSalvar
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = bindingActivityFormularioProduto.formularioProdutoTextNome
        val nome = campoNome.text.toString()
        val campoDescricao = bindingActivityFormularioProduto.formularioProdutoTextDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = bindingActivityFormularioProduto.formularioProdutoTextValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(nome, descricao, valor)
    }
}
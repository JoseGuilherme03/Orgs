package br.com.alura.orgs.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.databinding.DialogoAdicionaImagemBinding
import br.com.alura.orgs.model.Produto
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {
    private val bindingActivityFormularioProduto by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivityFormularioProduto.root)
        configuraBotaoSalvar()
        bindingActivityFormularioProduto.formularioProdutoImageView.setOnClickListener {
            val bindingDialogoAdiconaImagem by lazy {
                DialogoAdicionaImagemBinding.inflate(layoutInflater)
            }

            bindingDialogoAdiconaImagem.dailogoAdicionaImagemButtonRefresh.setOnClickListener {
                url = bindingDialogoAdiconaImagem.dialogoAdicionaImagemUrl.text.toString()
                bindingDialogoAdiconaImagem.dialogoAdicionaImagemvazia.load(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingDialogoAdiconaImagem.root)
                .setPositiveButton("CONFIRMAR") { _, _ ->
                    url = bindingDialogoAdiconaImagem.dialogoAdicionaImagemUrl.text.toString()
                    bindingActivityFormularioProduto.formularioProdutoImageView.load(url)
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

        return Produto(nome, descricao, valor, url)
    }
}
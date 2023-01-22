package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.databinding.ListaProdutosActivityBinding
import br.com.alura.orgs.recyclerview.adapter.ListaDeProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {
    private val dao = ProdutosDao()
    private val adapter = ListaDeProdutosAdapter(this, dao.pegaTodosProdutos())

    private val binding by lazy { ListaProdutosActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraReciclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.pegaTodosProdutos())
        configuraFab()
    }

    private fun configuraReciclerView() {
        val recyclerView = binding.listaProdutosRecyclerView
        recyclerView.adapter = adapter
    }

    private fun configuraFab() {
        val fab = binding.listaProdutosFab
        fab.setOnClickListener {
            acessaFormularioProdutoActivity()
        }
    }

    private fun acessaFormularioProdutoActivity() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }
}

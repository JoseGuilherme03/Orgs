package br.com.alura.orgs.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.recyclerview.adapter.ListaDeProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity : AppCompatActivity(R.layout.lista_produtos_activity) {
    private val dao = ProdutosDao()
    private val adapter = ListaDeProdutosAdapter(this, dao.pegaTodosProdutos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraReciclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.pegaTodosProdutos())
        configuraFab()
    }

    private fun configuraReciclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.lista_produtos_recyclerView)
        recyclerView.adapter = adapter
    }

    private fun configuraFab() {
        val fab = findViewById<FloatingActionButton>(R.id.lista_produtos_fab)
        fab.setOnClickListener {
            acessaFormularioProdutoActivity()
        }
    }

    private fun acessaFormularioProdutoActivity() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }
}

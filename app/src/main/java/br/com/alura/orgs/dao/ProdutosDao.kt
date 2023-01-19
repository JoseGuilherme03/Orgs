package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        listaProdutosDao.add(produto)
    }

    fun pegaTodosProdutos(): List<Produto> = listaProdutosDao.toList()

    companion object {
        private val listaProdutosDao = mutableListOf<Produto>(
            Produto(
                nome = "Salada de Frutas",
                descricao = "Morango, Banana e Uva",
                BigDecimal("9.99")
            )
        )
    }
}
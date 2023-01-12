package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Produto

class ProdutosDao {

    fun adiciona(produto: Produto) {
        listaProdutosDao.add(produto)
    }

    fun pegaTodosProdutos(): List<Produto> = listaProdutosDao.toList()

    companion object {
        private val listaProdutosDao = mutableListOf<Produto>()
    }
}
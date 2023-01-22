package br.com.alura.orgs.imageextension

import br.com.alura.orgs.R
import coil.load
import android.widget.ImageView

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        error(R.drawable.erro)
        placeholder(R.drawable.background_cinza)
    }
}
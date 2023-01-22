package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.DialogoAdicionaImagemBinding
import br.com.alura.orgs.imageextension.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(urlPadrao:String?, quandoImagemCarregada: (imagem:String?) -> Unit) {
        DialogoAdicionaImagemBinding.inflate(LayoutInflater.from(context)).apply {
            var url: String? = null

            urlPadrao?.let {
                dialogoAdicionaImagemvazia.tentaCarregarImagem(it)
                dialogoAdicionaImagemUrl.setText(it)
            }

            dailogoAdicionaImagemButtonRefresh.setOnClickListener {
                url = dialogoAdicionaImagemUrl.text.toString()
                dialogoAdicionaImagemvazia.tentaCarregarImagem(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("CONFIRMAR") { _, _ ->
                    url = dialogoAdicionaImagemUrl.text.toString()
                    quandoImagemCarregada(url)
                }
                .setNegativeButton("CANCELAR") { _, _ ->
                }
                .show()
        }
        }
}

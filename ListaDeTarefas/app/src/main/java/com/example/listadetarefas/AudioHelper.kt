package com.example.listadetarefas

import android.content.Context
import android.media.MediaPlayer

class AudioHelper(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    // Método para tocar o áudio
    fun playAudio() {

        mediaPlayer?.start()
    }


    fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release() // Libera o MediaPlayer após o uso
    }
}

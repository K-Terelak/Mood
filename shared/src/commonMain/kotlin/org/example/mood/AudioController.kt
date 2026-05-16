package org.example.mood

interface AudioController {
    fun load(source: String)
    fun play()
    fun pause()
    fun stop()
    fun seek(seconds: Double)
    fun getCurrentTime(): Double
    fun getDuration(): Double
    fun isPlaying(): Boolean
    fun setOnEnded(listener: (() -> Unit)?)
}

expect fun createAudioController(): AudioController


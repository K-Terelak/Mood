@file:OptIn(kotlin.js.ExperimentalWasmJsInterop::class)

package org.example.mood

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

private class WasmAudioController : AudioController {
    private val audio = (document.createElement("audio") as HTMLAudioElement).apply {
        preload = "metadata"
    }

    override fun load(source: String) {
        audio.src = source
        audio.load()
    }

    override fun play() {
        audio.play()
    }

    override fun pause() {
        audio.pause()
    }

    override fun stop() {
        audio.pause()
        audio.currentTime = 0.0
    }

    override fun seek(seconds: Double) {
        audio.currentTime = seconds
    }

    override fun getCurrentTime(): Double = audio.currentTime

    override fun getDuration(): Double = audio.duration.takeIf { it.isFinite() } ?: 0.0

    override fun isPlaying(): Boolean = !audio.paused

    override fun setOnEnded(listener: (() -> Unit)?) {
        audio.onended = {
            listener?.invoke()
            null
        }
    }
}

actual fun createAudioController(): AudioController = WasmAudioController()

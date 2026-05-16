package org.example.mood

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement
import web.navigator.navigator

class JsPlatform: Platform {
    private val userAgent = navigator.userAgent
    private val browserList = listOf("Chrome", "Firefox", "Safari", "Edge")

    override val name: String = userAgent.findAnyOf(browserList, ignoreCase = true)
            ?.let { (startIndex) -> userAgent.substring(startIndex).substringBefore(" ") }
            ?: "Unknown"
}

actual fun getPlatform(): Platform = JsPlatform()

private class JsAudioController : AudioController {
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

actual fun createAudioController(): AudioController = JsAudioController()

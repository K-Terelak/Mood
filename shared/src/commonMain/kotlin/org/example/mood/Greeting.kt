package org.example.mood

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return sayHello(platform.name)
    }
}
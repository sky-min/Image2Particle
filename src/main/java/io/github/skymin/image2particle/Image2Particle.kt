package io.github.skymin.image2particle

import io.github.skymin.image2particle.particle.ImageParticleAPI
import org.bukkit.plugin.java.JavaPlugin

class Image2Particle : JavaPlugin() {
    companion object {
        private var instance: Image2Particle? = null

        fun getInstance(): Image2Particle?{
            return instance
        }
    }

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        for (file in dataFolder.listFiles()!!){
            ImageParticleAPI.registerImage(file.nameWithoutExtension, file.name)
        }
    }
}
package io.github.skymin.image2particle.particle

import io.github.skymin.image2particle.Image2Particle
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

object ImageParticleAPI {

    private var images: MutableMap<String, ImageParticle> = mutableMapOf()

    fun registerImage(name: String, fileName: String) {
        val file = File(fileName)
        val image: BufferedImage
        try{
            image = ImageIO.read(file)
        }catch (e: IOException){
            Image2Particle.getInstance()?.logger?.warning("{${e.message}} ({$fileName})")
            return
        }
        registerImage(name, image)
    }

    fun registerImage(name: String, image: BufferedImage) {
        if (images[name] != null){
            throw ImageParticleException("{$name} already exists")
        }
        val centerX: Int = image.width / 2
        val centerY: Int = image.height / 2
        for (x: Int in 0 until image.width){
            for (y: Int in 0 until image.height){
                val rgb = image.getRGB(x, y)
                if(Color(rgb).alpha < 50) continue

            }
        }
        images[name] = ImageParticle(name, mapOf())
    }

    fun getImageParticle(name: String): ImageParticle? {
        return images[name]
    }
}
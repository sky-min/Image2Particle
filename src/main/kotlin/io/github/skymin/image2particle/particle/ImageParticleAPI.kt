package io.github.skymin.image2particle.particle

import java.awt.Color
import java.awt.image.BufferedImage
import org.bukkit.Color as BukkitColor

object ImageParticleAPI {

	private var images: MutableMap<String, ImageParticle> = mutableMapOf()

	fun registerImage(name: String, image: BufferedImage) {
		if (images[name] !== null) {
			throw ImageParticleException("{$name} already exists")
		}
		val centerX: Int = image.width / 2
		val centerY: Int = image.height / 2
		val colorData: MutableMap<Int, Map<Int, BukkitColor>> = mutableMapOf()
		for (x: Int in 0 until image.width) {
			val yColorData: MutableMap<Int, BukkitColor> = mutableMapOf()
			for (y: Int in 0 until image.height) {
				val color = Color(image.getRGB(x, y))
				if (color.alpha < 50) continue
				yColorData[y - centerY] = BukkitColor.fromRGB(color.red, color.green, color.blue)
			}
			colorData[x - centerX] = yColorData
		}
		images[name] = ImageParticle(name, colorData)
	}


	fun isRegistered(name: String): Boolean {
		if (images[name] === null) {
			return false
		}
		return true
	}

	fun getImageParticle(name: String): ImageParticle? {
		return images[name]
	}

	fun getImageList() : List<String> {
		return images.keys.toList()
	}
}
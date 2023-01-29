package io.github.skymin.image2particle

import io.github.skymin.image2particle.particle.ImageParticleAPI
import org.bukkit.plugin.java.JavaPlugin
import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO

class Image2Particle : JavaPlugin() {

	override fun onEnable() {
		if (!dataFolder.exists()) {
			dataFolder.mkdir()
		} else {
			dataFolder.listFiles()?.forEach {
				val image: BufferedImage
				try {
					image = ImageIO.read(it)
				} catch (e: IOException) {
					logger.warning("{${e.message}} ({${file.name}})")
					return
				}
				ImageParticleAPI.registerImage(file.nameWithoutExtension, image)
			}
		}
	}
}
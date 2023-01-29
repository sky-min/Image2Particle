package io.github.skymin.image2particle

import io.github.skymin.image2particle.command.ImageParticleCommand
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
			dataFolder.listFiles()?.forEachIndexed { _, file ->
				if(file !== null){
					val image: BufferedImage
					try {
						image = ImageIO.read(file)
					} catch (e: IOException) {
						logger.warning("{${e.message}} ({${file.name}})")
						return
					}
					ImageParticleAPI.registerImage(file.nameWithoutExtension, image)
				}
			}
		}
		getCommand("image2particle")?.setExecutor(ImageParticleCommand())
	}
}
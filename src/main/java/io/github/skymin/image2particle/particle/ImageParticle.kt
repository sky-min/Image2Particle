package io.github.skymin.image2particle.particle

import org.bukkit.Color

class ImageParticle(
	private val name: String,
	private val data: Map<Int, Map<Int, Color>>
) {

	fun getName(): String {
		return name
	}


}
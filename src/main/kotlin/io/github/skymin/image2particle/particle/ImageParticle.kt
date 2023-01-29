package io.github.skymin.image2particle.particle

import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import kotlin.math.cos
import kotlin.math.sin

class ImageParticle(
	private val name: String,
	private val data: Map<Int, Map<Int, Color>>
) {

	fun getName(): String {
		return name
	}

	fun encode(euler: EulerAngle, unit: Double, size: Float) {
		val pos: Location = euler.getPosition()
		//yaw
		val yaw = Math.toRadians(euler.getYaw())
		val ysin = sin(yaw)
		val ycos = cos(yaw)
		//pitch
		val pitch = Math.toRadians(euler.getPitch())
		val psin = sin(pitch)
		val pcos = cos(pitch)
		//roll
		val roll = Math.toRadians(euler.getRoll())
		val rsin = sin(roll)
		val rcos = cos(roll)

		data.forEach { (x, yMap) ->
			yMap.forEach{ (y, color) ->
				val dx = (y * rsin + x * rcos) * unit
				val dy = (y * rcos - x * rsin) * unit
				val dz = dy * psin
				euler.getWorld().spawnParticle(
					Particle.REDSTONE, pos.clone().add(
						dz * ysin + dx * ycos,
						dy * -pcos,
						dz * -ycos + dx * ysin
					), 1, 0.0, 0.0, 0.0, DustOptions(color, size)
				)
			}
		}
	}
}
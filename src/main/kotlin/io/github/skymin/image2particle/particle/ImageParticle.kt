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

	fun encode(eulerAngle: EulerAngle, unit: Float, size: Float) {
		val pos: Location = eulerAngle.getPosition()
		val yaw = Math.toRadians(eulerAngle.getYaw())
		val pitch = Math.toRadians(eulerAngle.getPitch())
		val roll = Math.toRadians(eulerAngle.getRoll())

		val ysin = sin(yaw)
		val ycos = cos(yaw)
		val psin = sin(pitch)
		val pcos = cos(pitch)
		val rsin = sin(roll)
		val rcos = cos(roll)

		data.forEach {( x, yMap) ->
			yMap.forEach{
				(y, color) ->
				val qx: Float = x * unit
				val qy: Float = y * unit
				val dx = qy * rsin + qx * rcos
				val dy = qy * rcos - qx * rsin
				val dz = dy * psin
				eulerAngle.getWorld().spawnParticle(
					Particle.REDSTONE, pos.add(
						dz * ysin + dx * ycos,
						dy * -pcos,
						dz * -ycos + dx * ysin
					), 1, 0.0, 0.0, 0.0, DustOptions(color, size)
				)
			}
		}
	}
}
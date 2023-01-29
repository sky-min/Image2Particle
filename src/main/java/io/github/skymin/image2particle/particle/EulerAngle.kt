package io.github.skymin.image2particle.particle

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector

class EulerAngle(
	private val world: World,
	private val x: Double,
	private val y: Double,
	private val z: Double,
	private val yaw: Double,
	private val pitch: Double,
	private val roll: Double
) {
	companion object {
		fun fromObject(vector: Vector, world: World, yaw: Double, pitch: Double, roll: Double): EulerAngle {
			return EulerAngle(world, vector.x, vector.y, vector.z, yaw, pitch, roll)
		}

		fun fromObject(loc: Location, roll: Double): EulerAngle {
			return EulerAngle(loc.world, loc.x, loc.y, loc.z, loc.yaw.toDouble(), loc.pitch.toDouble(), roll)
		}
	}

	fun getWorld(): World {
		return world
	}

	fun getX(): Double {
		return x
	}

	fun getY(): Double {
		return y
	}

	fun getZ(): Double {
		return z
	}

	fun getYaw(): Double {
		return yaw
	}

	fun getPitch(): Double {
		return pitch
	}

	fun getRoll(): Double {
		return roll
	}
}
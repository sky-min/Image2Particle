package io.github.skymin.image2particle.command

import io.github.skymin.image2particle.particle.EulerAngle
import io.github.skymin.image2particle.particle.ImageParticle
import io.github.skymin.image2particle.particle.ImageParticleAPI
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class ImageParticleCommand : TabExecutor{
	override fun onTabComplete(
		sender: CommandSender,
		command: Command,
		label: String,
		args: Array<out String>?
	): List<String>? {
		if(args === null) return null
		return when(args.size){
			1 ->  ImageParticleAPI.getImageList()
			2 -> listOf("1.0")
			3 -> listOf("1.0")
			4 -> listOf("0.0")
			5 -> listOf("0.0")
			6 -> listOf("0.0")
			else -> null
		}
	}

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
		if(sender !is Player) return false
		if(!command.testPermission(sender)) return false
		if(args === null) {
			sendHelper(sender)
			return false
		}
		val particle: ImageParticle? = ImageParticleAPI.getImageParticle(args[0])
		if(particle === null){
			sender.sendMessage("${args[0]} is not registered")
			return false
		}
		val inserts: MutableList<Double> = mutableListOf(1.0, 1.0, 0.0, 0.0, 0.0)
		for (i in 1..5){
			if(args[i] === null) break
			val new: Double? = convertNumber(args[i])
			if(new === null){
				sendHelper(sender)
				return false
			}
			inserts[i] = new
		}
		val pos: Location = sender.location
		particle.encode(EulerAngle.fromObject(
			pos.toVector(),
			pos.world,
			inserts[2],
			inserts[3],
			inserts[4],
		), inserts[2], inserts[1].toFloat())
		return true
	}

	private fun sendHelper(sender: CommandSender) {
		sender.sendMessage("${ChatColor.RED}}/image2particle <name:string> [size:float] [unit:float] [yaw:float] [pitch:float] [roll:float]")
	}

	private fun convertNumber(arg: String) : Double?{
		val result: Double
		try{
			result = arg.toDouble()
		}catch (e: NumberFormatException){
			return null
		}
		return result
	}
}
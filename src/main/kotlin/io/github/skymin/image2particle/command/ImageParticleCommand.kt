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
			else -> listOf()
		}
	}

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
		if(sender !is Player) return true
		if(!command.testPermission(sender)) return true
		if(args == null || args.getOrNull(0) === null) {
			sendHelper(sender)
			return true
		}
		val particle: ImageParticle? = ImageParticleAPI.getImageParticle(args[0])
		if(particle == null){
			sender.sendMessage("${args[0]} is not registered")
			return true
		}
		val inserts: Array<Double> = arrayOf(1.0, 1.0, 0.0, 0.0, 0.0)
		for ((i, arg) in args.withIndex()){
			if(i == 0) continue
			val new: Double? = convertNumber(arg)
			if(new === null){
				sendHelper(sender)
				return true
			}
			inserts[i - 1] = new
		}
		val pos: Location = sender.location
		particle.encode(EulerAngle.fromObject(
			pos.toVector(),
			pos.world,
			inserts[2],
			inserts[3],
			inserts[4],
		), inserts[1], inserts[0].toFloat())
		return true
	}

	private fun sendHelper(sender: CommandSender) {
		sender.sendMessage("${ChatColor.RED}/image2particle <name:string> [size:float] [unit:float] [yaw:float] [pitch:float] [roll:float]")
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
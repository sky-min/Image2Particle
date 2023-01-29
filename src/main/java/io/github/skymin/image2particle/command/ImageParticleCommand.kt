package io.github.skymin.image2particle.command

import io.github.skymin.image2particle.particle.ImageParticleAPI
import org.bukkit.ChatColor
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
	): MutableList<String>? {
		var arguments: List<String> = listOf()
		//TODO: autocomplete
		return null
	}

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
		if(sender !is Player) return false
		if(!command.testPermission(sender)) return false
		if(args === null) {
			sender.sendMessage("${ChatColor.RED}}/image2particle <name> [size] [unit] [yaw] [pitch] [roll]")
			return false
		}
		if(!ImageParticleAPI.isRegistered(args[0])){
			sender.sendMessage("${args[0]} is not registered")
			return false
		}
		//TODO .....
		return true
	}
}
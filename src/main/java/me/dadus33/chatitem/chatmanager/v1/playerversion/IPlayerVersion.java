package me.dadus33.chatitem.chatmanager.v1.playerversion;

import org.bukkit.entity.Player;

import me.dadus33.chatitem.utils.Version;

public interface IPlayerVersion {
	
	public int getProtocolVersion(Player p);
	
	default Version getPlayerVersion(Player p) {
		return Version.getVersion(getProtocolVersion(p));
	}
}

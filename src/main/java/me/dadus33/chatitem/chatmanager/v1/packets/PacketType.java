package me.dadus33.chatitem.chatmanager.v1.packets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface PacketType {

	String name();
	String getPacketName();
	String getFullName();
	List<String> getAlias();
	
	public static List<PacketType> values() {
		List<PacketType> list = new ArrayList<>();
		list.addAll(Arrays.asList(Server.values()));
		list.addAll(Arrays.asList(Handshake.values()));
		return list;
	}
	
	static final String SERVER_PREFIX = "PacketPlayOut", HANDSHAKE_PREFIX = "PacketHandshaking";
	
	public static PacketType getType(String packetName) {
		if(packetName.startsWith(SERVER_PREFIX)) {
			return getPacketTypeFor(packetName, Server.values(), Server.UNSET);
		} else if(packetName.startsWith(HANDSHAKE_PREFIX)) {
			return getPacketTypeFor(packetName, Handshake.values(), Handshake.UNSET);
		} else {
			for(PacketType types : values()) {
				if(types.getFullName().equalsIgnoreCase(packetName) || types.getAlias().contains(packetName))
					return types;
			}
			return null;
		}
	}
	
	static PacketType getPacketTypeFor(String packetName, PacketType[] types, PacketType unset) {
		for(PacketType packet : types)
			if(packet.getFullName().equalsIgnoreCase(packetName) || packet.getPacketName().equalsIgnoreCase(packetName)  || packet.getAlias().contains(packetName))
				return packet;
		return unset;
	}
	
	public static enum Server implements PacketType {
		
		CHAT("Chat", "ClientboundPlayerChatPacket", "ClientboundSystemChatPacket"),
		UNSET("Unset");
		
		private final String packetName, fullName;
		private List<String> alias = new ArrayList<>();
		
		private Server(String packetName, String... alias) {
			this.packetName = packetName;
			this.fullName = SERVER_PREFIX + packetName;
			for(String al : alias)
				this.alias.add(al);
		}

		@Override
		public String getPacketName() {
			return packetName;
		}

		@Override
		public String getFullName() {
			return fullName;
		}
		
		@Override
		public List<String> getAlias() {
			return alias;
		}
	}
	
	public static enum Handshake implements PacketType {
		
		IS_SET_PROTOCOL("InSetProtocol"),
		UNSET("Unset");
		
		private final String packetName, fullName;
		private List<String> alias = new ArrayList<>();
		
		private Handshake(String packetName, String... alias) {
			this.packetName = packetName;
			for(String al : alias)
				this.alias.add(HANDSHAKE_PREFIX + al);
			this.fullName = HANDSHAKE_PREFIX + packetName;
		}

		@Override
		public String getPacketName() {
			return packetName;
		}

		@Override
		public String getFullName() {
			return fullName;
		}
		
		@Override
		public List<String> getAlias() {
			return alias;
		}
		
	}
}

package net.hermite.RPCustomsPlayerEffects.util.Handlers;

import net.hermite.RPCustomsPlayerEffects.Packets.PacketCPPlayerSC;
import net.hermite.RPCustomsPlayerEffects.Packets.PacketFootStepGenCS;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	
	private static int packetId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        // Register messages which are sent from the client to the server here:
        INSTANCE.registerMessage(PacketCPPlayerSC.Handler.class, PacketCPPlayerSC.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketFootStepGenCS.Handler.class, PacketFootStepGenCS.class, nextID(), Side.SERVER);
    }
	
}
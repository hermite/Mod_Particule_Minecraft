package net.hermite.RPCustomsPlayerEffects.proxy;

import net.hermite.RPCustomsPlayerEffects.util.Handlers.EventClientHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void load() {
		MinecraftForge.EVENT_BUS.register(new EventClientHandler());
	}
		
	
}

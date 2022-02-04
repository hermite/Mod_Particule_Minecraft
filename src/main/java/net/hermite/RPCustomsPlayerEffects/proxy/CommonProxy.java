package net.hermite.RPCustomsPlayerEffects.proxy;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.hermite.RPCustomsPlayerEffects.Main;
import net.hermite.RPCustomsPlayerEffects.util.FootStepStorage;
import net.hermite.RPCustomsPlayerEffects.util.GestionFichier;
import net.hermite.RPCustomsPlayerEffects.util.StatusStorage;
import net.hermite.RPCustomsPlayerEffects.util.Class.IFootStep;
import net.hermite.RPCustomsPlayerEffects.util.Class.IStatus;
import net.hermite.RPCustomsPlayerEffects.util.Class.Status;
import net.hermite.RPCustomsPlayerEffects.util.Class.footStep;
import net.hermite.RPCustomsPlayerEffects.util.Class.footStepSaveClass;
import net.hermite.RPCustomsPlayerEffects.util.Handlers.CapabilityHandler;
import net.hermite.RPCustomsPlayerEffects.util.Handlers.EventServerHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {
		
	}

	public void load() {
		CapabilityManager.INSTANCE.register(IStatus.class, new StatusStorage(), Status.class);
		CapabilityManager.INSTANCE.register(IFootStep.class, new FootStepStorage(), footStep.class);
		
		MinecraftForge.EVENT_BUS.register(new EventServerHandler());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		// TODO Auto-generated method stub
		
	}

}

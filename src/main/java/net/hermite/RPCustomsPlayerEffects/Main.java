package net.hermite.RPCustomsPlayerEffects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import net.hermite.RPCustomsPlayerEffects.init.BlocksInit;
import net.hermite.RPCustomsPlayerEffects.proxy.CommonProxy;
import net.hermite.RPCustomsPlayerEffects.util.GestionFichier;
import net.hermite.RPCustomsPlayerEffects.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.hermite.RPCustomsPlayerEffects.util.Class.CPPlayer;
import net.hermite.RPCustomsPlayerEffects.util.Class.footStepSaveClass;
import net.hermite.RPCustomsPlayerEffects.util.Handlers.PacketHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	public static ArrayList<CPPlayer> CPPlayerList = new ArrayList<CPPlayer>();
	public static int LastPlayerEvent = 0;
	public static long LastEvent = 0;
	public static boolean WaitEvent = false;
	
	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		PacketHandler.registerMessages(Reference.MOD_ID);
		BlocksInit.init();
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event)
	{
		proxy.load();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		
	}	
}

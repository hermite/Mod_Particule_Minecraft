package net.hermite.RPCustomsPlayerEffects.init;

import net.hermite.RPCustomsPlayerEffects.Blocks.BlockFootStep;
import net.hermite.RPCustomsPlayerEffects.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class BlocksInit 
{
	public static Block footstep0;
	public static Block footstep1;
	public static Block footstep2;
	
	public static void init()
	{
		footstep0 = new BlockFootStep("footstep0", Material.WOOD);
		footstep1 = new BlockFootStep("footstep1", Material.WOOD);
		footstep2 = new BlockFootStep("footstep2", Material.WOOD);
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> e)
	{
		e.getRegistry().register(footstep0);
		e.getRegistry().register(footstep1);
		e.getRegistry().register(footstep2);
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> e)
	{
		e.getRegistry().registerAll(new ItemBlock(footstep0).setRegistryName(footstep0.getRegistryName()));
		e.getRegistry().registerAll(new ItemBlock(footstep1).setRegistryName(footstep1.getRegistryName()));
		e.getRegistry().registerAll(new ItemBlock(footstep2).setRegistryName(footstep2.getRegistryName()));
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent e)
	{
		registerRender(Item.getItemFromBlock(footstep0));
		registerRender(Item.getItemFromBlock(footstep1));
		registerRender(Item.getItemFromBlock(footstep2));
	}
	

	private static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(),"inventory"));
	}
}

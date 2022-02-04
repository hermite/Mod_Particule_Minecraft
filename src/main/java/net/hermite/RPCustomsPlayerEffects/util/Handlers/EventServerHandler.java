package net.hermite.RPCustomsPlayerEffects.util.Handlers;

import java.util.Date;
import java.util.Random;

import net.hermite.RPCustomsPlayerEffects.Main;
import net.hermite.RPCustomsPlayerEffects.Packets.PacketCPPlayerSC;
import net.hermite.RPCustomsPlayerEffects.util.Class.CPPlayer;
import net.hermite.RPCustomsPlayerEffects.util.Class.IFootStep;
import net.hermite.RPCustomsPlayerEffects.util.Class.IStatus;
import net.hermite.RPCustomsPlayerEffects.util.Providers.FootStepProvider;
import net.hermite.RPCustomsPlayerEffects.util.Providers.StatusProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.hermite.RPCustomsPlayerEffects.ClassHandlers.CPPlayerArrayHandler;

@Mod.EventBusSubscriber
public class EventServerHandler {
	
	protected Random rand;
	
	/*@SubscribeEvent
	public void Event(PlayerInteractEvent event) {
		EntityPlayer entity = event.getEntityPlayer();
		float x = (float)Math.random() * 1f - 0.5f;
		float y = (float)Math.random() * 0.5f + 1.2f;
		float z = (float)Math.random() * 1f - 0.5f;
		
		smellyPlayerParticle particule = new smellyPlayerParticle(entity.world,entity.posX + x,entity.posY + y,entity.posZ + z,0,0,0,0);

	    Minecraft.getMinecraft().effectRenderer.addEffect(particule);
        System.out.println("Item picked up!");
        System.out.println(entity.getPosition() + " Position !!!!!!!");
        System.out.println(entity.world.isRainingAt(entity.getPosition()));
    }
	*/
	
	@SubscribeEvent
	public void PlayerTickEvent(TickEvent.PlayerTickEvent e)
	{
		if(e.side.isClient()) return;
		if(e.phase.toString().equals("START")) return;
		
		EntityPlayer p =e.player;
		IStatus status = p.getCapability(StatusProvider.Status, null);
		
		
		CPPlayer CPP = new CPPlayer();
		CPP.setPlayerId(p.getEntityId());
		
		Date d = new Date();
		long lastCleanDate = status.getCleanDate();
		long diffCleanDate = (d.getTime() - lastCleanDate) / 1000;
		
		int GettingWetTimeRain = 300 * 20;
		int GettingWetTimeWater = 30 * 20;
		int WetTime = 3600 * 20;
		int MaxCleanTime = 432000;
		
		
		
		/*if(p.world.getWorldTime()%20 == 0)
		{
	        System.out.println(p.world.getWorldTime());
			System.out.println(p.getName() + " ; " + status.getWetTime() + " ; " + status.getGettingWetTime() + " ; " + status.getDust() + " ; " + status.getScentedTime() + " ; " + status.getCleanDate() + " ; " + e.side);
			System.out.println(p.world.isRainingAt(p.getPosition()) + " ; " + p.world.canSnowAt(p.getPosition(), false) + " ; " + p.isInWater());
			System.out.println(diffCleanDate);
		}*/
		/*if (p.getName().equals("hermite47"))
		{
			p.sendMessage(new TextComponentString("Status : " + p.world.isRainingAt(p.getPosition()) + p.world.canSnowAt(p.getPosition(), false) + p.isInWater()).setStyle(new Style().setColor(TextFormatting.BLUE)));
		}*/
        if(p.world.isRainingAt(p.getPosition()) || /*p.world.canSnowAt(p.getPosition(), false) ||*/ p.isInWater())
		{
	        if (status.getGettingWetTime() > 0)
	        {
	        	if (p.isInWater() && status.getGettingWetTime() > GettingWetTimeWater)
	        	{
	        		status.setGettingWetTime(GettingWetTimeWater);
	        	}
	        	status.consumeGettingWet(1);
	        	if(status.getGettingWetTime() == 0)
	        	{
	        		status.setWetTime(WetTime);
	        		p.sendMessage(new TextComponentString("Vous etes trempe !").setStyle(new Style().setColor(TextFormatting.BLUE)));
	        		
	        	}
	        }
	        else if ( status.getWetTime() == 0)
	        {
	        	if(p.isInWater())
	        	{
	        		status.setGettingWetTime(GettingWetTimeWater);
	        	}
	        	else
	        	{
	        		status.setGettingWetTime(GettingWetTimeRain);
	        	}
	        }
	        else if (status.getWetTime() > 0 && status.getWetTime() < WetTime)
	        {
	        	status.fillWet(1);
	        	status.setScentedTime(0);
	        	
	        }
	        else
	        {
	        	status.setScentedTime(0);
	        }
	    }
        else
        {
        	if(status.getGettingWetTime() > 0)
        	{
        		status.fillGettingWet(1);
        		if (status.getGettingWetTime() >= GettingWetTimeRain)
        		{
        			status.setGettingWetTime(0);
        		}
        	}
        	else
        	{
        		status.consumeWet(1);
        	}
        }
                
        if(diffCleanDate > MaxCleanTime)
    	{
			status.setDust(true);
    	}
        
        status.consumeScented(1);
        
        if(p.world.getWorldTime()%60 == 0)
        {
        	//System.out.println(p.getName() + " ; " + status.getWetTime()+ " ; " + status.getScentedTime()+ " ; " + status.getDust()+ " ; " + status.getGettingWetTime());
        	
	        if(status.getWetTime() > 0 )
	        {
	        	CPP.setWet(true);
	        	CPPlayerArrayHandler.ReplaceOrAdd(CPP, Main.CPPlayerList);
	        	PacketHandler.INSTANCE.sendToAll(new PacketCPPlayerSC(p.getEntityId(),1));
	        	/*float x = (float)Math.random() * 1f - 0.5f;
	    		float y = (float)Math.random() * 0.5f + 1.2f;
	    		float z = (float)Math.random() * 1f - 0.5f;
	    		
	    		wetPlayerParticle particule = new wetPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
	    		Minecraft.getMinecraft().effectRenderer.addEffect(particule);*/
	        }
	        else if (status.getScentedTime() > 0 )
	        {
	        	CPP.setPerfum(true);
	        	CPPlayerArrayHandler.ReplaceOrAdd(CPP, Main.CPPlayerList);
	        	PacketHandler.INSTANCE.sendToAll(new PacketCPPlayerSC(p.getEntityId(),3));
	        	/*float x = (float)Math.random() * 1f - 0.5f;
	    		float y = (float)Math.random() * 0.5f + 1.2f;
	    		float z = (float)Math.random() * 1f - 0.5f;
	    		
	    		perfumePlayerParticle particule = new perfumePlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
	    		Minecraft.getMinecraft().effectRenderer.addEffect(particule);*/
	        }
	        else if (status.getDust() )
	        {
	        	CPP.setSmell(true);
	        	CPPlayerArrayHandler.ReplaceOrAdd(CPP, Main.CPPlayerList);
	        	PacketHandler.INSTANCE.sendToAll(new PacketCPPlayerSC(p.getEntityId(),2));
	        	/*float x = (float)Math.random() * 1f - 0.5f;
	    		float y = (float)Math.random() * 0.5f + 1.2f;
	    		float z = (float)Math.random() * 1f - 0.5f;
	    		
	    		smellyPlayerParticle particule = new smellyPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
	    		Minecraft.getMinecraft().effectRenderer.addEffect(particule);*/
	        }
	        else 
	        {
	        	CPPlayerArrayHandler.ReplaceOrAdd(CPP, Main.CPPlayerList);
	        	PacketHandler.INSTANCE.sendToAll(new PacketCPPlayerSC(p.getEntityId(),4));
	        }
        }
        
        // Partie 2 : Génération FootStep
       
        
	}	
	
	@SubscribeEvent
	public void WorldTickEvent(TickEvent.WorldTickEvent e)
	{
		if (e.world.getWorldTime()%30 == 0)
        {
			IFootStep FootStep = e.world.getCapability(FootStepProvider.FootStep, null);
	        FootStep.RemoveFootStepExpired(e.world);
        }
	}
	
	
	@SubscribeEvent
	public void PlayerLogin(PlayerLoggedInEvent e)
	{
		EntityPlayer p =e.player;
		IStatus status = p.getCapability(StatusProvider.Status, null);
		Date d = new Date();
		long lastConnect = status.getLastLoggoutDate();
		long diff = (d.getTime() - lastConnect) / (1000*60*60);
		CPPlayer CPP = new CPPlayer();
		CPP.setPlayerId(p.getEntityId());
		int action = 0;
		
		//System.out.println(d.getTime() + " ; " + lastConnect + " ; " + diff);
		if (diff > (3*24))
		{
			status.setGettingWetTime(0);
			status.setWetTime(0);
		}
		
		if (diff > 5)
		{
			status.setScentedTime(0);
		}

		//System.out.println("AFFICHAGE :: " +status.getWetTime() + " ; " + status.getScentedTime() + " ; " + status.getDust());
		if(status.getWetTime() > 0)
        {
        	CPP.setWet(true);
        	/*float x = (float)Math.random() * 1f - 0.5f;
    		float y = (float)Math.random() * 0.5f + 1.2f;
    		float z = (float)Math.random() * 1f - 0.5f;
    		
    		wetPlayerParticle particule = new wetPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particule);*/
        }
        else if (status.getScentedTime() > 0 )
        {
        	CPP.setPerfum(true);
        	/*float x = (float)Math.random() * 1f - 0.5f;
    		float y = (float)Math.random() * 0.5f + 1.2f;
    		float z = (float)Math.random() * 1f - 0.5f;
    		
    		perfumePlayerParticle particule = new perfumePlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particule);*/
        }
        else if (status.getDust())
        {
        	CPP.setSmell(true);
        	/*float x = (float)Math.random() * 1f - 0.5f;
    		float y = (float)Math.random() * 0.5f + 1.2f;
    		float z = (float)Math.random() * 1f - 0.5f;
    		
    		smellyPlayerParticle particule = new smellyPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particule);*/
        }


    	CPPlayerArrayHandler.ReplaceOrAdd(CPP, Main.CPPlayerList);
    	//System.out.println("AJOUTE LISTE :: " +p.getEntityId() + " ; " + action);
		
		
		int ArrayListHKPlayerSize = Main.CPPlayerList.size();
		int PlayerId = 0;

    	//System.out.println("Envoi liste  :: " +ArrayListHKPlayerSize );
	 	for(int i=0;i<ArrayListHKPlayerSize;i++)
	 	{
	 		if (Main.CPPlayerList.get(i) != null)
	 		{
	 			
	 			PlayerId = Main.CPPlayerList.get(i).getPlayerId();
	 			action = CPPlayerArrayHandler.getEffect(PlayerId, Main.CPPlayerList);
	 			//System.out.println("Envoi liste"+i+"  :: " +PlayerId + " ; " + action );
	 	 		PacketHandler.INSTANCE.sendTo(new PacketCPPlayerSC(PlayerId,action), (EntityPlayerMP)p);
	 			//System.out.println("Envoi liste 2 :"+i+"  :: " +PlayerId + " ; " + action );
	 		}
	 	}
	 	//System.out.println("FIN Envoi liste  :: " +ArrayListHKPlayerSize );
		
	}
	
	
	@SubscribeEvent
	public void PlayerLogout(PlayerLoggedOutEvent e)
	{
		EntityPlayer p =e.player;
		IStatus status = p.getCapability(StatusProvider.Status, null);
		
		status.setLastLoggoutDate(new Date().getTime());
	}
	
		
	@SubscribeEvent
	public void PlayerRightClickBlock(PlayerInteractEvent.RightClickBlock e)
	{
		if(e.getSide().isClient()) return;
		
		EntityPlayer p = e.getEntityPlayer();
		String BlockName = p.world.getBlockState(e.getPos()).getBlock().getRegistryName().toString();
		String BlockDownName = p.world.getBlockState(e.getPos().down()).getBlock().getRegistryName().toString();
		String ItemName = e.getItemStack().getItem().getRegistryName().toString();
		
		if(((ItemName.equals("am:balai") || ItemName.equals("am:serviette") || ItemName.equals("am:petite_serviette") ) && ( BlockName.equals("am:tache_de_boue") || BlockName.equals("am:tache_d_eau")|| BlockName.equals("rpcpemod:footstep2")|| BlockName.equals("rpcpemod:footstep1") || BlockName.equals("rpcpemod:footstep0"))) || ((ItemName.equals("minecraft:diamond_hoe") || ItemName.equals("minecraft:wooden_hoe") || ItemName.equals("minecraft:stone_hoe") || ItemName.equals("minecraft:iron_hoe")) && (BlockName.equals("am:tache_de_boue") || BlockName.equals("am:tache_de_eau") || BlockName.equals("rpcpemod:footstep0") || BlockName.equals("rpcpemod:footstep1") || BlockName.equals("rpcpemod:footstep2")) && (BlockDownName.equals("minecraft:dirt") || BlockDownName.equals("minecraft:grass")) ))
		{
			IFootStep FootStep = e.getWorld().getCapability(FootStepProvider.FootStep, null);
			FootStep.remove(e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), e.getWorld());
			e.getItemStack().setItemDamage(e.getItemStack().getItemDamage()+1);
			if(e.getItemStack().getItemDamage() >= e.getItemStack().getMaxDamage())
			{
				e.getItemStack().setCount(-1);
			}
		}
		
	}
	
	@SubscribeEvent
	public void PlayerRightClickBlock(PlayerInteractEvent.RightClickItem e)
	{
		if(e.getSide().isClient()) return;
		
		int ScentedTime = 18000*20;
		
		EntityPlayer p = e.getEntityPlayer();
		IStatus status = p.getCapability(StatusProvider.Status, null);
		
		if(e.getItemStack().getItem().getRegistryName().toString().equals("am:savon"))
		{
			//e.getItemStack().setItemDamage(e.getItemStack().getItemDamage()+1);
			p.sendMessage(new TextComponentString("Vous venez de vous laver").setStyle(new Style().setColor(TextFormatting.AQUA)));
			status.setDust(false);
			status.setCleanDate(new Date().getTime());
			if(e.getItemStack().getItemDamage() >= e.getItemStack().getMaxDamage())
			{
				e.getItemStack().setCount(-1);
			}
		}
		else if((e.getItemStack().getItem().getRegistryName().toString().equals("am:serviette") ||e.getItemStack().getItem().getRegistryName().toString().equals("am:petite_serviette")))
		{
			if( status.getWetTime() > 0)
			{
				p.sendMessage(new TextComponentString("Vous venez de vous secher").setStyle(new Style().setColor(TextFormatting.BLUE)));
				status.setWetTime(0);
				e.getItemStack().setItemDamage(e.getItemStack().getItemDamage()+1);
				if(e.getItemStack().getItemDamage() >= e.getItemStack().getMaxDamage())
				{
					e.getItemStack().setCount(-1);
				}
			}
			/*else
			{
				p.sendMessage(new TextComponentString("Vous etes deja tout sec !").setStyle(new Style().setColor(TextFormatting.BLUE)));
			}*/
		}
		else if(e.getItemStack().getItem().getRegistryName().toString().equals("am:bouteille_de_parfum"))
		{
			if( status.getScentedTime() == 0)
			{
				e.getItemStack().setItemDamage(e.getItemStack().getItemDamage()+1);
				p.sendMessage(new TextComponentString("Vous venez de vous parfumer").setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
				status.setScentedTime(ScentedTime);
				if(e.getItemStack().getItemDamage() >= e.getItemStack().getMaxDamage())
				{
					e.getItemStack().setCount(-1);
				}
			}
			else
			{
				p.sendMessage(new TextComponentString("Vous sentez deja tres bon !").setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
			}
		}
		
	}
	
	@SubscribeEvent
	public void BreakBlock(BlockEvent.BreakEvent e)
	{
		
		String BlockName = e.getWorld().getBlockState(e.getPos().up()).getBlock().getRegistryName().toString();
		//System.out.println(e.getPos().getX()+ " ; " + e.getPos().getY()+ " ; " + e.getPos().getZ());
		
		if(BlockName.equals("am:tache_de_boue") || BlockName.equals("am:tache_de_eau") || BlockName.equals("rpcpemod:footstep0") || BlockName.equals("rpcpemod:footstep1") || BlockName.equals("rpcpemod:footstep2"))
		{
			IFootStep FootStep = e.getWorld().getCapability(FootStepProvider.FootStep, null);
			FootStep.remove(e.getPos().getX(), e.getPos().getY() +1, e.getPos().getZ(), e.getWorld());
		}
	}
}

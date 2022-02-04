package net.hermite.RPCustomsPlayerEffects.util.Handlers;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.Date;
import java.util.List;

import net.hermite.RPCustomsPlayerEffects.Main;
import net.hermite.RPCustomsPlayerEffects.ClassHandlers.CPPlayerArrayHandler;
import net.hermite.RPCustomsPlayerEffects.Packets.PacketFootStepGenCS;
import net.hermite.RPCustomsPlayerEffects.Particules.perfumePlayerParticle;
import net.hermite.RPCustomsPlayerEffects.Particules.smellyPlayerParticle;
import net.hermite.RPCustomsPlayerEffects.Particules.wetPlayerParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public class EventClientHandler {
	/*@SubscribeEvent
	public void PlayerTickEvent(TickEvent.PlayerTickEvent e)
	{
		if(e.side.isServer()) return;//Si l'event a lieu sur le client, on stoppe le traitement ( sinon problème de doublon des variables )
		if(e.phase.toString().equals("START")) return;
		
		EntityPlayer p = e.player;
		int PlayerAction = CPPlayerArrayHandler.getEffect(p.getEntityId(), Main.CPPlayerList);
		
		float x = 0;
		float y = 0;
		float z = 0;
		if(p.world.getWorldTime()%80 == 0)
        {
			System.out.println(p.getName() + " ; " + PlayerAction + " : " + e.side);
			switch (PlayerAction)
			{
				case 1:
					x = (float)Math.random() * 1f - 0.5f;
		    		y = (float)Math.random() * 0.5f + 1.2f;
		    		z = (float)Math.random() * 1f - 0.5f;
		    		
		    		wetPlayerParticle particule = new wetPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
		    		Minecraft.getMinecraft().effectRenderer.addEffect(particule);
				break;
				case 2:
					x = (float)Math.random() * 1f - 0.5f;
		    		y = (float)Math.random() * 0.5f + 1.2f;
		    		z = (float)Math.random() * 1f - 0.5f;
		    		
		    		smellyPlayerParticle particule2 = new smellyPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
		    		Minecraft.getMinecraft().effectRenderer.addEffect(particule2);
				break;
				case 3:
					x = (float)Math.random() * 1f - 0.5f;
		    		y = (float)Math.random() * 0.5f + 1.2f;
		    		z = (float)Math.random() * 1f - 0.5f;
		    		
		    		perfumePlayerParticle particule3 = new perfumePlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
		    		Minecraft.getMinecraft().effectRenderer.addEffect(particule3);
				break;
				default:
			}
        }
	}*/
	
	
	@SubscribeEvent
	public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent e)
	{
		Date d = new Date();
		Entity p = e.getEntity();
								
		if(p instanceof EntityPlayer && ((d.getTime() - Main.LastEvent) > 2000 || !Main.WaitEvent))
	    {
			//System.out.println(d.getSeconds());
			if((d.getTime() - Main.LastEvent) > 2000)
			{
				Main.LastEvent = d.getTime();
				Main.LastPlayerEvent = p.getEntityId();
				Main.WaitEvent = false;
			}
			else if(Main.LastPlayerEvent == p.getEntityId())
			{
				Main.WaitEvent = true;
				return;
			}
					
			int PlayerAction = CPPlayerArrayHandler.getEffect(p.getEntityId(), Main.CPPlayerList);
					
			float x = 0;
			float y = 0;
			float z = 0;
			//System.out.println(p.getEntityId() + " ; " + p.getName() + " ; " + PlayerAction);
			//p.sendMessage(new TextComponentString(p.getEntityId() + " ; " + p.getName() + " ; " + PlayerAction + " ; " ).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
			switch (PlayerAction)
			{
				case 1:
					x = (float)Math.random() * 1f - 0.5f;
				    y = (float)Math.random() * 0.5f + 1.2f;
				    z = (float)Math.random() * 1f - 0.5f;
				    		
				    wetPlayerParticle particule = new wetPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
				    Minecraft.getMinecraft().effectRenderer.addEffect(particule);
				break;
				case 2:
					x = (float)Math.random() * 1f - 0.5f;
				    y = (float)Math.random() * 0.5f + 1.2f;
				    z = (float)Math.random() * 1f - 0.5f;
				    		
				    smellyPlayerParticle particule2 = new smellyPlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
				    Minecraft.getMinecraft().effectRenderer.addEffect(particule2);
				break;
				case 3:
					x = (float)Math.random() * 1f - 0.5f;
				    y = (float)Math.random() * 0.5f + 1.2f;
				    z = (float)Math.random() * 1f - 0.5f;
				    		
				    perfumePlayerParticle particule3 = new perfumePlayerParticle(p.world,p.posX + x,p.posY + y,p.posZ + z,0,0,0,0);
				    Minecraft.getMinecraft().effectRenderer.addEffect(particule3);
				break;
				default:
			} 
			//System.out.println(Minecraft.getMinecraft().player.capabilities.isCreativeMode);
			if (!Minecraft.getMinecraft().isSingleplayer())
			{
				if(p.getName() == Minecraft.getMinecraft().player.getName() && !(Minecraft.getMinecraft().player.capabilities.isCreativeMode || Minecraft.getMinecraft().player.capabilities.isFlying) )
				{
					PacketHandler.INSTANCE.sendToServer(new PacketFootStepGenCS(1));				
				}
			}
	    }
	}
	
}

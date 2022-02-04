package net.hermite.RPCustomsPlayerEffects.Packets;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import net.hermite.RPCustomsPlayerEffects.util.Class.IFootStep;
import net.hermite.RPCustomsPlayerEffects.util.Class.IStatus;
import net.hermite.RPCustomsPlayerEffects.util.Providers.FootStepProvider;
import net.hermite.RPCustomsPlayerEffects.util.Providers.StatusProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFootStepGenCS implements IMessage {
		  // A default constructor is always required
		  public PacketFootStepGenCS(){}
		  //action (1 debout, 2 assis, 3 couché )
		  int action;

		  public PacketFootStepGenCS(int action){
			  this.action = action;
		  }
		  
		  @Override public void toBytes(ByteBuf buf) {
		    // Writes the int into the buf
		    buf.writeInt(action);
		  }

		 @Override public void fromBytes(ByteBuf buf) {
		    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
			 action = buf.readInt();
		 }
		 
		 public static class Handler implements IMessageHandler<PacketFootStepGenCS, IMessage> 
		 {
		 		  // Do note that the default constructor is required, but implicitly defined in this case

		 	@Override 
		 	public IMessage onMessage(PacketFootStepGenCS HKPlayer, MessageContext ctx) 
		 	{
		 		// This is the player the packet was sent to the server from
		 		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> HKhandle(HKPlayer, ctx));
		 		
		 		return null;
		 	}
		 	
		 	private void HKhandle (PacketFootStepGenCS Player, MessageContext ctx)
		 	{
		 		// The value that was sent
		 		EntityPlayer p = ctx.getServerHandler().player;
		 		if(p.capabilities.isCreativeMode || p.capabilities.isFlying)
		 			return;
		 		
		 		Date d = new Date();
				IStatus status = p.getCapability(StatusProvider.Status, null);
				
		 			IFootStep FootStep = p.world.getCapability(FootStepProvider.FootStep, null);
		         	//System.out.println(p.getEntityId()+ " ; "+p.getName()+ " ; " + status.getGettingWetTime() + " ; " + status.getDust()+ " ; " + status.getScentedTime()+ " ; " +status.getWetTime() + " ; " + status.getCleanDate()+ " ; " + d.getTime() +  " : " );
		         	Block b = p.world.getBlockState(p.getPosition()).getBlock();
		         	Block b2 = p.world.getBlockState(p.getPosition().down()).getBlock();
		         	
		         	BlockPos pos = p.getPosition().up();
		 			BlockPos pos2 = p.getPosition();
		 			
		 			if (b2.getRegistryName().toString().equals("minecraft:air") || b2.getRegistryName().toString().equals("minecraft:tallgrass"))
		 			{
		 				while (b2.getRegistryName().toString().equals("minecraft:air") || b2.getRegistryName().toString().equals("minecraft:tallgrass"))
		 				{
		 					pos = pos.down();
		 					pos2 = pos2.down();
		 					b = p.world.getBlockState(pos).getBlock();
		 					b2 = p.world.getBlockState(pos2).getBlock();
		 				}
		 			}
		 			else
		 			{
		 				pos = pos.down();
		 				pos2 = pos2.down();
		 			}
		 			
		         	if(status.getWetTime() > 0)
		         	{
		 	        	if (b2.getRegistryName().toString().equals("minecraft:dirt") || b2.getRegistryName().toString().equals("minecraft:sand") || b2.getRegistryName().toString().equals("minecraft:grass") || b2.getRegistryName().toString().equals("minecraft:soul_sand"))
		 	        	{
		 	        		//System.out.println("test");
		 		        	if(b.getRegistryName().toString().equals("minecraft:air") || b.getRegistryName().toString().equals("minecraft:tallgrass") || b.getRegistryName().toString().equals("minecraft:flower"))
		 		        	{
		 		        		p.world.setBlockState(pos, Block.getBlockFromName("am:tache_de_boue").getDefaultState());
		 		        		FootStep.add(pos.getX(), pos.getY(), pos.getZ(), new Date().getTime(),259200);
		 		        	}
		 	        	}
		 	        	else if (!(p.world.isRainingAt(p.getPosition()) /*|| p.world.canSnowAt(p.getPosition(), false)*/ ) && !b2.getRegistryName().toString().equals("am:tache_de_boue") && !b2.getRegistryName().toString().equals("am:tache_d_eau") && !b2.getRegistryName().toString().equals("minecraft:water"))
		 	        	{
		 	        		if(b.getRegistryName().toString().equals("minecraft:air") || b.getRegistryName().toString().equals("minecraft:tallgrass"))
		 	            	{
		 	            		if(status.getDust())
		 	            		{
		 	            			p.world.setBlockState(pos, Block.getBlockFromName("am:tache_de_boue").getDefaultState());
		 	            			FootStep.add(pos.getX(), pos.getY(), pos.getZ(), new Date().getTime(),259200);
		 	            		}
		 	            		else
		 	            		{
		 	            			p.world.setBlockState(pos, Block.getBlockFromName("am:tache_d_eau").getDefaultState());
		 	            			FootStep.add(pos.getX(), pos.getY(), pos.getZ(), new Date().getTime(),259200);
		 	            		}
		 	            	}
		 	        	}
		         	}
		         	else
		         	{
		         		if (b2.getRegistryName().toString().equals("minecraft:dirt") || b2.getRegistryName().toString().equals("minecraft:sand") || b2.getRegistryName().toString().equals("minecraft:grass") || b2.getRegistryName().toString().equals("minecraft:soul_sand")|| b2.getRegistryName().toString().equals("minecraft:gravel"))
		 	        	{
		         			if(b.getRegistryName().toString().equals("minecraft:air") || b.getRegistryName().toString().equals("minecraft:tallgrass"))
		 	            	{
		         				int random_int = (int)Math.floor(Math.random()*3);
		             			p.world.setBlockState(pos, Block.getBlockFromName("rpcpemod:footstep"+random_int).getDefaultState());
		             			int time = 1200;
		             			switch(b2.getRegistryName().toString()) {
		             			  case "minecraft:dirt":
		             				time = 14400;
		             			    break;
		             			  case "minecraft:grass":
		             				  time = 3600;
		             			    break;
		             			  case "minecraft:sand":
		             				  time = 14400;
		               			    break;
		             			  case "minecraft:soul_sand":
		             				  	time = 3600;
		                 			    break;
		             			  case "minecraft:gravel":
		             				  	time = 1200;
		                 			    break;
		             			  default:
		             			    // code block
		             			}
		             			FootStep.add(pos.getX(), pos.getY(), pos.getZ(), new Date().getTime(),time);
		 	            	}
		 	        	}
		         	
		         }
		 		
		 	}
		 }
}

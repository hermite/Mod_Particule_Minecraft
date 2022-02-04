package net.hermite.RPCustomsPlayerEffects.Packets;

import io.netty.buffer.ByteBuf;
import net.hermite.RPCustomsPlayerEffects.util.Class.CPPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.hermite.RPCustomsPlayerEffects.Main;
import net.hermite.RPCustomsPlayerEffects.ClassHandlers.CPPlayerArrayHandler;

public class PacketCPPlayerSC implements IMessage {
		  // A default constructor is always required
		  public PacketCPPlayerSC(){}
		  
		  private int PlayerId;
		  int action;

		  public PacketCPPlayerSC(int PlayerId,int action){
			  this.PlayerId = PlayerId;
			  this.action = action;
		  }
		  
		  @Override public void toBytes(ByteBuf buf) {
		    // Writes the int into the buf
			buf.writeInt(PlayerId);
		    buf.writeInt(action);
		  }

		 @Override public void fromBytes(ByteBuf buf) {
		    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
			 PlayerId = buf.readInt();
			 action = buf.readInt();
		 }
		 
		 public static class Handler implements IMessageHandler<PacketCPPlayerSC, IMessage> 
		 {
		 		  // Do note that the default constructor is required, but implicitly defined in this case

		 	@Override 
		 	public IMessage onMessage(PacketCPPlayerSC CPPlayer, MessageContext ctx) 
		 	{
		 		// This is the player the packet was sent to the server from
		 		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> HKhandle(CPPlayer, ctx));
		 		
		 		return null;
		 	}
		 	
		 	private void HKhandle (PacketCPPlayerSC Player, MessageContext ctx)
		 	{
		 		// The value that was sent
		 		int PlayerId = Player.PlayerId;
		 		int action = Player.action;
		 		    
		 		CPPlayer CPP = new CPPlayer();

		 		CPP.setPlayerId(PlayerId);
		 		CPP.setWet(false);
		 		CPP.setSmell(false);
		 		CPP.setPerfum(false);
		 		    
		 		switch(action)
		 		{		
		 		    case 1:
		 		    	CPP.setWet(true);
		 		    break;
		 		    	
		 		    case 2:
		 		    	CPP.setSmell(true);
		 		    break;
		 		    
		 		   case 3:
		 		    	CPP.setPerfum(true);
		 		    break;		 		    	
		 		}
		 		CPPlayerArrayHandler.ReplaceOrAdd(CPP, Main.CPPlayerList);
		 		    

		 		
		 	}
		 }
}

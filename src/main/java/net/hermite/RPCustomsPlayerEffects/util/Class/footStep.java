package net.hermite.RPCustomsPlayerEffects.util.Class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.unimi.dsi.fastutil.ints.IntArrays;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class footStep  implements IFootStep
{
		//A partir de l'interface IStatus, définir les opérations de chaque fonctions
	
		//Création des variables
		private ArrayList<footStepSaveClass> footStepBlocks = new ArrayList<footStepSaveClass>();
	    
	    //Fonctions gestion du status "Mouillé" du personnage
	   
		
		 @Override
		 public ArrayList<footStepSaveClass> getList()
		 {
		     return this.footStepBlocks;
		 }
		
		
		@Override
	    public void add(int x, int y, int z, long CurrentTime, long Delay)
	    {
	        this.footStepBlocks.add(new footStepSaveClass(x,y,z,CurrentTime, Delay));
	    }
		
		@Override
		public void remove(int x, int y, int z, World w)
	    {
			
			w.setBlockToAir(new BlockPos(x,y, z));
			
			 for (int i = 0; i < this.footStepBlocks.size(); i++) {
				 if(this.footStepBlocks.get(i).getX() == x && this.footStepBlocks.get(i).getY() == y && this.footStepBlocks.get(i).getZ() == z)
					 this.footStepBlocks.remove(i);
		     }
	    }
		
		@Override
		public void RemoveFootStepExpired(World w)
		{
			Date d = new Date();
			long timeFootStep = d.getTime();
			long diffTimeFootStep = timeFootStep;
			if(this.footStepBlocks != null && this.footStepBlocks.size() > 0)
			{
				for (int i = 0; i < this.footStepBlocks.size(); i++) {
					timeFootStep = this.footStepBlocks.get(i).getCreateTime();
					diffTimeFootStep = (d.getTime() - timeFootStep) / (1000);
					if (diffTimeFootStep > this.footStepBlocks.get(i).getDelay())
					{
						String BlockName = w.getBlockState(new BlockPos(this.footStepBlocks.get(i).getX(), this.footStepBlocks.get(i).getY(), this.footStepBlocks.get(i).getZ())).getBlock().getRegistryName().toString();
						if(BlockName.equals("am:tache_de_boue") || BlockName.equals("am:tache_d_eau")|| BlockName.equals("rpcpemod:footstep2")|| BlockName.equals("rpcpemod:footstep1") || BlockName.equals("rpcpemod:footstep0"))
						{
							w.setBlockToAir(new BlockPos(this.footStepBlocks.get(i).getX(), this.footStepBlocks.get(i).getY(), this.footStepBlocks.get(i).getZ()));
						}
						this.footStepBlocks.remove(i);
					}
			    }
			}
		}
		
		
}


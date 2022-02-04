package net.hermite.RPCustomsPlayerEffects.util;

import java.util.ArrayList;

import net.hermite.RPCustomsPlayerEffects.util.Class.IFootStep;
import net.hermite.RPCustomsPlayerEffects.util.Class.IStatus;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.hermite.RPCustomsPlayerEffects.util.Class.footStepSaveClass;

public class FootStepStorage implements IStorage<IFootStep>
{
	
//Sert a sauvegarder les données sur le player, pour rien perdre lors de la déconnexion
	@Override
	public NBTBase writeNBT(Capability<IFootStep> capability, IFootStep instance, EnumFacing side)
	{
	 	NBTTagList list = new NBTTagList();
	 	ArrayList<footStepSaveClass> FootStepList = instance.getList();
	 	for (int i = 0; i < FootStepList.size(); i++)
	 	{
	 		NBTTagCompound tag = new NBTTagCompound();
	 		tag.setInteger("x", FootStepList.get(i).getX());
	 		tag.setInteger("y", FootStepList.get(i).getY());
	 		tag.setInteger("z", FootStepList.get(i).getZ());
	 		tag.setLong("CreateTime", FootStepList.get(i).getCreateTime());
	 		tag.setLong("Delay", FootStepList.get(i).getDelay());
	 		list.appendTag(tag);
	 	}
	 	return list;
	}

	@Override
	public void readNBT(Capability<IFootStep> capability, IFootStep instance, EnumFacing side, NBTBase nbt)
	{
		 //if (nbt instanceof NBTTagList)
	     //{
			 System.out.println("i2=lance");
			 NBTTagList list = (NBTTagList) nbt;
			 System.out.println("i2=list" + list.tagCount());
			 for (int i = 0; i < list.tagCount(); i++)
			 {
				NBTTagCompound tag = new NBTTagCompound();
			 	tag = (NBTTagCompound) list.get(i);
			 	instance.add(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"), tag.getLong("CreateTime"), tag.getLong("Delay"));
			 }
	     //}
	}
	
}

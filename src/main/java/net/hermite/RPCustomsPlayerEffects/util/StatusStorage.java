package net.hermite.RPCustomsPlayerEffects.util;

import net.hermite.RPCustomsPlayerEffects.util.Class.IStatus;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class StatusStorage	implements IStorage<IStatus>
{
	
//Sert a sauvegarder les données sur le player, pour rien perdre lors de la déconnexion
	@Override
	public NBTBase writeNBT(Capability<IStatus> capability, IStatus instance, EnumFacing side)
	{
	 	NBTTagCompound tag = new NBTTagCompound();
	 	tag.setInteger("wetTime", instance.getWetTime());
	 	tag.setInteger("gettingWetTime", instance.getGettingWetTime());
	 	tag.setBoolean("dust", instance.getDust());
	 	tag.setInteger("scentedTime", instance.getScentedTime());
	 	tag.setLong("cleanDate", instance.getCleanDate());
	 	
	 	tag.setLong("lastLoggout", instance.getLastLoggoutDate());
	 	return tag;
	}

	@Override
	public void readNBT(Capability<IStatus> capability, IStatus instance, EnumFacing side, NBTBase nbt)
	{
		 if (nbt instanceof NBTTagCompound)
	     {
			 	NBTTagCompound tag = (NBTTagCompound) nbt;
				instance.setWetTime(tag.getInteger("wetTime"));
				instance.setGettingWetTime(tag.getInteger("gettingWetTime"));
				instance.setDust(tag.getBoolean("dust"));
				instance.setScentedTime(tag.getInteger("scentedTime"));
				instance.setCleanDate(tag.getLong("cleanDate"));
				instance.setLastLoggoutDate(tag.getLong("lastLoggout"));
	     }
	}
	
}

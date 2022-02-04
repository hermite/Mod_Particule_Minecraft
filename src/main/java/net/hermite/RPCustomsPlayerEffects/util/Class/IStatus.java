package net.hermite.RPCustomsPlayerEffects.util.Class;

import java.text.DateFormat;
import java.util.Date;

import net.minecraft.nbt.NBTTagIntArray;

public interface IStatus {
	//Définition des fonctions de gestions des différents status : meilleurs explications dans Status.java
	public void consumeWet(int points);
	public void fillWet(int points);
	public void setWetTime(int points);
	public int getWetTime();
	
	public void consumeGettingWet(int points);
	public void fillGettingWet(int points);
	public void setGettingWetTime(int points);
	public int getGettingWetTime();
	
	public void setDust(boolean points);
	public boolean getDust();
		
	public void consumeScented(int points);
	public void fillScented(int points);
	public void setScentedTime(int points);
	public int getScentedTime();

	public void setCleanDate(long points);
	public long getCleanDate();
	
	public void setLastLoggoutDate(long l);
	public long getLastLoggoutDate();
		
}

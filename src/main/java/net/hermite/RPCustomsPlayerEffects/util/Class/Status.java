package net.hermite.RPCustomsPlayerEffects.util.Class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unimi.dsi.fastutil.ints.IntArrays;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;

public class Status  implements IStatus
{
		//A partir de l'interface IStatus, définir les opérations de chaque fonctions
	
		//Création des variables
	    private int wetTime = 0;//Temps décompté pour définir si le personnage est mouillé
	    private int gettingWetTime = 0;//Temps décompté pour définir si le personnage est en train de se mouiller
	    private boolean isDust = false; // définit si le personnage est salle ou non.
	    private int scentedTime = 0; //Temps décompté pendant lequel le personnage est parfumé
	    private long cleanDate = 0;//Date de dernier lavage du personnage
	    private long lastLoggoutDate = 0;//Date de dernière déconnexion du personnage
	    
	    
	    //Fonctions gestion du status "Mouillé" du personnage
	    @Override
	    public void consumeWet(int points)
	    {
	        this.wetTime -= points;

	        if (this.wetTime < 0) this.wetTime = 0;
	    }

	    @Override
	    public void fillWet(int points)
	    {
	        this.wetTime += points;
	    }

	    @Override
	    public void setWetTime(int points)
	    {
	        this.wetTime = points;
	    }

	    @Override
	    public int getWetTime()
	    {
	        return this.wetTime;
	    }
	    
	    
	    
	    
	    
	  //Fonctions gestion du status "En train de se mouiller" du personnage
	    @Override
	    public void consumeGettingWet(int points)
	    {
	        this.gettingWetTime -= points;

	        if (this.gettingWetTime < 0) this.gettingWetTime = 0;
	    }

	    @Override
	    public void fillGettingWet(int points)
	    {
	        this.gettingWetTime += points;
	    }

	    @Override
	    public void setGettingWetTime(int points)
	    {
	        this.gettingWetTime = points;
	    }

	    @Override
	    public int getGettingWetTime()
	    {
	        return this.gettingWetTime;
	    }
	    
	    

	  //Fonctions gestion du status "Sale" du personnage
	    @Override
	    public void setDust(boolean points)
	    {
	        this.isDust = points;
	    }

	    @Override
	    public boolean getDust()
	    {
	        return this.isDust;
	    }
	    
	    
	    
	    
	    
	    
	    
	  //Fonctions gestion de la dernière date de lavage du personnage
	    @Override
	    public void setCleanDate(long points)
	    {
	        this.cleanDate = points;
	    }

	    @Override
	    public long getCleanDate()
	    {
	        return this.cleanDate;
	    }
	    
	    
	    
	    
	    
	    
	  //Fonctions gestion du status "Parfumé" du personnage
	    @Override
	    public void consumeScented(int points)
	    {
	        this.scentedTime -= points;

	        if (this.scentedTime < 0) this.scentedTime = 0;
	    }

	    @Override
	    public void fillScented(int points)
	    {
	        this.scentedTime += points;
	    }

	    @Override
	    public void setScentedTime(int points)
	    {
	        this.scentedTime = points;
	    }

	    @Override
	    public int getScentedTime()
	    {
	        return this.scentedTime;
	    }
	    
	    
	    
	    
	    
	  //Fonctions gestion de la date de dernière déconnexion
	    @Override
	    public void setLastLoggoutDate(long l)
	    {
	    	this.lastLoggoutDate = l;
	    }
	    
	    @Override
		public long getLastLoggoutDate()
	    {   	
	    	return this.lastLoggoutDate;
	    }
}


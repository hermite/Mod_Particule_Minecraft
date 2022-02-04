package net.hermite.RPCustomsPlayerEffects.util.Class;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IFootStep {
	//Définition des fonctions de gestions des différents status : meilleurs explications dans Status.java
	void add(int x, int y, int z, long CurrentTime, long Delay);

	void remove(int x, int y, int z, World w);

	void RemoveFootStepExpired(World w);


	ArrayList<footStepSaveClass> getList();
		
}

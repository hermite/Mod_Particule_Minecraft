package net.hermite.RPCustomsPlayerEffects.util.Class;

public class CPPlayer {
	
	int player;
	Boolean Wet = false;
	Boolean Smell = false;
	Boolean Perfum = false;
	
	public CPPlayer()
	{
		this.player = -1;
		this.Wet = false;
		this.Smell = false;
		this.Perfum = false;
	}
	
	public boolean isWet()
	{
		return this.Wet;
	}
	
	public boolean isSmell()
	{
		return this.Smell;
	}

	public boolean isPerfum()
	{
		return this.Perfum;
	}
	
	public void setWet(boolean b)
	{
		this.Wet = b;
	}
	
	public void setSmell(boolean b)
	{
		this.Smell = b;
	}
	
	public void setPerfum(boolean b)
	{
		this.Perfum = b;
	}
		
	public int getPlayerId()
	{
		return this.player;
	}
	
	public void setPlayerId(int PID)
	{
		this.player = PID;
	}
}

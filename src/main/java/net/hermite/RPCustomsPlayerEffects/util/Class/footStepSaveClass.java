package net.hermite.RPCustomsPlayerEffects.util.Class;

public class footStepSaveClass {

	private int x,y,z = 0;
	private long createTime, Delay = 0;
	

	public  footStepSaveClass(int x, int y, int z, long l, long Delay)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.createTime = l;
		this.Delay = Delay;
	}
	
	public void setfootSteBlock(int x, int y, int z, long l, long Delay)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.createTime = l;
		this.Delay = Delay;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getZ()
	{
		return this.z;
	}
	
	public long getCreateTime()
	{
		return this.createTime;
	}
	
	public long getDelay()
	{
		return this.Delay;
	}
	
	public void setX(int i)
	{
		this.x = i;
	}
	
	public void setY(int i)
	{
		this.y = i;
	}
	public void setZ(int i)
	{
		this.z = i;
	}
	public void setCreateTime(long i)
	{
		this.createTime = i;
	}
	public void setDelay(long i)
	{
		this.Delay = i;
	}
}

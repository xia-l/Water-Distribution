package buildings;


import water.Water;
public class Storage {

	private Water water;
	private float amount;
	public 	String id;
	
	public Storage(String id, Water water, float amount)
	{
		this.water = water;
		this.amount = amount;
		this.id = id;
	}
	
	public Storage()
	{}
	
	public Water getWater()
	{
		return this.water;
	}
	
	public void setWater(Water water)
	{
		this.water = water;
	}
	
	public float getAmount()
	{
		return this.amount;
	}
	
	public void setAmount(float amount)
	{
		this.amount = amount;
	}
	
	public void addWaterToStorage(float amount)
	{
		this.amount += amount;
	}
	
	public boolean consumeFromStorage(float amount)
	{
		if(this.amount - amount > 0)
		{
			this.amount-= amount;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toJSON()
	{
	return "";
	}
	
}

package tradingSystem;

import com.google.gson.Gson;

import water.Water;

public class WaterDeal {

	private Water water;
	private String companyName;
	private int daysToDelivery;
	private int amount;
	public String id;
	public WaterDeal(String id, Water water, String companyName, int daysToDelivery, int amount)
	{
		this.water = water;
		this.companyName = companyName;
		this.daysToDelivery = daysToDelivery;
		this.amount = amount;
		this.id = id;
	}
	
	
	public int getAmount()
	{
		return this.amount;
	}
	
	public Water getWater()
	{
		return this.water;
	}
	
	public String getCompanyName()
	{
		return this.companyName;
	}
	
	public int getDaysToDelivery()
	{
		return this.daysToDelivery;
	}
	
	public String toJSON()
	{
	Gson gson = new Gson();
		return gson.toJson(this);
	}
}

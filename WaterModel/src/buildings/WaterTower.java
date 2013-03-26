package buildings;

import java.util.ArrayList;

import water.Water;
import com.google.gson.Gson;

public class WaterTower extends Node {
	private Water water;
	private float capacity;
	private float currentWaterLevel;
	private ArrayList<Pipe> pipesOut;

public WaterTower(String id, Water water, float capacity,float currentWaterLevel,  ArrayList<Pipe> pipesOut)
{
	this.water = water;
	this.capacity = capacity;
	this.currentWaterLevel = currentWaterLevel;
	this.pipesOut = pipesOut;
	this.id = id;
	this.isSource = true;
}

public WaterTower(String id, Water water, float capacity, float currentWaterLevel)
{
	this.water = water;
	this.capacity = capacity;
	this.currentWaterLevel = currentWaterLevel;
	this.pipesOut = new ArrayList<Pipe>();
	this.id = id;
}

public WaterTower(String id, float capacity, float currentWaterLevel)
{
	this.capacity = capacity;
	this.currentWaterLevel = currentWaterLevel;
	this.pipesOut = new ArrayList<Pipe>();
	this.id = id;
}

public void addPipe(Pipe pipe)
{
	this.pipesOut.add(pipe);
}

public int getAmountOfPipes()
{
	return this.pipesOut.size();
}

public boolean removePipe(Pipe pipe)
{
	return this.pipesOut.remove(pipe);
}

public ArrayList<Pipe> getPipesOut()
{
	return this.pipesOut;
}

public Water getWater()
{
	return this.water;
}

public void setWater(Water water)
{
	this.water = water;
}

public float getCapacity()
{
	return this.capacity;
}

public void setCapacity(float capacity)
{
	this.capacity = capacity;
}

public float getCurrentWaterLevel()
{
	return this.currentWaterLevel;
}


public boolean consumeWater(float consumption)
{
	if(this.currentWaterLevel - consumption > 0)
	{
		this.currentWaterLevel =- consumption;
		return true;
	}
	else
	{
		return false; 
	}
}

public boolean addWaterToTower(float amountOfNewWater)
{
	if(amountOfNewWater + currentWaterLevel > this.capacity)
	{
		return false;
	}
	else
	{
		this.currentWaterLevel+= amountOfNewWater;
		return true;
	}
}

public String toJSON()
{
Gson gson = new Gson();
	return gson.toJson(this);
}
}

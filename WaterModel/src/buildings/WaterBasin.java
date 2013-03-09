package buildings;

import java.util.ArrayList;

import water.Water;

public class WaterBasin extends Node {
	private Water water;
	private float capacity;
	private float currentWaterLevel;
	private ArrayList<Pipe> pipesOut;

	private float maxFlow;

public WaterBasin(Water water, float capacity,float currentWaterLevel,  ArrayList<Pipe> pipesOut)
{
	this.water = water;
	this.capacity = capacity;
	this.currentWaterLevel = currentWaterLevel;
	this.pipesOut = pipesOut;
}

public WaterBasin(Water water, float capacity, float currentWaterLevel)
{
	this.water = water;
	this.capacity = capacity;
	this.currentWaterLevel = currentWaterLevel;
	this.pipesOut = new ArrayList<Pipe>();
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

public float getMaxFlow()
{
	return this.maxFlow;
}

public void setMaxFlow(float maxFlow)
{
	this.maxFlow = maxFlow;
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

public String rain(float amountOfRain)
{
	if(amountOfRain + currentWaterLevel > this.capacity)
	{
		currentWaterLevel = amountOfRain; //We assume the remaining rain water simply spills onto the ground and is wasted. 
		return "Basin is overflowing";
	}
	else
	{
		this.currentWaterLevel+= amountOfRain;
		return "Rain water has been added to basin";
	}
}

public String toJSON()
{
return "";
}
}

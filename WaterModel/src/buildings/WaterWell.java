package buildings;
import java.util.ArrayList;
import water.Water;

public class WaterWell extends Node {
private Water water;
private float amountOfWater;
private ArrayList<Pipe> pipesOut;
private float maxFlow;
public WaterWell(Water water, float amountOfWater, float maxFlow, ArrayList<Pipe> pipesOut)
{
	this.water = water;
	this.amountOfWater = amountOfWater;
	this.pipesOut = pipesOut;
	this.maxFlow = maxFlow;
}

public WaterWell(Water water, float amountOfWater, float maxFlow)
{
	this.water = water;
	this.amountOfWater = amountOfWater;
	this.pipesOut = new ArrayList<Pipe>();
	this.maxFlow = maxFlow;
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

public float getAmountOfWaterLeft()
{
	return this.amountOfWater;
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
	if(this.amountOfWater - consumption > 0)
	{
		this.amountOfWater =- consumption;
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

package buildings;
import java.util.ArrayList;

import water.Water;

public class PumpHouse extends Node {
	private ArrayList<Pipe> pipesOut;
	private ArrayList<Pipe> pipesIn;
	private Storage drinkableStorage = new Storage();
	private Storage nonDrinkableStorage = new Storage();;
	
	
	public PumpHouse(ArrayList<Pipe> pipesIn, ArrayList<Pipe> pipesOut)
	{
		this.pipesIn = pipesIn;
		this.pipesOut = pipesOut;
	}
	
	public void addWaterToStorage(boolean drinkable, float amount)
	{
		if(drinkable)
		{
			this.drinkableStorage.addWaterToStorage(amount);
		}
		else
		{
			this.nonDrinkableStorage.addWaterToStorage(amount);
		}
	}
	
	public boolean consumeWaterFromStorage(boolean drinkable, float amount)
	{
		if(drinkable)
		{
			return this.drinkableStorage.consumeFromStorage(amount);
		}
		else
		{
			return this.nonDrinkableStorage.consumeFromStorage(amount);
		}
	}
	public void addPipeOut(Pipe pipe)
	{
		this.pipesOut.add(pipe);
	}

	public int getAmountOfPipesOut()
	{
		return this.pipesOut.size();
	}

	public boolean removePipeOut(Pipe pipe)
	{
		return this.pipesOut.remove(pipe);
	}
	
	public void addPipeIn(Pipe pipe)
	{
		this.pipesIn.add(pipe);
	}

	public int getAmountOfPipesIn()
	{
		return this.pipesIn.size();
	}

	public boolean removePipeIn(Pipe pipe)
	{
		return this.pipesIn.remove(pipe);
	}
	
	public ArrayList<Pipe> getPipesOut()
	{
		return this.pipesOut;
	}
	
	public ArrayList<Pipe> getPipesIn()
	{
		return this.pipesIn;
	}
	public String toJSON()
	{
	return "";
	}
}

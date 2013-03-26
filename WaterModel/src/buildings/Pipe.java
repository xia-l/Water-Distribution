package buildings;

import com.google.gson.Gson;

import water.Water;

public class Pipe {
private Water water;
private float flow;
private float capacity;
private String from;
private String to;

public Pipe(Water water, float flow, float capacity, Node from, Node to)
{
	this.water = water;
	this.flow = flow;
	this.capacity = capacity;
	this.to = to.id;
	this.from = from.id;
	
}
public Water getWater()
{
	return this.water;
}

public void setWater(Water water)
{
	this.water = water;
}

public float getFlow()
{
	return this.flow;
}

public void setFlow(float flow)
{
	this.flow = flow;
}

public float getCapacity()
{
	return this.capacity;
}

public void setCapacity(float capacity)
{
	this.capacity = capacity;
}

public String getFrom()
{
	return this.from;
}

public void setFrom(Node from)
{
	this.from = from.id;
}

public String getTo()
{
	return this.to;
}

public void setTo(Node to)
{
	this.to = to.id;
}
public String toJSON()
{
Gson gson = new Gson();
	return gson.toJson(this);
}
}

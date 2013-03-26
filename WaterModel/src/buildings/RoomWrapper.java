package buildings;

import java.util.ArrayList;

import com.google.gson.Gson;

import dataObjects.*;
import water.Water;
import water.WaterTap;

public class RoomWrapper {
private String roomID;
private ArrayList<WaterTap> waterTaps; 
private int buildingID;

public RoomWrapper(Room room, int buildingID)
{
	this.roomID = room.id;
	this.buildingID = buildingID;
	this.waterTaps = new ArrayList<WaterTap>();
	for(int i=0; i< room.waterSensors.size(); i++)
	{
		WaterSensor sensor = room.waterSensors.get(i);
		WaterTap tap = new WaterTap(this.roomID, sensor.id, sensor, buildingID);
		this.waterTaps.add(tap);
	}
}

public boolean removeWaterTap(WaterTap tap)
{
	return this.waterTaps.remove(tap);
}

public void addWaterToTap(Water water, String tapID)
{
	WaterTap wt = this.getWaterTap(tapID);
	wt.setWater(water);
	
}
public String getRoomID()
{
	return this.roomID;
}

public void setRoomID(String ID)
{
	this.roomID = ID;
}

public int getBuildingID()
{
	return this.buildingID;
}

public void setBuildingID(int buildingID)
{
	this.buildingID = buildingID;
}

public ArrayList<WaterTap> getWaterTaps()
{
	return this.waterTaps;
}

public int amountOfWaterTaps()
{
	return this.waterTaps.size();
}

public WaterTap getWaterTap(String ID)
{
	for(int i=0; i<this.waterTaps.size(); i++)
	{
		WaterTap wt = waterTaps.get(i);
		if(wt.getID().equals(ID))
		{
			return wt;
		}
	}
	return null;
}

public String toJSON()
{
Gson gson = new Gson();
	return gson.toJson(this);
}
}

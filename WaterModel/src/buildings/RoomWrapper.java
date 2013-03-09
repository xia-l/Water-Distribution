package buildings;

import java.util.ArrayList;

import dataObjects.*;
import water.Water;
import water.WaterTap;

public class RoomWrapper {
private String roomID;
private ArrayList<WaterTap> waterTaps; 

public RoomWrapper(Room room)
{
	this.roomID = room.id;
	this.waterTaps = new ArrayList<WaterTap>();
	for(int i=0; i< room.waterSensors.size(); i++)
	{
		WaterSensor sensor = room.waterSensors.get(i);
		WaterTap tap = new WaterTap(this.roomID, sensor.id, sensor);
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
return "";
}
}

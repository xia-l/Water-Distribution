package model;
import java.io.IOException;
import java.util.ArrayList;

import implementation.BuildingInterfaceImpl;
import interfaces.BuildingInterfacePOJO;
import dataObjects.*;

import org.jdom2.*;

import buildings.BuildingWrapper;


public class WaterModel {
	ArrayList<BuildingWrapper> buildingWrappers;
	public WaterModel()
	{
		
	}
	
	public void loadBuildings() throws JDOMException, IOException
	{
		BuildingInterfacePOJO buildingInterface = new BuildingInterfaceImpl(); 
		 buildingWrappers = new ArrayList<BuildingWrapper>();
		ArrayList<Building> buildings = (ArrayList<Building>) buildingInterface.GetBuildings();
		
		for(int i =0; i < buildings.size(); i++)
		{
			Building b = buildings.get(i);
			ArrayList<Room> rooms = (ArrayList<Room>) buildingInterface.GetRooms(b.bid);
			BuildingWrapper wrapper = new BuildingWrapper(b, rooms);
			buildingWrappers.add(wrapper);
		}
	}
	
	public void createWaterGraph()
	{
		//Create all the water sources.
		// Pumphouses.
		//Combine it all through piping.
	}
}

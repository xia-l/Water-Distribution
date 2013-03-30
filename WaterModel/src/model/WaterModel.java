package model;
import java.io.IOException;
import java.util.ArrayList;

import implementation.BuildingInterfaceImpl;
import interfaces.BuildingInterfacePOJO;
import dataObjects.*;

import org.jdom2.*;

import tradingSystem.WaterDeal;
import tradingSystem.WaterTradingSystem;
import water.WaterTap;

import com.google.gson.Gson;

import buildings.BuildingWrapper;
import buildings.Node;
import buildings.RoomWrapper;


public class WaterModel {
	WaterGraph waterGraph;
	WaterTradingSystem tradingSystem; 
	public WaterModel()
	{
		this.waterGraph = new WaterGraph();
	}
	
	public void loadBuildings() throws JDOMException, IOException
	{
		BuildingInterfacePOJO buildingInterface = new BuildingInterfaceImpl(); 
		ArrayList<Building> buildings = (ArrayList<Building>) buildingInterface.GetBuildings();
		
		for(int i =0; i < buildings.size(); i++)
		{
			Building b = buildings.get(i);
			ArrayList<Room> rooms = (ArrayList<Room>) buildingInterface.GetRooms(b.bid);
			BuildingWrapper wrapper = new BuildingWrapper(b, rooms);
			this.waterGraph.addNode(wrapper);
		}
	}
	
	public void loadTradingSystem()
	{
		this.tradingSystem = new WaterTradingSystem(30000, 50, 100);
		this.tradingSystem.start();
	}
	
	public void stopTradingSystem()
	{
		this.tradingSystem.stop();
	}
	
	
	public void createWaterGraph()
	{
		//Create all the water sources.
		// Pumphouses.
		//Combine it all through piping.
	}
	
	private BuildingWrapper getBuildingWrapper(String id)
	{
		BuildingWrapper wrapper = null;
		for(Node n : this.waterGraph.getNodes())
		{
			if(n.id.equals(id))
			{
				wrapper = (BuildingWrapper)n;
			}
		}
		return wrapper;
	}
	public String getBuilding(String id)
	{
		String result = "";
		for(Node n : this.waterGraph.getNodes())
		{
			if(n.id.equals(id))
			{
				result = n.toJSON(); 
			}
		}
		return result;
	}
	
	public String getAllBuildings()
	{
		Gson gson = new Gson(); 
		return gson.toJson(waterGraph.getNodes());
	}
	
	public String getRoom(String bid, String rid)
	{
		BuildingWrapper wrapper = this.getBuildingWrapper(bid);
		if(wrapper != null)
		{
			RoomWrapper rwrapper = wrapper.findRoom(rid);
			return rwrapper.toJSON();
		}
		else
		{
			return "No building found";
		}
	}
	
	private RoomWrapper getRoomWrapper(String bid, String rid)
	{
		BuildingWrapper wrapper = this.getBuildingWrapper(bid);
		if(wrapper != null)
		{
			RoomWrapper rwrapper = wrapper.findRoom(rid);
			return rwrapper;
		}
		else
		{
			return null;
		}
	}
	
	public String getRooms(String bid)
	{
		BuildingWrapper wrapper = this.getBuildingWrapper(bid);
		Gson gson = new Gson(); 
		return gson.toJson(wrapper.getRooms());
	}
	
	public String getGraph()
	{
		Gson gson = new Gson(); 
		return gson.toJson(this.waterGraph);
	}
	
	public String getNewDeals() throws InterruptedException
	{
		ArrayList<WaterDeal> deals = this.tradingSystem.getNewDeals();
		Gson gson = new Gson();
		return gson.toJson(deals);
	}
	
	public String getWaterTap(String bid, String rid, String wid)
	{
		RoomWrapper rwrapper = this.getRoomWrapper(bid, rid);
		if(rwrapper != null)
		{
			WaterTap tap = rwrapper.getWaterTap(wid);
			return tap.toJSON();
		}
		else
		{
			return "Problem with IDs";
		}
	}
	
	public String getWaterTaps(String bid, String rid)
	{
		RoomWrapper rwrapper = this.getRoomWrapper(bid, rid);
		if(rwrapper != null)
		{
			Gson gson = new Gson();
			return gson.toJson(rwrapper.getWaterTaps());
		}
		else
		{
			return "Problem with IDs";
		}
	}
	
	public String readSensor(String bid, String rid, String wid, String sensorID)
	{
		return "";
	}
	
	public String adjustWaterTap(String bid, String rid, String wid, String sensorID, int value)
	{
		return "";
	}
	
	public String acceptDeal(int quality, boolean drinkable, int amount, String waterID, float price, String addToNodeID)
	{
		return "";
	}
	
	public String calculateMaxFlow()
	{
		return "";
	}
	
}

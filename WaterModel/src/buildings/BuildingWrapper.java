package buildings;

import dataObjects.Building;
import dataObjects.Room;

import java.util.ArrayList;
import com.google.gson.Gson;
public class BuildingWrapper extends Node{

	private Building building;
	private ArrayList<RoomWrapper> rooms;
	private ArrayList<Pipe> pipesIn;
	public BuildingWrapper(Building building, ArrayList<Room> rooms)
	{
		this.building = building;
		this.id = building.id + "";
		this.rooms = new ArrayList<RoomWrapper>();
		this.pipesIn = new ArrayList<Pipe>();
		for(int i=0; i<rooms.size(); i++)
		{
			RoomWrapper wrapper = new RoomWrapper(rooms.get(i), building.bid);
			this.rooms.add(wrapper);
		}
	}
	
	public Building getBuilding()
	{
		return this.building;
	}
	
	public ArrayList<RoomWrapper> getRooms()
	{
		return this.rooms;
	}
	
	public RoomWrapper findRoom(String ID)
	{
		for(int i=0; i<this.rooms.size(); i++)
		{
			RoomWrapper wrapper = this.rooms.get(i);
			if(wrapper.getRoomID().equals(ID))
			{
				return wrapper;
			}
		}
		return null;
	}
	
	public void addPipeIn(Pipe pipe)
	{
		this.pipesIn.add(pipe);
	}

	public int getAmountOfPipesIn()
	{
		return this.pipesIn.size();
	}

	public boolean removePipe(Pipe pipe)
	{
		return this.pipesIn.remove(pipe);
	}
	
	public ArrayList<Pipe> getPipesIn()
	{
		return this.pipesIn;
	}
	
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
		
	}


}

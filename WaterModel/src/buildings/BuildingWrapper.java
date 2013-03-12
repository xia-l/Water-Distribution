package buildings;

import dataObjects.Building;
import dataObjects.Room;

import java.util.ArrayList;
public class BuildingWrapper extends Node{

	private Building building;
	private ArrayList<RoomWrapper> rooms;
	
	public BuildingWrapper(Building building, ArrayList<Room> rooms)
	{
		this.building = building;
		this.rooms = new ArrayList<RoomWrapper>();
		for(int i=0; i<rooms.size(); i++)
		{
			RoomWrapper wrapper = new RoomWrapper(rooms.get(i));
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
	public String toJSON()
	{
		return "";
		
	}
}

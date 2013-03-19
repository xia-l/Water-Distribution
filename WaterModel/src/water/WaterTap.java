package water;
import java.util.ArrayList;

import dataObjects.*;
public class WaterTap {

	private String roomNumber;
	private String ID;
	private WaterSensor sensor;
	private Water water;
	private ArrayList<Measurement> measurements;
	
	public WaterTap(String roomNumber, String ID, WaterSensor sensor, Water water)
	{
		this.roomNumber = roomNumber;
		this.ID = ID;
		this.sensor = sensor;
		this.water = water;
		measurements =  new ArrayList<Measurement>();
	}
	
	public WaterTap(String roomNumber, String ID, WaterSensor sensor)
	{
		this.roomNumber = roomNumber;
		this.ID = ID;
		this.sensor = sensor;
		measurements = new ArrayList<Measurement>();
	}
	
	public void AddMeasurement()
	{
		//Do something
	}
	
	public ArrayList GetMeasurements()
	{
		return this.measurements;
	}
	
	public String getRoomNumber()
	{
		return this.roomNumber;
	}
	
	public void setRoomNumber(String roomNumber)
	{
		this.roomNumber = roomNumber;
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	public void setID(String ID)
	{
		this.ID = ID;		
	}
	
	public WaterSensor getWaterSensor()
	{
		return this.sensor;
	}
	
	public void setWaterSensor(WaterSensor sensor)
	{
		this.sensor = sensor;
	}
	
	public Water getWater()
	{
		return this.water;
	}
	
	public void setWater(Water water)
	{
		this.water = water;
	}
	
	public SensorValue readSensor(String sensorType)
	{
		//Do something fancy
		return null;
	}
	
	public void adjustWaterTap(String command)
	{
		//execute command.
	}
	
	public String toJSON()
	{
	return "";
	}
	
}

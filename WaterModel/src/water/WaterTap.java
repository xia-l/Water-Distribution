package water;
import implementation.BuildingInterfaceImpl;
import interfaces.BuildingInterfacePOJO;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import com.google.gson.Gson;

import dataObjects.*;
public class WaterTap {

	private String roomNumber;
	private int buildingID;
	private String ID;
	private WaterSensor sensor;
	private Water water;
	private ArrayList<Measurement> measurements;
	
	public WaterTap(String roomNumber, String ID, WaterSensor sensor, Water water, int buildingID)
	{
		this.roomNumber = roomNumber;
		this.ID = ID;
		this.sensor = sensor;
		this.water = water;
		measurements =  new ArrayList<Measurement>();
		this.buildingID = buildingID;
	}
	
	public WaterTap(String roomNumber, String ID, WaterSensor sensor ,int buildingID)
	{
		this.roomNumber = roomNumber;
		this.ID = ID;
		this.sensor = sensor;
		measurements = new ArrayList<Measurement>();
		this.buildingID = buildingID;
	}
		
	public ArrayList<Measurement> getMeasurements()
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
	
	public int getBuildingID()
	{
		return this.buildingID;
	}
	
	public void setBuildingID(int ID)
	{
		 this.buildingID = ID;	
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
	
	//Sensor is Example: gain
	public ArrayList<SensorValue> readSensor(String subSensor) throws Exception
	{
		BuildingInterfacePOJO buildingInterface = new BuildingInterfaceImpl(); 
		if(subSensor.equals("state") || subSensor.equals("production") || subSensor.equals("gain"))
		{
			ArrayList<SensorValue> readings = (ArrayList<SensorValue>) buildingInterface.GetSensorValue(this.sensor.id+"-"+subSensor);
			return readings;
		}
		else
		{
			throw new Exception("SubSensor not supported");
		}
	}
	
	public boolean adjustWaterTap(String subSensor, int value) throws Exception
	{

		BuildingInterfacePOJO buildingInterface = new BuildingInterfaceImpl(); 
		if(subSensor.equals("efficiency") || subSensor.equals("flow") || subSensor.equals("gain"))
		{
			String specificSensor = this.sensor.id+"-"+subSensor;
			return buildingInterface.SetSensorValue(this.buildingID, specificSensor, value);
		}
		else
		{
			throw new Exception("SubSensor not supported");
		}
	}
	
	public String toJSON()
	{
	Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}

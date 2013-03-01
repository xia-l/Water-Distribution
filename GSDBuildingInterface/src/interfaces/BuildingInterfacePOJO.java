package interfaces;
import dataObjects.*;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;

public interface BuildingInterfacePOJO {

	public List<Building> GetBuildings() throws JDOMException, IOException;
	public Building GetBuilding(int bid) throws JDOMException, IOException;
	public List<Room> GetRooms(int bid) throws JDOMException, IOException;
	public Room GetRoom(int bid, String rid) throws JDOMException, IOException;
	
	
	public List<SensorValue> GetSensorValue(String sensor) throws JDOMException, IOException;
	public boolean SetSensorValue(int bid, String sensor, int sensorValue);
}

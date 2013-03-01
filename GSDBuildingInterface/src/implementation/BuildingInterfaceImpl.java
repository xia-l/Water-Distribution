package implementation;
import interfaces.BuildingInterfaceJSON;
import interfaces.BuildingInterfacePOJO;
import interfaces.BuildingInterfaceXML;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


import dataObjects.*;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class BuildingInterfaceImpl implements BuildingInterfaceXML, BuildingInterfaceJSON, BuildingInterfacePOJO{

	private String url = "http://188.182.62.208:9090";
	
	public BuildingInterfaceImpl()
	{
	
	}
	
	public BuildingInterfaceImpl(String url)
	{
		this.url = url;
	}
	
public String GetAllBuildingsXML()
{

	try {
		String result = readURL(url+"/api/user/buildinginfo/?format=xml");
	
		return result;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
	
}

public String GetAllBuildingsJSON()
{

	try {
		String result = readURL(url+"/api/user/buildinginfo/?format=json");
	
		return result;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
	
}

@Override
public String GetBuildingDetailsJSON(int bid) {
	try {
		String result = readURL(url+"/api/user/building/entry/description/"+bid+"/?format=json");
	
		return result;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}

@Override
public String GetServiceValueJSON(String serviceID) {
	try {
		String result = readURL(url+"/api/user/measurement/?uuid=" + serviceID + "&order_by=-timestamp&format=json");
	
		return result;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}

@Override
public boolean SetServiceValueJSON(int bid, String serviceValue, int serviceID) {
	try {
		String result = readURL(url+"building/entry/set/" + bid + "/"+ serviceID + "/" + serviceValue +"/?format=json");
		return true;
	} catch (IOException e) {
		e.printStackTrace();
		return false;
	}
	
}

@Override
public String GetBuildingDetailsXML(int bid) {
	try {
		String result = readURL(url+"/api/user/building/entry/description/"+bid+"/?format=xml");
	
		return result;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}

@Override
public String GetServiceValueXML(String serviceID) {
	try {
		String result = readURL(url+"/api/user/measurement/?uuid=" + serviceID + "&order_by=-timestamp&format=xml");
	
		return result;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}

@Override
public boolean SetServiceValueXML(int bid, int serviceValue, String serviceID) {
	try {
		String result = readURL(url+"building/entry/set/" + bid + "/"+ serviceID + "/" + serviceValue +"/?format=xml");
		return true;
	} catch (IOException e) {
		e.printStackTrace();
		return false;
	}
	
}

private String readURL(String url) throws IOException
{
	URL server = new URL(url);
	BufferedReader in = new BufferedReader(
	new InputStreamReader(
			server.openStream()));

	String inputLine;
	String outputLine = "";
	while ((inputLine = in.readLine()) != null)
	{
		outputLine+= inputLine;
	}

	in.close();
	System.out.println(outputLine);
	return outputLine;
}

@Override
public List<Building> GetBuildings() throws JDOMException, IOException {
	
	SAXBuilder builder  = new SAXBuilder();
    String xml = GetAllBuildingsXML();
    

   StringReader in = new StringReader(xml);
    Document document = (Document)builder.build(in);
    System.out.println(document.toString());
 
    Element rootNode = document.getRootElement();

	List list = rootNode.getChildren("objects");
	ArrayList<Building> buildingList = new ArrayList<Building>();
	for (int i = 0; i < list.size(); i++) {
		 
		   Element node = (Element) list.get(i);
		   List objectList = node.getChildren("object");
		   for (int h = 0; h < objectList.size(); h++) {
		
			   Element objectNode = (Element) objectList.get(h);  
			   Building building = new Building();
		   building.description = objectNode.getChildText("description");
		   building.bid = Integer.parseInt(objectNode.getChildText("bid"));
		   building.bri = Integer.parseInt(objectNode.getChildText("bri"));
		   building.active =  Boolean.parseBoolean(objectNode.getChildText("active"));
		   building.id = Integer.parseInt(objectNode.getChildText("id"));
		   building.resource_uri =  objectNode.getChildText("resource_uri");
		   buildingList.add(building);
		   }
 
		}
	return buildingList;
}

@Override
public Building GetBuilding(int bid) throws JDOMException, IOException {
	
	String xml = readURL(url+"buildinginfo/"+bid+"/?format=xml");
	
	SAXBuilder builder  = new SAXBuilder();

   StringReader in = new StringReader(xml);
    Document document = (Document)builder.build(in);
    System.out.println(document.toString());
 
    Element rootNode = document.getRootElement();

	List list = rootNode.getChildren("object");
	Building building = new Building();
	for (int i = 0; i < list.size(); i++) {
		 
		   Element node = (Element) list.get(i);

		   building.description = node.getChildText("description");
		   building.bid = Integer.parseInt(node.getChildText("bid"));
		   building.bri = Integer.parseInt(node.getChildText("bri"));
		   building.active =  Boolean.parseBoolean(node.getChildText("active"));
		   building.id = Integer.parseInt(node.getChildText("id"));
		   building.resource_uri =  node.getChildText("resource_uri");

		}
	return building;
}

@Override
public List<Room> GetRooms(int bid) throws JDOMException, IOException {
	String xml = GetBuildingDetailsXML(bid);
	SAXBuilder builder  = new SAXBuilder();

	StringReader in = new StringReader(xml);
	Document document = (Document)builder.build(in);
	 
	Element rootNode = document.getRootElement();
	ArrayList<Room> rooms = new ArrayList<Room>();
	Element valueNode = rootNode.getChild("value");
	
	Element roomsElement = valueNode.getChild("rooms");
	
	List list = roomsElement.getChildren();
	
	
	for (int i = 0; i < list.size(); i++) {
		 
		   Element node = (Element) list.get(i);
		   Room room = new Room();
		   room.id = node.getChildText("ID");
		   
		   Element watersNode = node.getChild("waters");
		   List waterList = watersNode.getChildren("water");
		   
		   for(int h = 0; h < waterList.size(); h++)
		   {
			   Element waterNode = (Element) waterList.get(h);
			   WaterSensor waterSensor = new WaterSensor();
			   waterSensor.id = waterNode.getChildText("ID");
			   waterSensor.flow = Integer.parseInt(waterNode.getChildText("flow"));
			   
		   SensorInput input = new SensorInput();
		   SensorOutput output = new SensorOutput();
		   
		   Element inputNode = waterNode.getChild("input");
		   Element outputNode = waterNode.getChild("output");
		   
		input.efficiency = inputNode.getChildText("efficiency");
		input.flow = inputNode.getChildText("flow");
    	input.gain = inputNode.getChildText("gain");
		
		output.gain = outputNode.getChildText("gain"); 
		output.production = outputNode.getChildText("production"); 
		output.state = outputNode.getChildText("state"); 
		waterSensor.input = input;
		waterSensor.output = output;
		
		   room.waterSensors.add(waterSensor);
		   }
		   rooms.add(room);

		}
	
	return rooms;
}

@Override
public Room GetRoom(int bid, String rid) throws JDOMException, IOException {
	List<Room> rooms = this.GetRooms(bid);
	Room temp = new Room();
	temp.id = rid;
	return FindRoom(rooms, temp);
}


//I love Java and Lists. Really! It's like walking with dinosaurs!
public Room FindRoom(List rooms, Room r)
{
	Room result = null;
	for(int i =0; i < rooms.size(); i++)
	{
		Room room = (Room)rooms.get(i);
		if(room.id.equals(r.id))
		{
			result = room;
		}
	}
	return result;
}
@Override
//Remember to use the Output section of a sensor in the String sensorValue. Example: room-2-light-4-gain. Gain is part of the Output section. 
public List<SensorValue> GetSensorValue(String sensor) throws JDOMException, IOException {
	String result = GetServiceValueXML(sensor);
	
	SAXBuilder builder  = new SAXBuilder();
  
    String xml = GetAllBuildingsXML();
    

   StringReader in = new StringReader(result);
    Document document = (Document)builder.build(in);
 
    Element rootNode = document.getRootElement();

	List list = rootNode.getChildren("objects");
	ArrayList<SensorValue> sensorValues = new ArrayList<SensorValue>();
	for (int i = 0; i < list.size(); i++) {
		 
		   Element node = (Element) list.get(i);
		   List objectList = node.getChildren("object");
		   for (int h = 0; h < objectList.size(); h++) {
			   SensorValue sensorValue = new SensorValue();
			   Element objectNode = (Element) objectList.get(h);  
			   sensorValue.timestamp = objectNode.getChildText("timestamp");
			   sensorValue.value = Integer.parseInt(objectNode.getChildText("val"));
			   sensorValue.bid =    sensorValue.value = Integer.parseInt(objectNode.getChildText("bid"));
			   sensorValue.uuid =   sensorValue.timestamp = objectNode.getChildText("uuid");
			   sensorValue.resource_uri =   sensorValue.timestamp = objectNode.getChildText("resource_uri");
			   sensorValue.id =   sensorValue.timestamp = objectNode.getChildText("id");
			   sensorValues.add(sensorValue);
		   }
 
		}
	
	return sensorValues;
}

@Override
public boolean SetSensorValue(int bid, String sensor, int sensorValue) {
	try {
		String result = readURL(url+"building/entry/set/" + bid + "/"+ sensor + "/" + sensorValue +"/?format=xml");
		return true;
	} catch (IOException e) {
		e.printStackTrace();
		return false;
	}
	
}


}


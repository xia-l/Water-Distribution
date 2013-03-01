package implementation;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import interfaces.BuildingInterfaceJSON;
import interfaces.BuildingInterfacePOJO;
import interfaces.BuildingInterfaceXML;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import dataObjects.*;
public class TestRunner {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) throws JDOMException, IOException {
		// TODO Auto-generated method stub
	   // BuildingInterfaceJSON test = new BuildingInterfaceImpl();
	   // String o = test.GetAllBuildingsJSON();
	   // System.out.println(o);
	    JDOM();
	}
	
	public static void JDOM() throws JDOMException, IOException
	{
		BuildingInterfacePOJO build = new BuildingInterfaceImpl();
		List<Room> rooms = build.GetRooms(0);
		
		System.out.println(rooms.size());
		
		Room r = build.GetRoom(0, "floor-0-room-1");
		System.out.println(r.id);
	}

}

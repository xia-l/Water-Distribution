package interfaces;

public interface BuildingInterfaceXML {

	public String GetAllBuildingsXML();
	public String GetBuildingDetailsXML(int bid);
	public String GetServiceValueXML(String serviceID);
	public boolean SetServiceValueXML(int bid, int serviceValue, String serviceID);
	
}

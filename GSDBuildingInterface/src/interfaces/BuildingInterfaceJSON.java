package interfaces;

public interface BuildingInterfaceJSON {
	public String GetAllBuildingsJSON();
	public String GetBuildingDetailsJSON(int bid);
	public String GetServiceValueJSON(String serviceID);
	public boolean SetServiceValueJSON(int bid, String serviceValue, int serviceID);
	
	
}



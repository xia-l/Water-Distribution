package tradingSystem;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import buildings.Node;
import buildings.Pipe;
import buildings.PumpHouse;
import buildings.WaterTower;

import water.Water;

public class GeneratorTester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		testGson();
		//testTrade();
	}

	private static void testGson() throws IOException
	{
	
		Water water = new Water("water1", 100, 542, true);
		WaterTower tower = new WaterTower("Tower1", 5000, 4500);
		PumpHouse pstation = new PumpHouse("PumpHouse1");

		Pipe p1 = new Pipe(water, 50, 100, tower, pstation);
		tower.addPipe(p1);
		pstation.addPipeIn(p1);
		System.out.println("Objects created");
		Gson gson = new Gson();
		String test = gson.toJson(tower);
		System.out.println(test);
		System.in.read();
	}
	
	private static void testTrade() throws IOException
	{
		WaterTradingSystem trader = new WaterTradingSystem(10000, 4, 9.6f);
		System.out.println("Trader started.. please press e to quit..");
		int input = 0;
		int input2 = System.in.read();
		
		trader.start();
		while(true)
		{
		System.out.println("Im running");
		int amountOfDeals = trader.getAmountOfNewDeals();
		System.out.println(amountOfDeals);
		if(amountOfDeals > 6)
		{
			try {
				ArrayList<WaterDeal> deals = trader.getNewDeals();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		input = System.in.read();
		if(input == 101)
		{
			break;
		}
		}
		trader.stop();
		System.out.println("Bye!!!");
	}
}

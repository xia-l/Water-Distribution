package tradingSystem;

import java.io.IOException;
import java.util.ArrayList;

public class GeneratorTester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
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

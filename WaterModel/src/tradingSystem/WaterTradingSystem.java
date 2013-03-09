package tradingSystem;
import java.util.ArrayList;
import java.util.Random;

import water.Water;

public class WaterTradingSystem {

	private ArrayList<WaterDeal> deals = new ArrayList<WaterDeal>();
	private DealGenerator generator;
	
	private enum CompanyNames
	{
		CrystalClearWater(1, true), RefresherExtreme(2, true), SewageNDumpWater(3, false), RadioactiveCoolantRUs(4, false), SpringWaterDelux(5, true), HappyWater(6, false), MillfordsWaterSupply(7, false), AquaVitae(8, true); 
		
		private int code;
		 private boolean drinkable;
		 private CompanyNames(int c, boolean drinkable) 
		 {
		   code = c;
		   this.drinkable = drinkable;
		 }
		 public int getCode() {
		   return code;
		 }
		 
		 public boolean getDrinkable()
		 {
			 return drinkable;
		 }
	}
	
	public class DealGenerator implements Runnable
	{
		Thread runner; 
		private int frequency;
		private int threshold;
		private boolean active;
		private int priceLevel;
		private float basePrice;
		ArrayList<WaterDeal> reference;
		
		public DealGenerator(int frequency, int priceLevel, float basePrice, ArrayList<WaterDeal> reference)
		{
			this.frequency = frequency;
			this.reference = reference;
			this.priceLevel = priceLevel;
			this.basePrice = basePrice;
		}
		
		public void startGenerator()
		{
			this.active = true;
			runner = new Thread(this, "DealGenerator");
			this.runner.start();
		}
		
		public void stopGenerator()
		{
			this.active = false;
			this.runner.interrupt();
		}
		
		public void pauseGenerator() throws InterruptedException
		{
			Thread.sleep(5000);
		}
		
		public void run() {
			while(active)
			{
				Random rand = new Random();
				int waitSeed  = rand.nextInt(frequency);
				if(waitSeed < 30000)
				{
					waitSeed = 30000;
				}
					Random company = new Random();
					int code = company.nextInt(CompanyNames.values().length);
					CompanyNames delivery = CompanyNames.values()[code];
					
					Random randomPrice = new Random();
					int priceSeed = randomPrice.nextInt(this.priceLevel);
					if(priceSeed > 0)
					{
					this.basePrice *= priceSeed;
					}
					else
					{
						this.basePrice *= 1;
					}
					
					Random qualityRandom = new Random();
					float qualityFloat = qualityRandom.nextFloat();
					int quality = 0;
					if(qualityFloat == 0.0f)
					{
						quality = 10;
					}
					else
					{
						quality = (int) (100*qualityFloat);
					}
					

					Random deliveryRandom = new Random();
					
					int daysToDelivery = deliveryRandom.nextInt(32);
					if(daysToDelivery == 0)
					{
						daysToDelivery = 7;
					}
					
					Random amountRandom = new Random();
					int amount = amountRandom.nextInt(5000);
					if(amount < 500)
					{
						amount = 500;
					}
					
					Water water = new Water(quality, basePrice, delivery.drinkable);
					WaterDeal waterDeal = new WaterDeal(water, delivery.name(), amount,daysToDelivery);
					this.reference.add(waterDeal);
					System.out.println("New deal generated");
					
					try {
						System.out.println("Thread Sleeping");
						Thread.sleep(waitSeed);
					} catch (InterruptedException e) {
						this.active = false;
					}
				
			}
		}
		
	}
	public WaterTradingSystem(int frequency, int priceLevel, float basePrice)
	{
		this.generator = new DealGenerator(frequency, priceLevel, basePrice, deals);
	}
	
	public ArrayList<WaterDeal> getNewDeals() throws InterruptedException
	{
		this.generator.pauseGenerator();
		ArrayList<WaterDeal> newDeals = new ArrayList<WaterDeal>();
		for(int i =0 ; i < this.deals.size(); i++)
		{
			newDeals.add(this.deals.get(i));
			this.deals.remove(i);
		}
		return newDeals;
	}
	
	public int getAmountOfNewDeals()
	{
		return this.deals.size();
	}

	
	public void start()
	{
		this.generator.startGenerator();
	}
	
	public void stop()
	{
		System.out.println("Stopping..");
		this.generator.stopGenerator();
	}
}

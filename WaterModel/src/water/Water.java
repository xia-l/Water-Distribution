package water;

public class Water {
private int quality;
private float price;
private boolean drinkable;

public Water(int quality, float price, boolean drinkable)
{
	this.quality = quality;
	this.price = price;
	this.drinkable = drinkable;
}
public void setQuality(int quality)
{
	this.quality = quality;
}

public int getQuality()
{
	return this.quality;
}

public void setPrice(float price)
{
	this.price = price;
}

public float getPrice()
{
	return this.price;
}

public void setDrinkable(boolean drinkable)
{
	this.drinkable = drinkable;
}

public boolean isDrinkable()
{
	return this.drinkable;
}

public String toJSON()
{
	return "To be implemented";
}
}

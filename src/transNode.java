

public class transNode {
	
	private String BoS; //BorS is abv. for Buy or Sell
	private int shareCount; //amount of shares bought or sold
	private double sharePrice; //the price of the share. Always stored as a positive value
	private transNode prevTN;//the link for the previuos transaction node
	private transNode nextTN;//the link for the next transaction node
	
	
	
	public transNode()
	{
		//this will also never be called
	}
	
	public transNode(String BoSX, int shareCountX, double sharePriceX)
	{			
			BoS = BoSX;
			
			if(shareCountX > 0)
			{
				shareCount = shareCountX;
			}
			
			if(sharePriceX > 0)
			{
				sharePrice = sharePriceX;
			}
			
	}
	
	
	public void setBoS(String BoSX)
	{
		BoS = BoSX;
	}
	public String getBoS()
	{
		return BoS;
	}
	public void setShareCount(int shareCountX)
	{
		//share count must be greater then or equal to one, you cant buy no stock.
		if(shareCountX > 0)
		{
		shareCount = shareCountX;
		}
	}
	public int getShareCount()
	{
		return shareCount;
	}
	public void setSharePrice(int sharePriceX)
	{
		//share price cannot be negative
		if(sharePriceX >= 0)
		{
			sharePrice = sharePriceX;
		}
	}
	public double getSharePrice()
	{
		return sharePrice;
	}
	public void setPrevTN(transNode prevTNX)
	{
		prevTN  = prevTNX;
	}
	public transNode getPrevTN()
	{
		return prevTN;
	}
	public void setNextTN(transNode nextTNX)
	{
		nextTN = nextTNX;
	}
	public transNode getNextTN()
	{
		return nextTN;
	}

	
}

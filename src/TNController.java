

public class TNController extends transNode{
	
	private CompTagNode compTag;
	private transNode headTN;
	private transNode rearTN;
	private transNode TNScannerLink;
	private int tSS;//abv. total stocks sold
	private int tSSP;//abv. total stocks sold price
	
	public TNController()
	{
		//this will never be called
	}
	
	public TNController(CompTagNode compTagX)
	{
		headTN = null;
		rearTN = null;
		TNScannerLink = null;
		tSS = 0;
		tSSP = 0;
	}
	
	public void setTNScnnerLink(transNode TNScannerLinkX)
	{
		TNScannerLink =  TNScannerLinkX;
	}
	public transNode getTNScannerLink()
	{
		return TNScannerLink;
	}
	public transNode getHeadTN()
	{
		return headTN;
	}
	
	
	public void addTransNode(String buyOrSell, int shareCount, double sharePrice)
	{
	
		if(headTN == null) 
		{
			transNode newTransNode = new transNode(buyOrSell, shareCount, sharePrice);
			headTN = newTransNode;
			headTN.setPrevTN(null);
			headTN.setNextTN(null);
		}
		
		else if(headTN != null && rearTN == null)
		{	
			transNode newTransNode = new transNode(buyOrSell, shareCount, sharePrice);
			headTN.setNextTN(newTransNode);
			rearTN = newTransNode;
			rearTN.setPrevTN(headTN);
			rearTN.setNextTN(null);
		}
		
		else
		{
			
			transNode newTransNode = new transNode(buyOrSell, shareCount, sharePrice);
			rearTN.setNextTN(newTransNode);
			newTransNode.setPrevTN(rearTN);
			newTransNode.setNextTN(null);
			rearTN = newTransNode;
		}
	}
}

	
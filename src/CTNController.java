
public class CTNController extends CompTagNode
{
	
	//CTN is abv. for compTagNod
	private CompTagNode headerLink;//used to hold a referance to the front of the CTN node list
	private CompTagNode rearLink;//used to hold a referance to the rear of the CTN node list
	private CompTagNode CTNscannerLink;//used to move through the list and retrive information in a nondisructive format
	//I was orginally planing to use this class to hold links for both the CTN and TN classes but you cannot inharrent multipul classes in java

	
	public CTNController() 
	{
		headerLink = null;
		rearLink = null;
		CTNscannerLink = null;
	}
	
	public void setheaderLink(CompTagNode headerLinkX)
	{
		headerLink = headerLinkX;
	}
	public CompTagNode getHeaderLink()
	{
		return headerLink;
	}
	public void setrearLink(CompTagNode rearLinkX)
	{
		rearLink = rearLinkX;
	}
	public CompTagNode getRearLink()
	{
		return rearLink;
	}
	public void setCTNScannerLink(CompTagNode CTNscannerLinkX)
	{
		CTNscannerLink = CTNscannerLinkX;
	}
	public CompTagNode getCTNScannerLink()
	{
		return CTNscannerLink;
	}
	public void addCompNode(String CompName, String CompTag)
	{
		if(headerLink == null) 
		{
			CompTagNode newCompNode = new CompTagNode(CompName, CompTag);
			headerLink = newCompNode;
			headerLink.setPrevComp(null);
			headerLink.setNextComp(null);
		}
		
		else if(headerLink != null && rearLink == null)
		{
			CompTagNode newCompNode = new CompTagNode(CompName, CompTag);
			headerLink.setNextComp(newCompNode);
			rearLink = newCompNode;
			rearLink.setPrevComp(headerLink);
			rearLink.setNextComp(null);
		}
		
		else
		{
			CompTagNode newCompNode = new CompTagNode(CompName, CompTag);
			rearLink.setNextComp(newCompNode);
			newCompNode.setPrevComp(rearLink);
			newCompNode.setNextComp(null);
			rearLink = newCompNode;
		}
	}

}

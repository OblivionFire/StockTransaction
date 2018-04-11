
public class CompTagNode {
	
	/*
	 * comp is used to abrv. company
	 * prev is used to abrv. previous
	 * trans is used to abrv. transaction
	 * tag is the stack tag of a comp
	 * Name is the full comp name
	 */
	
	  private CompTagNode nextComp;//used to link to the next company node
	  private CompTagNode prevComp;//used to link to the last company node
	  private transNode firstTrans;//used to link to the first company transaction
	  private String compTag;//Stores the stock tag for the company
	  private String compName;//stores the company name
	  private TNController TNC; 
	  


	  public CompTagNode() 
	  {
		//this will never be called  
	  }
	  
	  public CompTagNode(String compNameX, String compTagX)
	  {
		  if(!compNameX.equals(null))
		  {
			  compName = compNameX;
		  }
		  
		  if(!compTagX.equals(null))
		  {
			  compTag = compTagX;
		  }
		  
		  TNC = null;
	  }
	  
	  public void setNextComp(CompTagNode nextCompX)
	  {
		  nextComp = nextCompX;
	  }
	  public CompTagNode getNextComp() 
	  {
		return nextComp;  
	  }
	  public void setPrevComp(CompTagNode prevCompX)
	  {
		  prevComp = prevCompX;
	  }
	  public CompTagNode getprevComp() 
	  {
		  return prevComp;
	  }
	  public void setCompTag(String compTagX)
	  {
		  compTag = compTagX;
	  }
	  public String getCompTag()
	  {
		  return compTag;
	  }
	  public void setCompName(String compNameX)
	  {
		  compName = compNameX;
	  }
	  public String getCompName()
	  {
		  return compName;
	  }
	  public void setTNC(TNController TNCX)
	  {
		TNC = TNCX;
	  }
	  public TNController getTNC()
	  {
		 return TNC;
	  }

}


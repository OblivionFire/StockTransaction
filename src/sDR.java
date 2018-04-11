

public class sDR {
	
	//I use objects like this to return multipul data types from one method, in this case it is a
	//b -boolean- 
	//d - double-
	
	String rString;
	double rDouble;
	String compName;
	
	public sDR()
	{
		
	}
	public sDR(String stringX)
	{
		rString = stringX;
	}
	public sDR(double stringX)
	{
		rDouble = stringX;
	}
	public sDR(String stringX, double doubleX)
	{
		rString = stringX;
		rDouble = doubleX;
	}
	
	
	public void setrString(String stringX)
	{
		rString = stringX;
	}
	
	public String getrString()
	{
		return rString;
	}
	
	public void setrDouble(double doubleX)
	{
		rDouble = doubleX;
	}
	
	public double getrDouble()
	{
		return rDouble;
	}
	
	public String getCompName() 
	{
		return compName;
	}
	public void setCompname(String compNameX)
	{
		compName = compNameX;
	}
}

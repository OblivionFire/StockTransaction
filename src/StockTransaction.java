

import java.util.*;
import java.io.*;

public class StockTransaction 
{

	CTNController companies;
	TNController transNodes;
	
	
	public StockTransaction() throws IOException
	{
		companies = new CTNController();
		transNodes = new TNController();
		String[] compTemp = new String[2];
		String compTempString;
		File compInputFile = new File("src/stocks.txt");
		Scanner compInput = new Scanner(compInputFile);
		while (compInput.hasNext())
		{
			compTempString = compInput.nextLine();
			compTemp = compTempString.split(";");
			//I should probably use a for each loop but I don't think it is needed in this situation
			compTemp[0] = compTemp[0].trim();
			compTemp[1] = compTemp[1].trim();
			companies.addCompNode(compTemp[1], compTemp[0]);
		}

		String[] transTemp = new String[4];
		String transTempString;
		File transInputFile = new File("src/transactions.txt");
		Scanner transInput = new Scanner(transInputFile);
		
		int shareCount;//an int representing the shareCount of the transaction is parsed into this - from a string -  before being passed into a transaction node
		double sharePrice;//a double representing the sharePrice of the transaction is parsed into this - from a string -  before being passed into a transaction node
		boolean next = false;//used to exit out of while loop once the transaction has been assigned to the porper company
		while(transInput.hasNextLine())
		{
			transTempString = transInput.nextLine();
			transTemp = transTempString.split(";");
			//again I don't think a for each loop is needed. If it was anylonger it probably would
			transTemp[0] = transTemp[0].trim();
			transTemp[1] = transTemp[1].trim();
			transTemp[2] = transTemp[2].trim();
			transTemp[3] = transTemp[3].trim();
			transTemp[3] = transTemp[3].substring(1);
			shareCount = Integer.parseInt(transTemp[2]);
			sharePrice = Double.parseDouble(transTemp[3]);
			
			System.out.println(transTemp[0]);
			System.out.println(transTemp[1]);
			System.out.println(transTemp[2]);
			System.out.println(transTemp[3]);
			
			companies.setCTNScannerLink(companies.getHeaderLink());
			next = false;
			while(companies.getCTNScannerLink() != null && next == false)
			{
				if(companies.getCTNScannerLink().getCompTag().equals(transTemp[0]))
				{
					if(companies.getCTNScannerLink().getTNC() == null)
					{
						companies.getCTNScannerLink().setTNC(new TNController());
						companies.getCTNScannerLink().getTNC().addTransNode(transTemp[1], shareCount, sharePrice);
					}
					
					else
					{
						companies.getCTNScannerLink().getTNC().addTransNode(transTemp[1], shareCount, sharePrice);
					}
					next = true;
				}
				
				companies.setCTNScannerLink(companies.getCTNScannerLink().getNextComp());
			}
		}
		
		
	}
	
	
	public sDR calculateRealizedGains(String stockTag)
	{
		int soldShareCount = 0;//holds the amount of share you have sold of one stock
		double realizedGains = 0.0;//holds the amount you have lost/gained on the stocks you have sold
		sDR Return = new sDR();//return type, string/double
		CompTagNode compToCalc = searchForStockTag(stockTag);//searches for the correct stock tag. returns it if it exists, null if not
		boolean exit = false; //Used to exit out of the while loop calculating Realized gains if you have finished calculating, but have bought more then you have sold
		if(compToCalc == null)
		{
			Return.setrString("BST");// (bad stock tag) returns false and 0.0 if stock tag was no imputed correctly or does not exist
			Return.setrDouble(0.0);
		}
		
		else
		{
			 Return.setCompname(compToCalc.getCompName());
			 TNController CTCTNC = compToCalc.getTNC();//referance to the transaction node controler
			 CTCTNC.setTNScnnerLink(CTCTNC.getHeadTN());//referance to the transaction node scanner
			 
			 if(CTCTNC.getTNScannerLink().getBoS().equals("sell"))
			 {
				 Return.setrDouble(0.0);
				 Return.setrString("SOE");//stock overflow exception
				 return Return;
			 }
			
			 
			 else
			 {
				while(CTCTNC.getTNScannerLink() != null)
				{
					if(CTCTNC.getTNScannerLink().getBoS().equals("sell"))
					{
						soldShareCount += CTCTNC.getTNScannerLink().getShareCount();
						realizedGains += (CTCTNC.getTNScannerLink().getSharePrice() * CTCTNC.getTNScannerLink().getShareCount());
						//calculating how many shares you have sold and how much you have made off them
					}
					
					CTCTNC.setTNScnnerLink(CTCTNC.getTNScannerLink().getNextTN());
				}
				
				CTCTNC.setTNScnnerLink(CTCTNC.getHeadTN());//resetting to the front of the list
				
				while(CTCTNC.getTNScannerLink() != null && exit == false)
				{
					if(soldShareCount < 0)
					{
						Return.setrString("SOE");
						exit = true;
					}
					else
					{
						//a check to make sure the shares a possitive. If they are negative you are trying to sell stocks you dont own
						if(CTCTNC.getTNScannerLink().getBoS().equals(("buy")))
						{
							if(soldShareCount == 0)
							{
								Return.setrDouble(realizedGains);
								Return.setrString("true");
								exit = true;
							}
							else if (CTCTNC.getTNScannerLink().getShareCount()>soldShareCount)
							{
								realizedGains -= (CTCTNC.getTNScannerLink().getSharePrice() * soldShareCount); //This will take care of the cass where you have 
								//bought eight stock in one transaction but ony have to calc realized gains on 6 more
								Return.setrDouble(realizedGains);
								Return.setrString("true");
								exit = true;
							}
							
							else
							{
								realizedGains -= (CTCTNC.getTNScannerLink().getSharePrice() * CTCTNC.getTNScannerLink().getShareCount());
								soldShareCount -= CTCTNC.getTNScannerLink().getShareCount();
							}
							
						}
						CTCTNC.setTNScnnerLink(CTCTNC.getTNScannerLink().getNextTN());
					}
				}
			}
		}
		return Return;
	}
	
	public CompTagNode searchForStockTag(String stockTag)
	{
		boolean exit = false;
		companies.setCTNScannerLink(companies.getHeaderLink());
		CompTagNode tempCompTagNode = null;
		
		while(companies.getCTNScannerLink() != null && exit == false)
		{
			if(companies.getCTNScannerLink().getCompTag().equals(stockTag))
			{
				tempCompTagNode = companies.getCTNScannerLink();
				exit = true;
			}
			
			companies.setCTNScannerLink(companies.getCTNScannerLink().getNextComp());
		}
		
		return tempCompTagNode;
	}
}


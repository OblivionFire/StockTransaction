

import java.io.IOException;
import java.util.*;
public class Test {

	public static void main(String[] args) {
		
		
		boolean exit = false;
		Scanner input = new Scanner(System.in);
		String choice;
		String stockChoice;
		StockTransaction test;
		try 
		{
			test = new StockTransaction();
			
			System.out.println("All stock and transaction from the provided stocks.txt and transactions.txt have been read in and stored accordingly");
			
			
			do 
			{
				
				System.out.println("What would you like to do next (Please press corasponding number key only once.");
				System.out.println("1) Request realized gains for a specific stock");
				System.out.println("2) Exit");
				System.out.println("");
				choice = input.nextLine();
						
				
				switch(choice)
				{
				
					case "1":
					{
						System.out.println("What Stock would you like your realized gains calculated for?"); 
						System.out.println("please input only the stock tag as seen on the New York Stock exchange and then press enter");
						System.out.println("");
						stockChoice = input.nextLine();
						sDR testOutPut = test.calculateRealizedGains(stockChoice);
						if(testOutPut.getrString().equals("SOE"))
						{
							System.out.println(String.format("Sorry, the number of stocks sold is greater then the number of stocks bought for %s.",testOutPut.getCompName())); 
							System.out.println("Please check your input data and restart program, thank you.");
							System.out.println("");
						}
						
						else if(testOutPut.getrString().equals("BST"))
						{
							System.out.println("That stock tag is not in the system on the current time,");
							System.out.println("please make sure it is a valid tag on the New Your Stock Exchange,");
							System.out.println("and that it is in the correct format. Then try again, thank you.");
							System.out.println("");
						}
						
						else if (testOutPut.getrString().equals("true"))
						{
							if(testOutPut.getrDouble() > 0.0)
							{
								System.out.println(String.format("You have a current realized gain of " + testOutPut.getrDouble() + " USD for %s.", testOutPut.getCompName()));	
								System.out.println("");
							}
							
							else if(testOutPut.getrDouble() == 0.0)
							{
								System.out.println(String.format("You have no current realized gains for %s", testOutPut.getCompName()));
								System.out.println("");
								System.out.println("However you are currenty break perfectly even");
								System.out.println("");
							}
							
							else
							{
								System.out.println(String.format("You have a no current realized gains for %s.", testOutPut.getCompName()));
								System.out.println(String.format("But for the record you are down " + testOutPut.getrDouble() + " USD for %s.", testOutPut.getCompName()));
								System.out.println("");
							}
						}
						break;
					}
						
					case "2":
					{
						System.out.println("Closing Program, all files have remainded un-edited,");
						System.out.println("Have a nice day");
						exit = true;
						break;
					}
					
					default :
					{
						System.out.println("That is not a valid input, please try again");
						System.out.println("");
						break;
					}
				
				}
				
			
			}while(exit == false);
			
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		

	}

}

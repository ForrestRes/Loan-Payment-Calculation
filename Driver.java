import java.util.Arrays;
import java.util.Scanner;

public class Driver {
	
////////////////declare constants
	final static int numOfInterestRates = 4; 	//number of interest rates shown in the chart
	final static int numOfYears = 10;	

///////////////display function displays one year of payments, rates, and totals
	public static void displayYear(int years, float[] interestRates, float[] monthlyPayments, float[] totalPayments)
	{
		System.out.print(years);
		for(int i = 0; i<numOfInterestRates; i++)
			System.out.printf("\t\t\t%.2f%%\t\t\t\t%.2f\t\t\t\t%.2f\n", interestRates[i]*100, monthlyPayments[i], totalPayments[i]);
	}
	
	public static void main(String[] args) {
////////////////declare variables
		Scanner scan = new Scanner(System.in);
		float loan = 0;				//holds initial loan amount	
		float AIR = 0;				//holds initial annual interest rate
		float [] monthlyPayments;	//holds the values for the current monthly payments corresponding to interestRates array
		float [] totalPayments;		//holds total payment amount corresponding to interestRates
		float [] interestRates;		//holds the range of interest rates for easy access

///////////////get input for loan and AIR
		while(loan<=0)		//get loan amount
		{
			System.out.print("Loan amount: ");
			loan = scan.nextFloat();
			scan.nextLine();
			if(loan<=0)
				System.out.println("This is an unnacceptable loan amount.");
		}
		while(AIR<=0)		//get AIR amount as a % and stored at decimal
		{
			System.out.print("Annual interest Rate (%): ");
			AIR = scan.nextFloat()/100;
			scan.nextLine();
			if(AIR<=0)
				System.out.println("This is an unnacceptable rate.");
		}

///////////////initialize variables
		monthlyPayments = new float[numOfInterestRates];
		
		totalPayments = new float[numOfInterestRates];
		for(int i = 0; i<numOfInterestRates; i++)
			totalPayments[i] = loan;
		
		interestRates = new float[numOfInterestRates];
		for(int i = 0; i<numOfInterestRates; i++)
			interestRates[i] = (float)(AIR + (i*0.005));
		
///////////print initial column names for output
		System.out.println("Years\t\t\tInteres Rate\t\t\tMonthly Payment\t\t\tTotal Payment");

///////////make calculation and call displayYear
		for(int i = 1; i<=numOfYears; i++)
		{
			//adjust total payments
			for(int j = 0; j<numOfInterestRates; j++)
				totalPayments[j] = (float)((interestRates[j]/12*loan*i*12)/(1-(Math.pow(1+interestRates[j]/12,-i*12))));
			//adjust monthly payments
			for(int j = 0; j<numOfInterestRates; j++)
				monthlyPayments[j] = (float)((loan*(interestRates[j]/12))/(1-(1/(Math.pow(1+(interestRates[j]/12), i*12)))));
			//call display year
			displayYear(i, interestRates, monthlyPayments, totalPayments);
		}
	}

}

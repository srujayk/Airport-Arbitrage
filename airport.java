/**
 * @(#)airport.java
 *
 *
 * @author
 * @version 1.00 2016/7/31
 */
import java.util.*;
import java.io.*;

public class airport
{

	public static double[][] rates;

    public static void main(String[] args) throws Exception
    {
        File file = new File("input07.txt");
        Scanner firstInput = new Scanner(file);
        int max = 0;
		firstInput.nextInt();
		while(firstInput.hasNextInt())
		{
			int first = firstInput.nextInt();
			int second = firstInput.nextInt();
			firstInput.nextDouble();
			if(first > max)
			{
				max = first;
			}
			if(second > max)
			{
				max = second;
			}
		}
		max++;
		rates = new double[max][max];
		startRates(file);
		if(!calculate())
		{
			System.out.println(0);
		}
		else
		{
			System.out.println(1);
		}
		/*for(double[] row: rates)
		{
			int a = 0;
			for(double number: row)
			{
				if(number == 0)
				{
					a++;
				}
				//System.out.printf("%.4f",number);
				System.out.print(number + " ");
			}
			if(a == rates.length-1)
			{
				System.out.println("NOOO!");
			}
			System.out.println();
		}*/
    }

    public static void startRates(File file) throws Exception
    {
    	Scanner input = new Scanner(file);
    	input.nextInt();
    	while(input.hasNextInt())
    	{
    		rates[input.nextInt()][input.nextInt()] = input.nextDouble();
    	}
    }

    public static boolean calculate()
    {
		boolean toReturn = true;
		for(int i = 0; i < rates.length; i++)
		{
			if(rates[i][i] == 0)
			{
				rates[i][i] = 1;
			}
			else if(rates[i][i] != 1)
			{
				toReturn = false;
			}
		}
		while(areZeroes())
		{
			for(int r = 0; r < rates.length; r++)
			{
				for(int c = 0; c < rates.length; c++)
				{
					if(rates[r][c] == 0)
					{
						helpCalculate(r,c);
					}
				}
			}
		}
		toReturn = check();
		return toReturn;
    }

    public static boolean areZeroes()
    {
    	for(double[] row: rates)
		{

			for(double number: row)
			{
				if(number == 0)
				{
					return true;
				}
			}
		}
		return false;
    }

    public static void helpCalculate(int r, int c)
    {
		int tempR = c;
		int tempC = r;

		for(int i = 0; i < rates.length; i++)
		{
			if(i == tempR)
			{
				continue;
			}
			if(rates[tempR][i] != 0 && rates[i][tempC] != 0)
			{
				rates[r][c] = 1/(rates[tempR][i] * rates[i][tempC]);
			}
		}
    }

    public static boolean check()
    {
    	for(int r = 0; r < rates.length; r++)
    	{
    		for(int c = 0; c < rates.length; c++)
    		{
    			String a = String.format("%.3f",rates[r][c]);
    			String b = String.format("%.3f",1/rates[c][r]);
    			if(!a.equals(b))
    			{
    				//System.out.println(r + " " + c);
    				return false;
    			}
    		}
    	}
    	return true;
    }
}

package part01;

import java.util.HashMap;
import java.util.ArrayList;

public class AnnualPlan {
	 
	HashMap<String , String > exhibitMonthHalls = new HashMap<String , String>(); 
	String[] allowedHalls = {"HALL_A", "HALL_B","HALL_C"};
	
	String planId;
	int year;
	
	
	
	public AnnualPlan(String id,int year) throws Exception
	{
		if(!setYear(year))
		{
			throw new Exception ("Year must be later than 2025");
		}
		this.planId =  id;
	}
	
	
	private boolean setYear(int year)
	{
		if (year <= 2025)
		{
			return false;
		}
		else
		{
			this.year = year;
			return true;
		}
	}
	
	
	
	public String getPlanId()
	{
		return this.planId;
	}
	
	public int getYear()
	{
		return this.year;
	}
	

	
	public HashMap<String , String > getexhibitMonthHalls()
	{
		return this.exhibitMonthHalls;
	}
	
	public int getTotalExhibits()
	{
		return this.exhibitMonthHalls.size();
	}
	
	
	public boolean setExhibitHalls(String newValue, Boolean remove)
	{
		String hall = newValue.split("-")[0];
		int month = Integer.parseInt(newValue.split("-")[1]);
		String hallAndMonth = hall + "-" + Integer.toString(month);
		String exhibitId = newValue.split("-")[2];
		
		if(remove)
		{
			exhibitMonthHalls.remove(hallAndMonth);
			return true;
		}
		
		boolean validHall = false;
		for(String h : allowedHalls)
		{
			if(hall.equals(h))
			{
				validHall = true;
			}
		}
		
		if(!validHall)
		{
			System.err.println("Invalid Hall");
			return false;
		}
		
		if (!(month >= 1) && !(month <=12))
		{
			System.err.println("Invalid month");
			return false;
		}
		
		exhibitMonthHalls.put(hallAndMonth, exhibitId);
		return true;
	}
	
	public boolean addExhibitHalls(String exhibitId , String hall , int month) throws Exception
	{
		String hallAndMonth = hall + "-" + Integer.toString(month);
		boolean validHall = false;
		for(String h : allowedHalls)
		{
			if(hall.equals(h))
			{
				validHall = true;
			}
		}
		
		if(!validHall)
		{
			System.err.println("Invalid Hall");
			return false;
		}
		
		if (!(month >= 1) && !(month <=12))
		{
			System.err.println("Invalid month");
			return false;
		}
		
		for(String check : exhibitMonthHalls.keySet())
		{
			if (hallAndMonth.equals(check))
			{
				System.err.println("This Spot is already occupied");
				return false;
			}
		}
		
		exhibitMonthHalls.put(hallAndMonth, exhibitId);
		return true;
	
	}

	public String getExhibit(String hall , int month)
	{
		String hallAndMonth = hall + "-" + Integer.toString(month);
		
		for(String check : exhibitMonthHalls.keySet())
		{
			if (check.equals(hallAndMonth))
			{
				return exhibitMonthHalls.get(hallAndMonth) + " is in " + hallAndMonth;
			}
		}
		
		return "Exhibit not found in " + this.planId;
	}
	
	public String getAnnualPlanInfo(String infoName) throws Exception
	{
		switch (infoName)
		{
		case "year":
			return "Year: " +  this.year;

		case "total_exhibits":
			return "Total Exhibits: " + exhibitMonthHalls.size();
			
		default:
			throw new Exception("Invalid infoName");
			
			
		}
		
	}
	
	public void updateAnnualPlanInfo(String infoName, String newValue , Boolean Remove) throws Exception
	{
		switch (infoName)
		{
		case "year":
			if(!setYear(Integer.parseInt(newValue)))
			{
				throw new Exception("Year must be later than 2025");
			}
			break;
		
		case "hall":
			setExhibitHalls(newValue, Remove);
			break;
			

			
		default:
			throw new Exception("Invalid infoName");
			
			
		}
		
	}
	
	public void removeExhibit(String exhibitId)
	{
		String remove = "";
		for(String find : exhibitMonthHalls.keySet())
		{
			if(exhibitMonthHalls.get(find).equals(exhibitId))
			{
				remove = find;
			}
		}
		exhibitMonthHalls.remove(remove);
	}
	
	

	
	
}

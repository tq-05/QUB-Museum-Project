package part01;


import java.util.ArrayList;

import java.util.HashMap;

import java.util.Scanner;

import java.io.File;

import java.io.FileWriter;   

import java.io.FileReader;   

import java.io.IOException;  

import api.QUBMuseumAPI;



public class QUBMuseum implements QUBMuseumAPI {
	
// all the menu states
	public enum MenuState {
		MAIN_MENU,
// ---------------------------------------------
		MANAGE_ARTIFACTS,
		
		CREATE_ARTIFACT_NAME,
		CREATE_ARTIFACT_TYPE,
		CREATE_ARTIFACT_DESCRIPTION,
		CREATE_ARTIFACT_ENGAGEMENT_TIME,

// CREATING ARTIFACTS
		VIEW_ARTIFACTS,
		GET_ARTIFACT_INFO,
		HANDLE_ARTIFACT_ID,
		HANDLE_ARTIFACT_INFO_NAME,
		HANDLE_ARTIFACT_NEW_VALUE,
		GET_ARTIFACT_ENGAGEMENT_TIME,
		
	
		HANDLE_ARTIFACT_SEARCH_CRITERIA,
		HANDLE_ARTIFACT_SORTBY,
		
		HANDLE_ARTIFACT_SEARCH_NAME,
		HANDLE_ARTIFACT_SEARCH_NAME_CONTAINS,
		HANDLE_ARTIFACT_SEARCH_TYPE,
		HANDLE_ARTIFACT_SEARCH_ID,
		HANDLE_EXHIBIT_SORTBY,

// VIEWING ARTIFACTS
		
		UPDATE_ARTIFACT_NAME,
		UPDATE_ARTIFACT_TYPE,
		UPDATE_ARTIFACT_DESCRIPTION,
		UPDATE_ARTIFACT_ENGAGEMENT_TIME,
		DELETE_ARTIFACT,

// DELETING OR UPDATING ARTIFACTS
		
// ----------------- ARTIFACT STATES ---------------------------------------
		MANAGE_EXHIBITS,
		
		CREATE_EXHIBIT_NAME,
		CREATE_EXHIBIT_DESCRIPTION,
	
// CREATING EXHIBITS
		
		VIEW_EXHIBITS,
		VIEW_EXHIBIT_ARTIFACTS,
		GET_EXHIBIT_INFO,
		GET_ARTIFACT_SIGN,
		
		HANDLE_EXHIBIT_SEARCH_CRITERIA,
		HANDLE_EXHIBIT_SEARCH_NAME,
		HANDLE_EXHIBIT_SEARCH_NAME_CONTAINS,
		HANDLE_EXHIBIT_SEARCH_ID,
		
		GET_EXHIBIT_ENGAGEMENT_TIME,
		

		
// VIEWING EXHIBITS
		REORDER_EXHIBIT_ARTIFACTS,
		ADD_EXHIBIT_ARTIFACT,
		REMOVE_EXHIBIT_ARTIFACT,
		HANDLE_EXHIBIT_ID,
		HANDLE_EXHIBIT_INFO_NAME,
		HANDLE_EXHIBIT_NEW_VALUE,
		UPDATE_EXHIBIT_NAME,
		UPDATE_EXHIBIT_DESCRIPTION,
		DELETE_EXHIBIT,
		

// ---------------- EXHIBIT STATES ----------------------------
		MANAGE_ANNUAL_PLANS,
		VIEW_ANNUAL_PLANS,
		
		GET_ANNUAL_PLAN_INFO,

		GET_ANNUAL_PLAN_EXHIBIT,
		GET_ANNUAL_PLAN,
		GET_ANNUAL_PLAN_HALL,
		GET_ANNUAL_PLAN_MONTH,
		HANDLE_ANNUAL_PLAN_ID,
		HANDLE_ANNUAL_PLAN_INFO_NAME,
		HANDLE_ANNUAL_PLAN_NEW_VALUE,
		

		ADD_ANNUAL_PLAN_EXHIBIT_HALL,
		ADD_ANNUAL_PLAN_EXHIBIT_MONTH,
		ADD_ANNUAL_PLAN_EXHIBIT,
		CREATE_ANNUAL_PLAN,
		DELETE_ANNUAL_PLAN,
		
		UPDATE_ANNUAL_PLAN_YEAR,
		UPDATE_ANNUAL_PLAN_HALL,
		UPDATE_ANNUAL_PLAN_MONTH,
		UPDATE_ANNUAL_PLAN_HALL_EXHIBIT,
		
		// ---------------- ADDITIONAL FEATURES ------------
		
		SAVE_DATA,
		LOAD_DATA,
		
		
		EXIT
	}
	
	
	private MenuState currentState = MenuState.MAIN_MENU;
	private MenuState nextState ,stateType;
	private HashMap<String , String> artifactStateData = new HashMap<>();
	private HashMap<String , String> exhibitStateData = new HashMap<>();
	private HashMap<String , String> planStateData = new HashMap<>();
	
	private String currentPrompt;
	//public static Boolean remove = false; //TESTING
	Boolean remove = false;
	
	ArrayList<String> reOrderArtifactIds , newArtifactIdList;
	ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
	ArrayList<Exhibit> exhibits = new ArrayList<Exhibit>();
	ArrayList<AnnualPlan> plans = new ArrayList<AnnualPlan>();
	ArrayList<Integer> usedYears = new ArrayList<Integer>();
	
	int nextArtifactId = 0;
	int nextExhibitId = 0;
	int nextPlanId = 0;

	
	// main program
	public static void main(String[] args) {
		
		// create artifact store
		
		// Create API which manages all business logic
		QUBMuseum api = new QUBMuseum();
		// Create UI instance
		Scanner input = new Scanner(System.in);
		// Main loop
		boolean running = true;
		while (running) {
			// Display current prompt
			System.out.println(api.getCurrentPrompt());
			// Get user input
			String userInput = input.nextLine().trim();
			// Process input through state machine
			String result = api.processInput(userInput);
			// Check for exit condition
			if ("EXIT".equals(result)) { running = false;} 
			else if (!result.isEmpty()) {
				System.out.println(result);
			}
		}
	}
	
	// --- Misc ---
	// converts a number to a month
	public String convertToMonth(int input)
	{
		switch (input) 
		{
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
		}  
		return null;
	}
	
	// checks if "#" is present in a string
	public Boolean checkSplittingChar(String input)
	{
		if(input.contains("#"))
		{
			return false;
		}
		return true;
	}
	
	
	
    // --- Artifact Management ---

	// tries to create an artifact and stores it within artifacts
	@Override
	public String createArtifact(String type, String name, String description, int engagementMinutes){
		try {
			Artifact arti = new Artifact("artifact_"+ nextArtifactId,type,name,description,engagementMinutes);
			nextArtifactId++;
			artifacts.add(arti);
			return "Artifact with artifactId: " + arti.getArtifactId() + " has been successfully created";
		}
		catch (Exception ex)
		{
			// System.out.println("Problem creating artifact. (createArtifact)\n");
			// System.out.println(ex);
			return null;
		}
		
	}
	// gets an artifact property based on what infoName the user has input
	@Override
	public String getArtifactInfo(String artifactId, String infoName) 
	{
		try
		{
			for (Artifact art : artifacts)
			{
				if(art.getArtifactId().equals(artifactId))
				{
					return art.getArtifactInfo(infoName);
				}
			}
			// System.out.println("Artifact with Id: " + artifactId + " has not been found");
			return null;
		}
		catch(Exception ex)
		{
			// System.out.print(ex);
			return null;
		}
	}
	// gets the engagement time of an artifact
	@Override
	public int getArtifactEngagementTime(String artifactId)  {
		try
		{
			for (Artifact art : artifacts)
			{
				if(art.getArtifactId().equals(artifactId))
				{
					return art.getArtifactEngagementTime();
				}
			}
			// System.out.println("Artifact with Id: " + artifactId + " has not been found");
			return -1;
		}
		catch(Exception ex)
		{
			// System.out.print("Bad input, Please enter a String (getArtifactEngagementTime)");
			return -1;
		}
		
	}
	// updates a property of an artifact based off the infoName
	@Override
	public void updateArtifactInfo(String artifactId, String infoName, String newValue) 
	{
		try
		{
			boolean found = false;
			for (Artifact art : artifacts)
			{
				if(art.getArtifactId().equals(artifactId))
				{
					art.updateArtifactInfo(infoName, newValue);
					found = true;
				}
			}
			if (!found)
			{
				// System.out.println(artifactId + " was not been found");
			}
			else
			{
				// System.out.println(artifactId + " has been successfully updated");
			}
		}
		catch (Exception ex)
		{
			// System.out.println(ex);
		}
		
	}
	/*
	 *  Deletes the artifact from artifacts 
	 *  And  deletes the chosen artifact from any exhibits it is in
	 */
	
	@Override
	public void deleteArtifact(String artifactId) 
	{
		try
		{
			boolean found = false;
			
			for (int index = artifacts.size() - 1; index >= 0; index--)
			{
				if(artifacts.get(index).getArtifactId().equals(artifactId))
				{
					artifacts.remove(index);
					found = true;
				}
			}
			
			if (!found)
			{
				// System.out.println("Artifact with Id: " + artifactId + " has not been found");
			}
			else
			{
				for(Exhibit exhibit : exhibits)
				{
					exhibit.removeArtifact(artifactId);
				}
				// System.out.println("Artifact with Id: " + artifactId + " has been successfully deleted");
			}
		}
		catch (Exception ex)
		{
			System.err.println(ex);
		}
		
	}
	/*
	 * artifacts is first filtered using the searchCriteria
	 * 
	 * searchCriteria is split using the "=" to filter according to the type
	 * 
	 * then it is sorted by the input of sortBy
	 */
	@Override
	public ArrayList<String> findArtifacts(String searchCriteria, String sortBy) 
	{

		ArrayList<Artifact> queryList = new ArrayList<Artifact>();
		ArrayList<String> returnList = new ArrayList<String>();
		
		
		// Makes a list based off the first part of this method's function in the API
		if (searchCriteria.split("=").length == 2) // splits the input using the =
		{
			String searchType = searchCriteria.split("=")[0].trim();
			String searchQuery = searchCriteria.split("=")[1].trim();
			
			// Below is to get a list of the search TYPE and putting all artifacts into a list which meets a valid search query
			switch (searchType)
			{
			case "name":
				
				for (Artifact art : artifacts)
				{
					if(searchQuery.equals(art.getName()))
					{
						queryList.add(art);
					}
				}
				break;
			
			case "name_contains":
				for (Artifact art : artifacts)
				{
					for(String text : art.getName().split(" "))
					{
						if (searchQuery.equals(text))
						{
							queryList.add(art);
						}
					}
				}
				
				break;
			
			case "type":

				boolean valid = false;
				
				for(Artifact art : artifacts)
				{
					if (art.getArtifactType().equals(searchQuery))
					{
						queryList.add(art);
					}
				}
				break;
				
			case "id":		
				for(Artifact art : artifacts)
				{
					if (art.getArtifactId().equals(searchQuery))
					{
						returnList.add(art.getArtifactId());
						return returnList;
					}
				}
				break;
				
			
			}
		}
		else
		{
			for(Artifact art : artifacts)
			{
				returnList.add(art.getArtifactId());
			}
			return returnList;
		}
		// ------------------------------------------------------
		
		
		//now sort this using the sortBy
		if (queryList.size() >= 2)
		{
			boolean sorted = false;
			switch (sortBy)
			{
			case "name":
				

				while (!sorted)
				{
					boolean changed = false;
					// bubble sort
					
					for(int nextIndex = 1; nextIndex < queryList.size(); nextIndex++)
					{
						int previousIndex = nextIndex-1;
						
						if(queryList.get(previousIndex).getName().compareTo(queryList.get(nextIndex).getName()) > 0)
						{
							changed = true;
							Artifact temp = queryList.get(nextIndex);
							queryList.set(nextIndex, queryList.get(previousIndex));
							queryList.set(previousIndex, temp);
							
						}
					}
					
					// checks that it has not made any changes so it can exit the loop
					if (!changed)
					{
						sorted = true;
					}

				}
				break;
			
			case "type":
				
				while (!sorted)
				{
					boolean changed = false;
					// bubble sort
					
					for(int nextIndex = 1; nextIndex < queryList.size(); nextIndex++)
					{
						int previousIndex = nextIndex-1;
						
						if(queryList.get(previousIndex).getArtifactType().compareTo(queryList.get(nextIndex).getArtifactType()) > 0)
						{
							changed = true;
							Artifact temp = queryList.get(nextIndex);
							queryList.set(nextIndex, queryList.get(previousIndex));
							queryList.set(previousIndex, temp);
							
						}
					}
					
					// checks that it has not made any changes so it can exit the loop
					if (!changed)
					{
						sorted = true;
					}

				}
				break;

				
				
			case "id":
				
				while (!sorted)
				{
					boolean changed = false;
					// bubble sort
					
					for(int nextIndex = 1; nextIndex < queryList.size(); nextIndex++)
					{
						int previousIndex = nextIndex-1;
						
						if(Integer.parseInt(queryList.get(previousIndex).getArtifactId().split("_")[1]) > Integer.parseInt(queryList.get(nextIndex).getArtifactId().split("_")[1]))
						{
							changed = true;
							Artifact temp = queryList.get(nextIndex);
							queryList.set(nextIndex, queryList.get(previousIndex));
							queryList.set(previousIndex, temp);
						}
					}
					
					// checks that it has not made any changes so it can exit the loop
					if (!changed)
					{
						sorted = true;
					}

				}

				
				break;
				
			case "engagement":
				
				while (!sorted)
				{
					boolean changed = false;
					// bubble sort
					
					for(int nextIndex = 1; nextIndex < queryList.size(); nextIndex++)
					{
						int previousIndex = nextIndex-1;
						
						if(queryList.get(previousIndex).getArtifactEngagementTime() > queryList.get(nextIndex).getArtifactEngagementTime())
						{
							changed = true;
							Artifact temp = queryList.get(nextIndex);
							queryList.set(nextIndex, queryList.get(previousIndex));
							queryList.set(previousIndex, temp);
						}
					}
					
					// checks that it has not made any changes so it can exit the loop
					if (!changed)
					{
						sorted = true;
					}

				}
				
				break;
				
				default:
				
				while (!sorted)
				{
					boolean changed = false;
					// bubble sort
					
					for(int nextIndex = 1; nextIndex < queryList.size(); nextIndex++)
					{
						int previousIndex = nextIndex-1;
						
						if(Integer.parseInt(queryList.get(previousIndex).getArtifactId().split("_")[1]) > Integer.parseInt(queryList.get(nextIndex).getArtifactId().split("_")[1]))
						{
							changed = true;
							Artifact temp = queryList.get(nextIndex);
							queryList.set(nextIndex, queryList.get(previousIndex));
							queryList.set(previousIndex, temp);
						}
					}
					
					// checks that it has not made any changes so it can exit the loop
					if (!changed)
					{
						sorted = true;
					}

				}
				break;
			}
		}
		else if (queryList.size() == 0)
		{
			return new ArrayList<String>();
		}
		
		for (int index = 0 ; index < queryList.size(); index++)
		{
			returnList.add(queryList.get(index).getArtifactId());
		}
		
		
		return returnList;
	} 

	
	
	
	
	
	
	
	
	
	
	// --- Exhibit Management ---

		// Tries to create an exhibit and adds it to exhibits
		@Override
		public String createExhibit(String name, String description)
		{
			try {
				Exhibit exhibit = new Exhibit("exhibit_"+nextExhibitId,name,description);
				nextExhibitId++;
				exhibits.add(exhibit);
				return "Exhibit with exhibitId: " + exhibit.getExhibitId() + " has been successfully created";
			}
			catch (Exception ex)
			{
				//System.out.println("Problem creating exhibit. (createExhibit)\n");
				//System.out.println(ex);
				return null;
			}
		
		}
		
		// Gets an exhibit's property based off the infoName
		@Override
		public String getExhibitInfo(String exhibitId, String infoName)
		{
			try
			{
				for (Exhibit exhibit : exhibits)
				{
					if(exhibit.getExhibitId().equals(exhibitId))
					{
						return exhibit.getExhibitInfo(infoName);
					}
				}
				//System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
				return null;
			}
			catch(Exception ex)
			{
				//System.out.print(ex);
				return null;
			}
		}

		// Gets an exhibit's total engagement time which is calculated based off the number of artifacts in the exhibit
		@Override
		public int getExhibitEngagementTime(String exhibitId) 
		{
			try
			{
				boolean exhibitFound = false;
				int totalTime = 0;
				for (Exhibit exhibit : exhibits)
				{
					if (exhibit.getExhibitId().equals(exhibitId))
					{
						exhibitFound = true;
						
						for (Artifact art : artifacts)
						{
							for (String artInEx : exhibit.getExhibitArtifacts())
							{
								if(art.getArtifactId().equals(artInEx))
								{
									totalTime += art.getArtifactEngagementTime();
								}
							}
						}
						return totalTime;
					}
				}
				if(!exhibitFound)
				{
					// System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
					return -1;
				}
				return -1;
				
				
			}
			catch(Exception ex)
			{
				System.out.print(ex);
				return -1;
			}
		}
		
		// Updates an exhibit's property based off the infoName (name or description)
		@Override
		public void updateExhibitInfo(String exhibitId, String infoName, String newValue) 
		{
			try
			{
				boolean found = false;
				for (Exhibit exhibit : exhibits)
				{
					if(exhibit.getExhibitId().equals(exhibitId))
					{
						exhibit.updateExhibitInfo(infoName, newValue);
						found = true;
					}
				}
				if (!found)
				{
					//System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
				}
				else
				{
					//System.out.println("Exhibit with Id: " + exhibitId + " has been successfully updated");
				}
			}
			catch(Exception ex)
			{
				//System.out.println(ex);
			}
		}

		// Deletes the exhibit from exhibits and deletes the exhibit from any plans it is in
		@Override
		public void deleteExhibit(String exhibitId) 
		{
			boolean found = false;
			
			for (int index = exhibits.size()-1 ; index >= 0; index--)
			{
				if(exhibits.get(index).getExhibitId().equals(exhibitId))
				{
					exhibits.remove(index);
					found = true;
				}
			}
			
			
			if (!found)
			{
				//System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
			}
			else
			{
				for(AnnualPlan plan : plans)
				{
					plan.removeExhibit(exhibitId);
				}
				//System.out.println("Exhibit with Id: " + exhibitId + " has been successfully deleted");
			}
			
		}
		/*
		 * exhibits is first filtered using the searchCriteria
		 * 
		 * searchCriteria is split using the "=" to filter according to the type
		 * 
		 * then it is sorted by the input of sortBy
		 */
		@Override
		public ArrayList<String> findExhibits(String searchCriteria, String sortBy) 
		{
			ArrayList<Exhibit> queryList = new ArrayList<Exhibit>();
			ArrayList<String> returnList = new ArrayList<String>();
			
			
			// Makes a list based off the first part of this method's function in the API
			if (searchCriteria.split("=").length == 2) // splits the input using the =
			{
				String searchType = searchCriteria.split("=")[0].trim();
				String searchQuery = searchCriteria.split("=")[1].trim();
				
				// Below is to get a list of the search TYPE and putting all artifacts into a list which meets a valid search query
				switch (searchType)
				{
				case "name":
					
					for (Exhibit exhibit : exhibits)
					{
						if(searchQuery.equals(exhibit.getName()))
						{
							queryList.add(exhibit);
						}
					}
					break;
				
				case "name_contains":
					for (Exhibit exhibit : exhibits)
					{
						for(String text : exhibit.getName().split(" "))
						{
							if (searchQuery.equals(text))
							{
								queryList.add(exhibit);
							}
						}
					}
					
					break;
				
					
				case "id":		
					for(Exhibit exhibit : exhibits)
					{
						if (exhibit.getExhibitId().equals(searchQuery))
						{
							returnList.add(exhibit.getExhibitId());
							return returnList;
						}
					}
					break;
					
				
				}
			}
			else
			{
				for(Exhibit exhibit : exhibits)
				{
					returnList.add(exhibit.getExhibitId());
				}
				return returnList;
			}
			// ------------------------------------------------------
			
			
			if (queryList.size() >= 2)
			{
				boolean sorted = false;
				switch (sortBy)
				{
				case "name":
					

					while (!sorted)
					{
						boolean changed = false;
						// bubble sort
						
						for(int nextIndex = 1; nextIndex < queryList.size(); nextIndex++)
						{
							int previousIndex = nextIndex-1;
							
							if(queryList.get(previousIndex).getName().compareTo(queryList.get(nextIndex).getName()) > 0)
							{
								changed = true;
								Exhibit temp = queryList.get(nextIndex);
								queryList.set(nextIndex, queryList.get(previousIndex));
								queryList.set(previousIndex, temp);
								
							}
						}
						
						// checks that it has not made any changes so it can exit the loop
						if (!changed)
						{
							sorted = true;
						}

					}
					break;
				

					
					
				case "id":
					while (!sorted)
					{
						boolean changed = false;
						// bubble sort
						
						for(int nextIndex = 1; nextIndex < queryList.size(); nextIndex++)
						{
							int previousIndex = nextIndex-1;
							
							if(Integer.parseInt(queryList.get(previousIndex).getExhibitId().split("_")[1]) > Integer.parseInt(queryList.get(nextIndex).getExhibitId().split("_")[1]))
							{
								changed = true;
								Exhibit temp = queryList.get(nextIndex);
								queryList.set(nextIndex, queryList.get(previousIndex));
								queryList.set(previousIndex, temp);
							}
						}
						
						// checks that it has not made any changes so it can exit the loop
						if (!changed)
						{
							sorted = true;
						}

					}

					
					break;
				}
			}
			else if (queryList.size() == 0)
			{
				return new ArrayList<String>();
			}
			
			for (int index = 0 ; index < queryList.size(); index++)
			{
				returnList.add(queryList.get(index).getExhibitId());
			}
			
			
			return returnList;
		}
		/*
		 * An artifact is added to the exhibit with a sign,
		 * which is stored in a hashMap in the exhibit class
		 * 
		 * Checks if exhibit id and artifact id is valid
		 * 
		 */
		@Override
		public void addArtifactToExhibit(String artifactId, String exhibitId, String sign) 
		{
			try
			{
				
				boolean artifactFound = false;
				boolean exhibitFound = false;
				for (Exhibit exhibit : exhibits)
				{
					if(exhibit.getExhibitId().equals(exhibitId))
					{
						exhibitFound = true;
						for (Artifact art : artifacts)
						{
							if(art.getArtifactId().equals(artifactId))
							{
								artifactFound = true;
								// System.out.println(exhibit.addArtifact(artifactId, sign));   
								exhibit.addArtifact(artifactId, sign);
								break;
							}
						}
						if(!artifactFound)
						{
							// System.out.println("Artifact with Id: " + artifactId + " has not been found");
						}
						break;
					}
				}
				if(!exhibitFound)
				{
					// System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
				}

				
			}
			catch(Exception ex)
			{
				//System.out.print(ex);
			}
		}
		/*
		 * An artifact is removed from the exhibit ,
		 * which is stored in a hashMap in the exhibit class
		 * 
		 * Checks if exhibit id and artifact id is valid
		 * 
		 */
		@Override
		public void removeArtifactFromExhibit(String artifactId, String exhibitId) 
		{
			try
			{
				boolean found = false;
				for (Exhibit exhibit : exhibits)
				{
					if(exhibit.getExhibitId().equals(exhibitId))
					{
						found = true;
						if(!exhibit.removeArtifact(artifactId))
						{
							//System.out.println("Artifact with Id: " + artifactId + " is not in Exhibit with Id: " + exhibitId);
						}
						//System.out.println("Artifact with Id: " + artifactId + " has been successfully removed from Exhibit with Id: " + exhibitId);
						break;
					}
				}
				
				if(!found)
				{
					//System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
				}
			
				
			}
			catch(Exception ex)
			{
				//System.out.print(ex);
			}
		}
		/*
		 * Returns the sign of an artifact stored in an exhibit
		 */
		@Override
		public String getExhibitArtifactSign(String exhibitId, String artifactId) 
		{
			try
			{
				for (Exhibit exhibit : exhibits)
				{
					if(exhibit.getExhibitId().equals(exhibitId))
					{
						return exhibit.getExhibitArtifactSign(artifactId);
					}
				}
				//System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
				return null;
			}
			catch(Exception ex)
			{
				//System.out.print(ex);
				return null;
			}
		}
		/*
		 * Returns the list of artifacts stored in an exhibit
		 */
		@Override
		public ArrayList<String> getExhibitArtifacts(String exhibitId) 
		{
			try
			{
				for (Exhibit exhibit : exhibits)
				{
					if(exhibit.getExhibitId().equals(exhibitId))
					{
						return exhibit.getExhibitArtifacts();
					}
				}
				//System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
				return null;
			}
			catch(Exception ex)
			{
				//System.out.print(ex);
				return null;
			}
		}
		/*
		 * re orders the artifact list in exhibits by having a new list being entered
		 */
		@Override
		public void reorderExhibitArtifacts(String exhibitId, ArrayList<String> artifactIds) 
		{
			try
			{
				boolean exhibitFound = false;
				for (Exhibit exhibit : exhibits)
				{
					if(exhibit.getExhibitId().equals(exhibitId))
					{
						exhibit.reorderArtifacts(artifactIds);
						exhibitFound = true;
						
					}
				}
				if(!exhibitFound)
				{
					//System.out.println("Exhibit with Id: " + exhibitId + " has not been found");
				}
				else
				{
					//System.out.println("The artifacts in exhibit " + exhibitId + " have been successfully reordered");
				}
				
			}
			catch(Exception ex)
			{
				//System.out.print(ex);
			}
		}

		
		
		
		
		
		
		
		
		
		
		
	    // --- Annual Plan Management ---

		// creates an annual plan and stores it into plan
		@Override
		public String createAnnualPlan(int year) 
		{
			try {
				AnnualPlan plan = new AnnualPlan("plan_"+nextPlanId,year);
				nextPlanId++;
				plans.add(plan);
				usedYears.add(year);
				//System.err.println(plans.size() + " <------- size of plans");
				return "Annual plan with Id: " + plan.getPlanId() + " has been successfully created";
			}
			catch (Exception ex)
			{
				//System.out.println("Problem creating artifact. (createAnnualPlan)\n");
				//System.out.println(ex);
				return null;
			}
	
		}
		// returns the plan id with the selected year else return null
		@Override
		public String getAnnualPlan(int year) 
		{
			try {
				boolean found = false;
				for (AnnualPlan plan : plans)
				{
					if(plan.getYear() == year)
					{
						found = true;
						return plan.getPlanId();
					}
				}
				
				if(!found)
				{
					//System.out.println("Annual Plan with year: " + year + " was not found");
					return "Annual Plan with year: " + year + " was not found";
				}
			}
			catch (Exception ex)
			{
				//System.out.println(ex);
				return null;
			}
			return null;
		}
		/*
		 *  adds an exhibit to an annual plan
		 *  designates the exhibit to a hall in a month
		 */
		@Override
	    public void addExhibitToAnnualPlan(String exhibitId, String planId, String hall, int month) 
		{
			try
			{
				boolean planFound = false;
				boolean exhibitFound = false;
				
				for (AnnualPlan plan : plans)
				{
					if(plan.getPlanId().equals(planId))
					{
						planFound = true;
						for (Exhibit exhibit : exhibits)
						{
							if(exhibit.getExhibitId().equals(exhibitId))
							{
								exhibitFound = true;
								if(plan.addExhibitHalls(exhibitId, hall, month))
								{
									//System.out.println("Exhibit: " + exhibitId + "  has been added to Annual Plan: " + planId + 
									//		"\nIn " + hall + " in " + convertToMonth(month));
								}
								else
								{
									//System.out.println();
								}
								

							}
						}
					}
				}
				
				if(!planFound)
				{
					//System.out.println("Annual Plan with id: " + planId + " could not be found");
				}
				if(!exhibitFound)
				{
					//System.out.println("Exhibit with id: " + exhibitId + " could not be found");
				}
			}
			catch(Exception ex)
			{
				//System.out.println(ex);
			}
			
		}
		
		/*
		 * Gets the exhibit in a specified hall and month in a plan
		 */
		@Override
		public String getAnnualPlanExhibit(String planId, String hall, int month) 
		{
			
			for (AnnualPlan plan : plans)
			{
				if(plan.getPlanId().equals(planId))
				{
					return plan.getExhibit(hall, month);
				}
			}
			

			//System.out.println("Annual Plan with id: " +  planId + " was not found");
			return null;

			
		}
		
		// Deletes an annual plan
		@Override
		public void deleteAnnualPlan(String planId) 
		{
			boolean found = false;
			int yearToBeRemoved = 0;
			
			for (int index = plans.size()-1; index >= 0; index--)
			{
				if(plans.get(index).getPlanId().equals(planId))
				{
					yearToBeRemoved = plans.get(index).getYear();
			
					plans.remove(index);
					found = true;
					
				}
			}
			
			
			
			if(!found)
			{
				//System.err.println("Annual plan with Id: " + planId + " was not found");
			}
			else
			{
				for (int index = usedYears.size()-1; index >= 0; index--)
				{
					if(yearToBeRemoved == usedYears.get(index))
					{
						usedYears.remove(index);
					}
				}
				
				//System.err.println("Annual plan with Id: " + planId + " has been successfully deleted");
			}
		}
		/*
		 * returns an annual plan info based off the infoName
		 */
		@Override
		public String getAnnualPlanInfo(String planId, String infoName) 
		{
			try
			{
				for (AnnualPlan plan : plans)
				{
					if(plan.getPlanId().equals(planId))
					{
						return plan.getAnnualPlanInfo(infoName);
					}
				}
				//System.out.println("Annual Plan with id: " +  planId + " was not found");
				return null;
			}
			catch(Exception ex)
			{
				//System.err.println(ex);
				return null;
			}
			
			
		}
		/*
		 * Updates an annual plan info based off the infoName
		 * 
		 * When updating a hall, users can enter "REMOVE" to remove the exhibit in
		 * a specified hall in a month, or override it.
		 */
		@Override
		public void updateAnnualPlanInfo(String planId, String infoName, String newValue) 
		{
			try
			{
				boolean found = false;
				int yearToBeRemoved = 0;
				for (AnnualPlan plan : plans)
				{
					if(plan.getPlanId().equals(planId))
					{
						if(infoName.equals("year"))
						{
							usedYears.add(Integer.parseInt(newValue));
							yearToBeRemoved = plan.getYear();
						}
						plan.updateAnnualPlanInfo(infoName, newValue, remove);
						found = true;
					}
				}
				
				
				if (!found)
				{
					//System.out.println("Plan with Id: " + planId + " has not been found");
				}
				else
				{
					if (yearToBeRemoved != 0)
					{
						for (int index = usedYears.size()-1; index >= 0; index--)
						{
							if(yearToBeRemoved == usedYears.get(index))
							{
								usedYears.remove(index);
							}
						}
						
					}
					
					//System.out.println("Plan with Id: " + planId + " has been successfully updated");
				}
			}
			catch(Exception ex)
			{
				//System.out.println(ex);
			}
		}
		
		
		
		
		
		
		
		
		
		
		

	    // --- Console UI Testing Support ---

		@Override
		public String getCurrentState() 
		{
			return "";
		}
		// gets the current prompt for the current state.
		@Override
		public String getCurrentPrompt()
		{
			updatePrompt();
			return currentPrompt;
		}

		@Override
	    public boolean isValidInput(String input) 
	    {
	    	return true;
	    }
	    
		@Override
		public ArrayList<String> getValidInputs()
	    {
	    	return null;
	    }

		@Override
	    public ArrayList<String> getInvalidInputs()
	    {
	    	return null;
	    }

		
		
		
		
		
		
		
		
		
		
		
		
		// -------------------- PROCESSING -------------------
		// Processes the input for the current state
		@Override
		public String processInput(String input)
	    {
			switch (this.currentState) {
			// === MAIN NAVIGATION ===
			case MAIN_MENU:
				return handleMainMenu(input); 

			case MANAGE_ARTIFACTS:
				return handleManageArtifacts(input);
			
			case VIEW_ARTIFACTS:
				return handleViewArtifacts(input);
				
			// ---------------- ARTIFACTS --------------
				

				
				
			
			// ----------------- MENUS ------------------------------
				
			
				
			case CREATE_ARTIFACT_NAME:
				return handleCreateArtifactName(input);
			
			case CREATE_ARTIFACT_DESCRIPTION:
				return handleCreateArtifactDescription(input);
			
			case CREATE_ARTIFACT_ENGAGEMENT_TIME:
				return handleCreateArtifactEngagement(input);
			
			case CREATE_ARTIFACT_TYPE:
				return handleCreateArtifactType(input);
			
			// ----------------------- CREATION OF ARTIFACTS ---------------------
			case HANDLE_ARTIFACT_ID:
				return handleGettingArtifactId(input);
			
				
			case HANDLE_ARTIFACT_INFO_NAME:
				return handleGettingInfoNameArtifact(input);
			
			
			case GET_ARTIFACT_ENGAGEMENT_TIME:
				return "";
				
			case HANDLE_ARTIFACT_SEARCH_NAME:
				return handleSearchNameArtifact(input);
			
			case HANDLE_ARTIFACT_SEARCH_NAME_CONTAINS:
				return handleSearchNameContainsArtifact(input);
				
			case HANDLE_ARTIFACT_SEARCH_TYPE:
				return handleSearchTypeArtifact(input);
					
			case HANDLE_ARTIFACT_SEARCH_ID:
				return handleSearchIdArtifact(input);
				
			case HANDLE_ARTIFACT_SEARCH_CRITERIA:
				return handleSearchCriteriaArtifact(input);
				
			case HANDLE_ARTIFACT_SORTBY:
				return handleSortByArtifact(input);
				
				
			// ----------------------- VIEWING OF ARTIFACTS -------------------
			case DELETE_ARTIFACT:
				return handleDeletingArtifacts(input);
			
			case UPDATE_ARTIFACT_NAME:
				return handleUpdateNameArtifact(input);
			
			case UPDATE_ARTIFACT_DESCRIPTION:
				return handleUpdateDescriptionArtifact(input);
				
			case UPDATE_ARTIFACT_TYPE:
				return handleUpdateTypeArtifact(input);
				
			case UPDATE_ARTIFACT_ENGAGEMENT_TIME:
				return handleUpdateEngagementArtifact(input);
			

				

		//------------------------ MANIPULATION OF ARTIFACTS --------------------
				

				
// -------------------------- ARTIFACTS ---------------------------------------------
			case MANAGE_EXHIBITS:
				return handleManageExhibits(input);
			
			case VIEW_EXHIBITS:
				return handleViewExhibits(input);
				
			// ------------ EXHIBIT MENUS --------------------
				
			case CREATE_EXHIBIT_NAME:
				return handleCreateExhibitName(input);
				
			case CREATE_EXHIBIT_DESCRIPTION:
				return handleCreateExhibitDescription(input);

			// ------------- EXHIBIT CREATION ----------------
				
			case HANDLE_EXHIBIT_ID:
				return handleGettingExhibitId(input);
			
			case HANDLE_EXHIBIT_INFO_NAME:
				return handleGettingInfoNameExhibit(input);
				
			case DELETE_EXHIBIT:
				return handleDeletingExhibits(input);
				
			case UPDATE_EXHIBIT_NAME:
				return handleUpdateNameExhibit(input);
				
			case UPDATE_EXHIBIT_DESCRIPTION:
				return handleUpdateDescriptionExhibit(input);
				
			case ADD_EXHIBIT_ARTIFACT:
				return handleAddingExhibitArtifact(input);
				
			case REMOVE_EXHIBIT_ARTIFACT:
				return handleRemovingExhibitArtifact(input);
				
			case HANDLE_EXHIBIT_SEARCH_NAME:
				return handleSearchNameExhibit(input);
			
			case HANDLE_EXHIBIT_SEARCH_NAME_CONTAINS:
				return handleSearchNameContainsExhibit(input);
					
			case HANDLE_EXHIBIT_SEARCH_ID:
				return handleSearchIdExhibit(input);
				
			case HANDLE_EXHIBIT_SEARCH_CRITERIA:
				return handleSearchCriteriaExhibit(input);
				
			case HANDLE_EXHIBIT_SORTBY:
				return handleSortByExhibit(input);
			
			case REORDER_EXHIBIT_ARTIFACTS:
				return handleReorderExhibitArtifacts(input);
				
			// ------------------ EXHIBITS -----------------
				
			case VIEW_ANNUAL_PLANS:
				return handleViewPlan(input);
				
			case MANAGE_ANNUAL_PLANS:
				return handleManagePlan(input);
				
			// ------------------ ANNUAL PLAN MENUS --------
				
			case HANDLE_ANNUAL_PLAN_ID:
				return handleGettingPlanId(input);
				
			case CREATE_ANNUAL_PLAN:
				return handleCreatePlan(input);
				
			case DELETE_ANNUAL_PLAN:
				return handleDeletingPlan(input);
				
			case HANDLE_ANNUAL_PLAN_INFO_NAME:
				return handleGettingInfoNamePlan(input);
				
			case UPDATE_ANNUAL_PLAN_YEAR:
				return handleUpdateYearPlan(input);
				
			case UPDATE_ANNUAL_PLAN_HALL:
				return handleUpdateHallPlan(input);
				
			case UPDATE_ANNUAL_PLAN_MONTH:
				return handleUpdateMonthPlan(input);
			
			case ADD_ANNUAL_PLAN_EXHIBIT_HALL:
				return handleCreateHallPlan(input);
						
			case ADD_ANNUAL_PLAN_EXHIBIT_MONTH:
				return handleCreateMonthPlan(input);
				
			case GET_ANNUAL_PLAN:
				return handleGettingPlan(input);
				
			case GET_ANNUAL_PLAN_MONTH:
				return handleGettingMonthPlan(input);
			
			case GET_ANNUAL_PLAN_HALL:
				return handleGettingHallPlan(input);
			// ------------- ADDITIONAL FEATURES -------
			case SAVE_DATA:
				return handleSaveData(input);
				
			case LOAD_DATA:
				return handleLoadData(input);
			}
			
			
			return "EXIT";
	    	
	    }
		
		
		
		
		
		
		
		
		// -------------------- PROMPTS ------------------------------
		// updates the current prompt for the current state
		private void updatePrompt() 
		{
			switch (currentState) {
			case MAIN_MENU:
				currentPrompt = "\nUlster Museum Menu\n" +
						"++++++++++\n" +
						"1. Manage Artifacts\n" +
						"2. Manage Exhibits\n" +
						"3. Manage Annual Plans\n" +
						"4. Save Data\n" +
						"5. Load Data\n" +
						"6. Exit\n" +
						"Enter Selection: ";
				break;
			case MANAGE_ARTIFACTS:
				currentPrompt = "\nUlster Museum Manage Artifacts\n" +
						"++++++++++\n" +
						"1. Create Artifact\n" +
						"2. View Artifacts\n" +
						"3. Delete Artifact\n" +
						"4. Update Artifact\n" +
						"5. Return to Main Menu\n" +
						"Enter Selection: ";
				break;
				
			case VIEW_ARTIFACTS:
				currentPrompt = "\nUlster Museum View Artifacts\n" +
						"++++++++++\n" +
						"1. View all artifacts\n" +
						"2. Get Artifact Info \n" +
						"3. Get Artifact Engagement Time\n" +
						"4. Find Artifacts \n" +
						"5. Return to Main Menu \n" +
						"Enter Selection: ";
				break;		
				
		
			
			// --------------- MENUS ---------------
				
			case CREATE_ARTIFACT_NAME:
				currentPrompt = "\nEnter Artifact name: ";
				break;
			
			case CREATE_ARTIFACT_DESCRIPTION:
				currentPrompt = "\nEnter Artifact description: ";
				break;
			
			case CREATE_ARTIFACT_ENGAGEMENT_TIME:
				currentPrompt = "\nEnter Artifact engagement time in minutes: ";
				break;
			
			case CREATE_ARTIFACT_TYPE:
				currentPrompt = "\nUlster Museum Available Artifact Types\n"+
				"++++++++++\n" +
				"1. PAINTING"+ "		7. TOOL\n"+
				"2. SCULPTURE"+ "		8. FOSSIL\n"+
				"3. POTTERY"+ "		9. COIN\n"+
				"4. JEWELRY"+  "		10. PHOTOGRAPH\n"+
				"5. TEXTILE"+ "		11. DOCUMENT\n"+
				"6. WEAPON"+ "		12. OTHER\n"+
				"Enter Selection:";
				break;
			
			// --------------- CREATING ARTIFACTS -----------
				
			case HANDLE_ARTIFACT_ID:
				currentPrompt = "\nEnter \"EXIT\" to go back to main menu \nEnter an artifact id : ";
				break;
			
			case HANDLE_ARTIFACT_INFO_NAME:
				currentPrompt = "\nUlster Museum Available Info Names\n" +
						"++++++++++\n" +
						"1. Name\n" +
						"2. Type \n" +
						"3. Description\n" +
						"4. Engagement_minutes \n" +
						"Enter Selection: ";
				break;
				
			case HANDLE_ARTIFACT_SEARCH_CRITERIA:
				currentPrompt = "\nUlster Museum Available Search Criterias\n" +
						"++++++++++\n" +
						"1. Name\n" +
						"2. Name contains \n" +
						"3. Id\n" +
						"4. Type\n" +
						"5. No Search criteria \n" +
						"Enter Selection: ";
				break;
				
			case HANDLE_ARTIFACT_SEARCH_NAME:
				currentPrompt = "\nEnter Artifact name to search: ";
				break;
			
			case HANDLE_ARTIFACT_SEARCH_NAME_CONTAINS:
				currentPrompt = "\nEnter containing text in artifact name to search: ";
				break;
				
			case HANDLE_ARTIFACT_SEARCH_TYPE:
				currentPrompt = "\nUlster Museum Available Artifact Types To Search\n"+
						"++++++++++\n" +
						"1. PAINTING"+ "		7. TOOL\n"+
						"2. SCULPTURE"+ "		8. FOSSIL\n"+
						"3. POTTERY"+ "		9. COIN\n"+
						"4. JEWELRY"+  "		10. PHOTOGRAPH\n"+
						"5. TEXTILE"+ "		11. DOCUMENT\n"+
						"6. WEAPON"+ "		12. OTHER\n"+
						"Enter Selection:";
				break;
				
			case HANDLE_ARTIFACT_SEARCH_ID:
				currentPrompt = "\nArtifact Id to search: ";
				break;
			
			case HANDLE_ARTIFACT_SORTBY:
				currentPrompt = "\nQUB How would you like to sort the result?\n" +
						"++++++++++\n" +
						"1. Name\n" +
						"2. Type \n" +
						"3. Id\n" +
						"4. Engagement \n" +
						"5. Default \n" +
						"Enter Selection: ";
				break;
			
			// ---------------- VIEWING ARTIFACTS ------------

			case UPDATE_ARTIFACT_NAME:
				currentPrompt = "\nEnter new name: ";
				break;
			
			case UPDATE_ARTIFACT_DESCRIPTION:
				currentPrompt = "\nEnter new description: ";
				break;
				
			case UPDATE_ARTIFACT_TYPE:
				currentPrompt = "\nUlster Museum Available Artifact Types\n"+
						"++++++++++\n" +
						"1. PAINTING"+ "		7. TOOL\n"+
						"2. SCULPTURE"+ "		8. FOSSIL\n"+
						"3. POTTERY"+ "		9. COIN\n"+
						"4. JEWELRY"+  "		10. PHOTOGRAPH\n"+
						"5. TEXTILE"+ "		11. DOCUMENT\n"+
						"6. WEAPON"+ "		12. OTHER\n"+
						"Enter Selection:";
				break;
				
			case UPDATE_ARTIFACT_ENGAGEMENT_TIME:
				currentPrompt = "\nEnter engagement time in minutes: ";
				break;
			
			case DELETE_ARTIFACT:
				currentPrompt = "\nArtifact Deletion Confirmation\n"+
								"++++++++++\n" +
								"1.To delete\n"+
								"2.To return to main menu\n"+
								"Enter Selection: ";
				break;

			// ---------------- MANAGE ARTIFACTS ---------------
				
				
				
			case MANAGE_EXHIBITS:
				currentPrompt = "\nUlster Museum Manage Exhibits\n" +
						"++++++++++\n" +
						"1. Create Exhibit\n" +
						"2. View Exhibits\n" +
						"3. Delete Exhibit\n" +
						"4. Update Exhibit\n" +
						"5. Remove Artifact from Exhibit \n" +
						"6. Add Artifact to Exhibit \n" +
						"7. Re-order Exhibit Artifacts \n" +
						"8. Return to Main Menu\n" +
						"Enter Selection: ";
				break;
			
			case VIEW_EXHIBITS:
				currentPrompt = "\nUlster Museum View Exhibits\n" +
						"++++++++++\n" +
						"1. View all Exhibits\n" +
						"2. Get Exhibits Info \n" +
						"3. Find Exhibits \n" +
						"4. Get Exhibit Artifact Signage \n" +
						"5. Get Exhibit Artifacts \n" +
						"6. Return to Main Menu \n" +
						"Enter Selection: ";
				break;
			
				
			// ----------- EXHIBIT MENUS ------------------------------
			case CREATE_EXHIBIT_NAME:
				currentPrompt = "\nEnter Exhibit name: ";
				break;
				
			case CREATE_EXHIBIT_DESCRIPTION:
				currentPrompt = "\nEnter Exhibit description: ";
				break;
				
			// ------------ EXHIBIT CREATION -------------
				
			case HANDLE_EXHIBIT_ID:
				currentPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
								"Enter an exhibit id : ";
				if(MenuState.UPDATE_ANNUAL_PLAN_HALL_EXHIBIT == stateType)
				{
					currentPrompt = "\nEnter \"EXIT\" to go back to main menu\n"
							+ "Enter \"REMOVE\" to remove the exhibit in the hall\n" 
							+"Enter an exhibit id : ";
				}
				break;
			
			case HANDLE_EXHIBIT_INFO_NAME:
				if (stateType == MenuState.GET_EXHIBIT_INFO)
				{
					currentPrompt = "\nUlster Museum Available Info Names\n" +
							"++++++++++\n" +
							"1. Name\n" +
							"2. Description\n" +
							"3. Total Engagement time\n" +
							"Enter Selection: ";
				}
				else
				{
					currentPrompt = "\nUlster Museum Available Info Names\n" +
							"++++++++++\n" +
							"1. Name\n" +
							"2. Description\n" +
							"Enter Selection: ";
				}
				
				break;

			case REORDER_EXHIBIT_ARTIFACTS:
				currentPrompt = "\nQUB Artifact Order\n";
				int artPos = 1;
				for(String art : reOrderArtifactIds)
				{
					currentPrompt += artPos + ". " + art +"\n";
					artPos++;
				}
				currentPrompt += "Please enter artifacts starting from the first to be shown: ";
				break;
				
			case DELETE_EXHIBIT:
				currentPrompt = "\nExhibit Deletion Confirmation\n"+
						"++++++++++\n" +
						"1.To delete\n"+
						"2.To return to main menu\n"+
						"Enter Selection: ";
				break;
				
			case UPDATE_EXHIBIT_NAME:
				currentPrompt = "\nEnter new name: ";
				break;
				
			case UPDATE_EXHIBIT_DESCRIPTION:
				currentPrompt = "\nEnter new description: ";
				break;
				
			case ADD_EXHIBIT_ARTIFACT:
				currentPrompt = "\nEnter Artifact Signage: ";
				break;
				
			case REMOVE_EXHIBIT_ARTIFACT:
				currentPrompt = "\nExhibit Artifact Removal Confirmation\n"+
						"++++++++++\n" +
						"1.To Remove\n"+
						"2.To return to main menu\n"+
						"Enter Selection: ";
				break;
			
			case HANDLE_EXHIBIT_SEARCH_CRITERIA:
				currentPrompt = "\nUlster Museum Available Search Criterias\n" +
						"++++++++++\n" +
						"1. Name\n" +
						"2. Name contains \n" +
						"3. Id\n" +
						"4. No Search criteria \n" +
						"Enter Selection: ";
				break;
				
			case HANDLE_EXHIBIT_SEARCH_NAME:
				currentPrompt = "Enter Exhibit name to search: ";
				break;
				
			case HANDLE_EXHIBIT_SEARCH_NAME_CONTAINS:
				currentPrompt = "\nEnter containing text in exhibit name to search: ";
				break;
				
			case HANDLE_EXHIBIT_SEARCH_ID:
				currentPrompt = "\nExhibit Id to search: ";
				break;

			case HANDLE_EXHIBIT_SORTBY:
				currentPrompt = "\nQUB How would you like to sort the result?\n" +
						"++++++++++\n" +
						"1. Name\n" +
						"2. Id\n" +
						"3. Default \n" +
						"Enter Selection: ";
				break;
				
			// -------------------- EXHIBITS ---------------------
				
			case MANAGE_ANNUAL_PLANS:
				currentPrompt = "\nUlster Museum Manage Annual Plans\n" +
						"++++++++++\n" +
						"1. Create Annual Plan\n" +
						"2. View Annual Plans\n" +
						"3. Delete Annual Plan\n" +
						"4. Update Annual Plan\n" +
						"5. Add Exhibit to Annual Plan\n" +
						"6. Return to Main Menu\n" +
						"Enter Selection: ";
				break;
				
			case VIEW_ANNUAL_PLANS:
				currentPrompt = "\nUlster Museum View Annual Plans\n" +
						"++++++++++\n" +
						"1. View Annual Plans\n" +
						"2. Get Annual Plan\n" +
						"3. Get Annual Plan Info\n" +
						"4. Get Annual Plan Exhibit\n" +
						"5. Return to Main Menu\n" +
						"Enter Selection: ";
				break;
			// ----------------- ANNUAL PLAN MENUS -------------
				
			case HANDLE_ANNUAL_PLAN_ID:
				currentPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an plan id : ";
				break;
			
			case CREATE_ANNUAL_PLAN:
				currentPrompt = "\nPlease enter a year: ";
				break;
				
			case DELETE_ANNUAL_PLAN:
				currentPrompt ="\nAnnual Plan Deletion Confirmation\n"+
				"++++++++++\n" +
				"1.To delete\n"+
				"2.To return to main menu\n"+
				"Enter Selection: ";
				break;
			
			case HANDLE_ANNUAL_PLAN_INFO_NAME:
				if(stateType.equals(MenuState.GET_ANNUAL_PLAN_INFO))
				{
					currentPrompt ="\nUlster Museum Available Info Names\n"+
							"++++++++++\n" +
							"1.Year\n"+
							"2.Total Exhibits\n"+
							"Enter Selection: ";
				}
				else
				{
					currentPrompt ="\nUlster Museum Available Info Names\n"+
							"++++++++++\n" +
							"1.Year\n"+
							"2.Hall\n"+
							"Enter Selection: ";
				}
				break;
				
			case UPDATE_ANNUAL_PLAN_YEAR:
				currentPrompt = "\nPlease enter a year: ";
				break;
				
			case UPDATE_ANNUAL_PLAN_HALL:
				currentPrompt ="\nUlster Museum Available Halls\n"+
				"++++++++++\n" +
				"1.Hall A\n"+
				"2.Hall B\n"+ 
				"3.Hall C\n"+
				"Enter Selection: ";
				break;
			
			case UPDATE_ANNUAL_PLAN_MONTH:
				currentPrompt ="\nUlster Museum Months\n"+
				"++++++++++\n" +
				"1. January"+ "	7. July\n"+
				"2. February"+ "	8. August\n"+
				"3. March"+ "	9. September\n"+
				"4. April"+  "	10. October\n"+
				"5. May"+ "		11. November\n"+
				"6. June"+ "		12. December\n"+
				"Enter Selection:";
				break;
			
			case ADD_ANNUAL_PLAN_EXHIBIT_HALL:
				currentPrompt ="\nUlster Museum Available Halls\n"+
						"++++++++++\n" +
						"1.Hall A\n"+
						"2.Hall B\n"+ 
						"3.Hall C\n"+
						"Enter Selection: ";
						break;
						
			case ADD_ANNUAL_PLAN_EXHIBIT_MONTH:
				currentPrompt ="\nUlster Museum Months\n"+
						"++++++++++\n" +
						"1. January"+ "	7. July\n"+
						"2. February"+ "	8. August\n"+
						"3. March"+ "	9. September\n"+
						"4. April"+  "	10. October\n"+
						"5. May"+ "		11. November\n"+
						"6. June"+ "		12. December\n"+
						"Enter Selection:";
						break;
						
						
			case GET_ANNUAL_PLAN:
				currentPrompt = "\nPlease Enter a year to search: ";
				break;
				
			case GET_ANNUAL_PLAN_MONTH:
				currentPrompt ="\nUlster Museum Months\n"+
						"++++++++++\n" +
						"1. January"+ "	7. July\n"+
						"2. February"+ "	8. August\n"+
						"3. March"+ "	9. September\n"+
						"4. April"+  "	10. October\n"+
						"5. May"+ "		11. November\n"+
						"6. June"+ "		12. December\n"+
						"Enter Selection:";
						break;
				
			case GET_ANNUAL_PLAN_HALL:
				currentPrompt ="\nUlster Museum Available Halls\n"+
						"++++++++++\n" +
						"1.Hall A\n"+
						"2.Hall B\n"+ 
						"3.Hall C\n"+
						"Enter Selection: ";
						break;
						
			// ---------- ADDITIONAL FEATURES -----------
						
			case SAVE_DATA:
				currentPrompt ="\nUlster Museum Save Data\n"+
						"++++++++++\n" +
						"1.Save Data\n"+
						"2.Return to Main Menu \n"+ 
						"Enter Selection: ";
				break;
				
			case LOAD_DATA:
				currentPrompt ="\nUlster Museum Load Data\n"+
						"++++++++++\n" +
						"1.Load Data\n"+
						"2.Return to Main Menu \n"+ 
						"Enter Selection: ";
				break;
			}
		} 
		
		
		
		
		// transitions the current state to the next specified state
		private void transitionTo(MenuState newState) {
			currentState = newState;
			updatePrompt();
		}
		
		
		public String handleViewArtifacts(String input) {
			Scanner inputIn = new Scanner(System.in);
			
			switch (input) {
			case "1":
				ArrayList<Artifact> displayArtifacts = new ArrayList<Artifact>();
				String returnPrompt ="";
				for(Artifact art : artifacts)
				{
					displayArtifacts.add(art);
				}
				
				boolean sorted = false;
				
				while (!sorted)
				{
					boolean changed = false;
					
					for(int nextIndex = 1; nextIndex < displayArtifacts.size(); nextIndex++)
					{
						int previousIndex = nextIndex-1;
						
						if(displayArtifacts.get(previousIndex).getName().compareTo(displayArtifacts.get(nextIndex).getName()) > 0)
						{
							changed = true;
							Artifact temp = displayArtifacts.get(nextIndex);
							displayArtifacts.set(nextIndex, displayArtifacts.get(previousIndex));
							displayArtifacts.set(previousIndex, temp);
							
						}
					}
					if(!changed)
					{
						sorted = true;
					}
				
				}
				
				for(Artifact art : displayArtifacts)
				{
					returnPrompt +=
							"\nId: " + art.getArtifactId() +"\n"
						+ "Name: " + art.getName() + "\n"
						+ "Type: " + art.getArtifactType() + "\n"
						+ "Description: " + art.getArtifactDescription() + "\n"
						+ "Engagement Time: "+ art.getArtifactEngagementTime() + " minutes" +"\n";
					
					
				}
				return returnPrompt;
			case "2":
				transitionTo(MenuState.HANDLE_ARTIFACT_ID);
				nextState = MenuState.HANDLE_ARTIFACT_INFO_NAME;
				stateType = MenuState.GET_ARTIFACT_INFO;
				return "";
			case "3":
				transitionTo(MenuState.HANDLE_ARTIFACT_ID);
				nextState = MenuState.GET_ARTIFACT_ENGAGEMENT_TIME;
				return "";
			case "4":
				transitionTo(MenuState.HANDLE_ARTIFACT_SEARCH_CRITERIA);
				return "";
			case "5":
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}  
			
			System.out.println("Invalid Response - Try Again");
			return"";
		}
		
		
		public String handleManageArtifacts(String input) {
			Scanner inputIn = new Scanner(System.in);
			
			switch (input) {
			case "1":
				transitionTo(MenuState.CREATE_ARTIFACT_NAME);
				return "";
			case "2":
				transitionTo(MenuState.VIEW_ARTIFACTS);
				
				return "";
			case "3":
				transitionTo(MenuState.HANDLE_ARTIFACT_ID);
				nextState = MenuState.DELETE_ARTIFACT;
				return "";
			case "4":
				transitionTo(MenuState.HANDLE_ARTIFACT_ID);
				nextState = MenuState.HANDLE_ARTIFACT_INFO_NAME;
				stateType = MenuState.HANDLE_ARTIFACT_NEW_VALUE;
				return "";
			case "5":
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}  
			
			System.out.println("Invalid Response - Try Again");
			return"";
			
		}
		
	
		
		// ----------- ARTIFACT MENU HANDLERS----------
		
		public String handleMainMenu(String input) {
			
			switch (input) {
			case "1":
				transitionTo(MenuState.MANAGE_ARTIFACTS);
				return "";
			case "2":
				transitionTo(MenuState.MANAGE_EXHIBITS);
				return "";
			case "3":
				transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
				return "";
			case "4":
				transitionTo(MenuState.SAVE_DATA);
				return "";
			case "5":
				transitionTo(MenuState.LOAD_DATA);
				return "";
			case "6":
				transitionTo(MenuState.EXIT);
				return "EXIT";
			}  
			return"Invalid Response - Try Again";
			
		}
		
		// -------------- MENU HANDLERS ---------------------
		
		private String handleCreateArtifactName(String input) {
			if (input.trim().isEmpty()) {
				return "Name cannot be empty. Please enter a name.";
			}
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			artifactStateData.put("name", input.trim());
			transitionTo(MenuState.CREATE_ARTIFACT_DESCRIPTION);
			return "";
		}
		
		
		private String handleCreateArtifactDescription(String input) {
			if (input.trim().isEmpty()) {
				return "Description cannot be empty. Please enter a description.";
			}
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			artifactStateData.put("description", input.trim());
			transitionTo(MenuState.CREATE_ARTIFACT_ENGAGEMENT_TIME);
			return "";
			
		}
		
		
		private String handleCreateArtifactEngagement(String input) {
			try
			{
				if (input.trim().isEmpty()) {
					return "Engagement cannot be empty. Please enter an engagement time in minutes.";
				}
				else if (Integer.parseInt(input) <= 0)
				{
					return "Engagement must be higher than 0";
				}

				artifactStateData.put("engagement", input.trim());
				
				transitionTo(MenuState.CREATE_ARTIFACT_TYPE);
				return "";
			}
			catch(Exception ex)
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			
		}
		
		
		private String handleCreateArtifactType(String input) {
			
			String type;
			
			switch (input) {
				case "1":
					type = "Painting";
					break;
				case "2":
					type = "Sculpture";
					break;
				case "3":
					type = "Pottery";
					break;
				case "4":
					type = "Jewelry";
					break;
				case "5":
					type = "Textile";
					break;
				case "6":
					type = "Weapon";
					break;
				case "7":
					type = "Tool";
					break;
				case "8":
					type = "Fossil";
					break;
				case "9":
					type = "Coin";
					break;
				case "10":
					type = "Photograph";
					break;
				case "11":
					type = "Document";
					break;
				case "12":
					type = "Other";
					break;
				default:
					type = "";
					break;
			}  
			if(type == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			else
			{
				artifactStateData.put("type", type.toString().trim());
				transitionTo(MenuState.MANAGE_ARTIFACTS);
				return createArtifact(
						artifactStateData.get("type"), 
						artifactStateData.get("name"), 
						artifactStateData.get("description"), 
						Integer.parseInt(artifactStateData.get("engagement"))
						);
				
			}
			
			
		}
		// ------------ CREATE ARTIFACT HANDLERS --------------
		
		private String handleGettingArtifactId(String input) {
			
			if(input.trim().equals("EXIT"))
			{
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}
			
			
			
			boolean found = false;
			if(nextState == MenuState.GET_ARTIFACT_SIGN)
			{
				for (String art : getExhibitArtifacts(exhibitStateData.get("id")))
				{
					
					if(art.equals(input))
					{
						found = true;
						artifactStateData.put("id", input.trim());
					}
				}
			}
			else
			{
				for (Artifact art : artifacts)
				{
					
					if(art.getArtifactId().equals(input))
					{
						found = true;
						artifactStateData.put("id", input.trim());
					}
				}
			}
			
			
			if(found == false)
			{
				System.out.println("\nInvalid Response - Try Again");
				System.out.println("Here is a list of valid artifactIds:");
				if(nextState == MenuState.GET_ARTIFACT_SIGN)
				{
					if(getExhibitArtifacts(exhibitStateData.get("id")).size() >= 2)
					{
						for (int index = 0; index < getExhibitArtifacts(exhibitStateData.get("id")).size() - 1;index++)
						{
							System.out.print(getExhibitArtifacts(exhibitStateData.get("id")).get(index) + " ,");
						}
						System.out.print(getExhibitArtifacts(exhibitStateData.get("id")).get(getExhibitArtifacts(exhibitStateData.get("id")).size() - 1));
						System.out.println("\n");
						return"";
					}
					else if (getExhibitArtifacts(exhibitStateData.get("id")).size() != 0)
					{
						System.out.print(getExhibitArtifacts(exhibitStateData.get("id")).get(0));
						System.out.println("\n");
						return"";
					}
					else
					{
						return "No artifacts in " + exhibitStateData.get("id");
					}
				}
				else 
				{
					if(artifacts.size() >= 2)
					{
						for (int index = 0; index < artifacts.size() - 1;index++)
						{
							System.out.print(artifacts.get(index).getArtifactId() + " ,");
						}
						System.out.print(artifacts.get(artifacts.size() - 1).getArtifactId());
						System.out.println("\n");
						return"";
					}
					else if (artifacts.size() != 0)
					{
						System.out.print(artifacts.get(0).getArtifactId());
						System.out.println("\n");
						return"";
					}
					else
					{
						System.out.println("\nThere are no Artifacts Made");
						return"";
					}
				}
				
				
			}
			else
			{
				switch (nextState)
				{
					case GET_ARTIFACT_ENGAGEMENT_TIME:
						transitionTo(MenuState.MANAGE_ARTIFACTS);
						return "\nEngagement Time: " + Integer.toString(getArtifactEngagementTime(artifactStateData.get("id"))) + " minutes";
					
					case GET_ARTIFACT_SIGN:
						transitionTo(MenuState.MANAGE_EXHIBITS);
						return "Signage: " + getExhibitArtifactSign(exhibitStateData.get("id"), artifactStateData.get("id"));
				}
				
				transitionTo(nextState);
				return "";
			}
			
			
		}
		
		private String handleGettingInfoNameArtifact(String input)
		{
			String valid = "pass";
			switch (input) {
			case "1":
				artifactStateData.put("infoName", "name");
				if(stateType != MenuState.GET_ARTIFACT_INFO) { stateType= MenuState.UPDATE_ARTIFACT_NAME;}
				break;

			case "2":
				artifactStateData.put("infoName", "type");
				if(stateType != MenuState.GET_ARTIFACT_INFO) { stateType= MenuState.UPDATE_ARTIFACT_TYPE;}
				break;

			case "3":
				artifactStateData.put("infoName", "description");
				if(stateType != MenuState.GET_ARTIFACT_INFO) { stateType= MenuState.UPDATE_ARTIFACT_DESCRIPTION;}
				break;

			case "4":
				artifactStateData.put("infoName", "engagement_minutes");
				if(stateType != MenuState.GET_ARTIFACT_INFO) { stateType= MenuState.UPDATE_ARTIFACT_ENGAGEMENT_TIME;}
				break;

			default:
				valid = "";
				break;
			}
			

			
			if(valid == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";

			}
			else
			{
				switch (stateType)
				{
				case GET_ARTIFACT_INFO:
					transitionTo(MenuState.MAIN_MENU);
					return getArtifactInfo(artifactStateData.get("id") , artifactStateData.get("infoName"));
				case UPDATE_ARTIFACT_NAME:
					transitionTo(MenuState.UPDATE_ARTIFACT_NAME);
					return "";
				case UPDATE_ARTIFACT_TYPE:
					transitionTo(MenuState.UPDATE_ARTIFACT_TYPE);
					return "";
				case UPDATE_ARTIFACT_DESCRIPTION:
					transitionTo(MenuState.UPDATE_ARTIFACT_DESCRIPTION);
					return "";
				case UPDATE_ARTIFACT_ENGAGEMENT_TIME:
					transitionTo(MenuState.UPDATE_ARTIFACT_ENGAGEMENT_TIME);
					return "";
					
					
					
				}
				System.err.println("StateType broke :( ");
				return "";
				
			}
			
			
			
			
		}
		
		private String handleUpdateNameArtifact(String input)
		{
			if (input.trim().isEmpty()) {
				return "Name cannot be empty. Please enter a name.";
			}
			
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			
			artifactStateData.put("newValue", input.trim());
			updateArtifactInfo(artifactStateData.get("id"), artifactStateData.get("infoName"), artifactStateData.get("newValue"));
			transitionTo(MenuState.MAIN_MENU);
			return "";
		}
		
		private String handleUpdateTypeArtifact(String input)
		{
			String type;
			
			switch (input) {
				case "1":
					type = "Painting";
					break;
				case "2":
					type = "Sculpture";
					break;
				case "3":
					type = "Pottery";
					break;
				case "4":
					type = "Jewelry";
					break;
				case "5":
					type = "Textile";
					break;
				case "6":
					type = "Weapon";
					break;
				case "7":
					type = "Tool";
					break;
				case "8":
					type = "Fossil";
					break;
				case "9":
					type = "Coin";
					break;
				case "10":
					type = "Photograph";
					break;
				case "11":
					type = "Document";
					break;
				case "12":
					type = "Other";
					break;
				default:
					type = "";
					break;
			}  
			if(type == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			else
			{
				artifactStateData.put("newValue", input.trim());
				updateArtifactInfo(artifactStateData.get("id"), artifactStateData.get("infoName"), type);
				transitionTo(MenuState.MAIN_MENU);
				return "";
				
			}
		}
		
		private String handleUpdateDescriptionArtifact(String input)
		{
			if (input.trim().isEmpty()) {
				return "Description cannot be empty. Please enter a description.";
			}
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			
			artifactStateData.put("newValue", input.trim());
			updateArtifactInfo(artifactStateData.get("id"), artifactStateData.get("infoName"), artifactStateData.get("newValue"));
			transitionTo(MenuState.MANAGE_ARTIFACTS);
			return "";
		}
		
		private String handleUpdateEngagementArtifact(String input)
		{
			try
			{
				if (input.trim().isEmpty()) {
					return "Engagement cannot be empty. Please enter an engagement time in minutes.";
				}
				else if (Integer.parseInt(input) <= 0)
				{
					return "Engagement must be higher than 0";
				}

				artifactStateData.put("newValue", input.trim());
				updateArtifactInfo(artifactStateData.get("id"), artifactStateData.get("infoName"), artifactStateData.get("newValue"));
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}
			catch(Exception ex)
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
		}
		
		private String handleDeletingArtifacts(String input)
		{
			switch (input)
			{
			case "1":
				deleteArtifact(artifactStateData.get("id"));
				transitionTo(MenuState.MAIN_MENU);
				return "";
			case "2":
				transitionTo(MenuState.MAIN_MENU);
				return"";
			}
			System.out.println("Invalid Response - Try Again");
			return"";

		}
		
		private String handleSearchCriteriaArtifact(String input)
		{
			switch (input) {
			case "1":
				artifactStateData.put("searchCriteria", "name=");
				transitionTo(MenuState.HANDLE_ARTIFACT_SEARCH_NAME);
				return "";
			case "2":
				artifactStateData.put("searchCriteria", "name_contains=");
				transitionTo(MenuState.HANDLE_ARTIFACT_SEARCH_NAME_CONTAINS);
				return "";
			case "3":
				artifactStateData.put("searchCriteria", "id=");
				transitionTo(MenuState.HANDLE_ARTIFACT_SEARCH_ID);
				return "";
			case "4":
				artifactStateData.put("searchCriteria", "type=");
				transitionTo(MenuState.HANDLE_ARTIFACT_SEARCH_TYPE);
				return "";
			case "5":
				artifactStateData.put("searchCriteria", "=");
				transitionTo(MenuState.HANDLE_ARTIFACT_SORTBY);
				return "";
			}
			System.out.println("Invalid Response - Try Again");
			return"";
		}
		
		private String handleSearchTypeArtifact(String input)
		{
			String type;
			
			switch (input) {
				case "1":
					type = "Painting";
					break;
				case "2":
					type = "Sculpture";
					break;
				case "3":
					type = "Pottery";
					break;
				case "4":
					type = "Jewelry";
					break;
				case "5":
					type = "Textile";
					break;
				case "6":
					type = "Weapon";
					break;
				case "7":
					type = "Tool";
					break;
				case "8":
					type = "Fossil";
					break;
				case "9":
					type = "Coin";
					break;
				case "10":
					type = "Photograph";
					break;
				case "11":
					type = "Document";
					break;
				case "12":
					type = "Other";
					break;
				default:
					type = "";
					break;
			}  
			if(type == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			else
			{
				artifactStateData.put("searchCriteria", artifactStateData.get("searchCriteria") + type);
				transitionTo(MenuState.HANDLE_ARTIFACT_SORTBY);
				return "";
				
			}
			
		}
		
		private String handleSearchNameArtifact(String input)
		{
			if (input.trim().isEmpty()) {
				return "Text cannot be empty. Please enter text.";
			}
			
			artifactStateData.put("searchCriteria", artifactStateData.get("searchCriteria") + input.trim());
			transitionTo(MenuState.HANDLE_ARTIFACT_SORTBY);
			return "";
		}
		
		private String handleSearchNameContainsArtifact(String input)
		{
			if (input.trim().isEmpty()) {
				return "Text for name contains cannot be empty. Please enter non empty text.";
			}
			
			artifactStateData.put("searchCriteria", artifactStateData.get("searchCriteria") + input.trim());
			transitionTo(MenuState.HANDLE_ARTIFACT_SORTBY);
			return "";
		}

		private String handleSearchIdArtifact(String input)
		{
			boolean found = false;
			for (Artifact art : artifacts)
			{
				
				if(art.getArtifactId().equals(input))
				{
					found = true;
					artifactStateData.put("id", input.trim());
				}
			}
			
			
			if(found == false)
			{
				System.out.println("\nInvalid Response - Try Again");
				System.out.println("Here is a list of valid artifactIds:");
				if(artifacts.size() >= 2)
				{
					for (int index = 0; index < artifacts.size() - 1;index++)
					{
						System.out.print(artifacts.get(index).getArtifactId() + " ,");
					}
					System.out.print(artifacts.get(artifacts.size() - 1).getArtifactId());
					System.out.println("\n");
					return"";
				}
				else
				{
					System.out.print(artifacts.get(0).getArtifactId());
					System.out.println("\n");
					return"";
				}
			
				
			}
			else
			{
				artifactStateData.put("searchCriteria", artifactStateData.get("searchCriteria") + input.trim());
				transitionTo(MenuState.HANDLE_ARTIFACT_SORTBY);
				return "";
			}
			
			
		}
		
		private String handleSortByArtifact(String input)
		{
			String valid = "pass";
			String returnString = "| ";
			switch (input) {
			case "1":
				artifactStateData.put("sortBy", "name");
				break;
			case "2":
				artifactStateData.put("sortBy", "type");
				break;
			case "3":
				artifactStateData.put("sortBy", "id");
				break;
			case "4":
				artifactStateData.put("sortBy", "engagement");
				break;
			case "5":
				artifactStateData.put("sortBy", "id");
				break;
			default:
					valid = "";
			}
			
			//System.err.println("Search Criteria: " + artifactStateData.get("searchCriteria") + "\nSort By: " + artifactStateData.get("sortBy"));
			if(valid == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			else
			{
				for(String artifactId : findArtifacts(artifactStateData.get("searchCriteria"), artifactStateData.get("sortBy")))
				{
					returnString += artifactId + " | ";
				}
				
				transitionTo(MenuState.MANAGE_ARTIFACTS);
				return returnString;
			}
			
		}
		
		
		// ---------------- VIEWING AND MANIPULATING ARTIFACTS------------------
		
		
		private String handleViewExhibits(String input) {
			switch (input) {
			case "1":
				
				ArrayList<Exhibit> displayExhibits = new ArrayList<Exhibit>();
				String returnPrompt = "";
				
				for(Exhibit exhibit : exhibits)
				{
					displayExhibits.add(exhibit);
				}
				
				boolean sorted = false;
				
				while (!sorted)
				{
					boolean changed = false;
					
					for(int nextIndex = 1; nextIndex < displayExhibits.size(); nextIndex++)
					{
						int previousIndex = nextIndex-1;
						
						if(displayExhibits.get(previousIndex).getName().compareTo(displayExhibits.get(nextIndex).getName()) > 0)
						{
							changed = true;
							Exhibit temp = displayExhibits.get(nextIndex);
							displayExhibits.set(nextIndex, displayExhibits.get(previousIndex));
							displayExhibits.set(previousIndex, temp);
							
						}
					}
					if(!changed)
					{
						sorted = true;
					}
				
				}
				for(Exhibit exhibit : displayExhibits)
				{
					returnPrompt +="\nId: " + exhibit.getExhibitId() +"\n"
		 				+ "Name: " + exhibit.getName() + "\n"
						+ "Description: " + exhibit.getExhibitDescription() + "\n"
						+ "Total Engagement Time: "+ Integer.toString(getExhibitEngagementTime(exhibit.getExhibitId())) + " minutes" + "\n";
				}
				return returnPrompt;
			case "2":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				nextState = MenuState.HANDLE_EXHIBIT_INFO_NAME;
				stateType = MenuState.GET_EXHIBIT_INFO;
				return "";
			case "3":
				transitionTo(MenuState.HANDLE_EXHIBIT_SEARCH_CRITERIA);
				return "";
			case "4":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				stateType = MenuState.GET_ARTIFACT_SIGN;
				return "";
			case "5":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				stateType = MenuState.VIEW_EXHIBIT_ARTIFACTS;
				return "";
			case "6":
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}  
			
			System.out.println("Invalid Response - Try Again");
			return"";
		}
		
		
		

		private String handleManageExhibits(String input) {
			
			switch (input) {
			case "1":
				transitionTo(MenuState.CREATE_EXHIBIT_NAME);
				return "";
			case "2":
				transitionTo(MenuState.VIEW_EXHIBITS);
				
				return "";
			case "3":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				nextState = MenuState.DELETE_EXHIBIT;
				return "";
			case "4":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				nextState = MenuState.HANDLE_EXHIBIT_INFO_NAME;
				stateType = MenuState.HANDLE_EXHIBIT_NEW_VALUE;
				return "";
				
			case "5":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				stateType = MenuState.REMOVE_EXHIBIT_ARTIFACT;
				return "";
				
			case "6":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				stateType = MenuState.ADD_EXHIBIT_ARTIFACT;
				return "";
				
			case "7":
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				stateType = MenuState.MAIN_MENU;
				nextState = MenuState.REORDER_EXHIBIT_ARTIFACTS;
				return "";
				
			case "8":
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}  
			
			System.out.println("Invalid Response - Try Again");
			return"";
			
		}
		
		// ------------------ EXHIBIT MENUS --------------------------------------
		
		private String handleCreateExhibitName(String input) {
			if (input.trim().isEmpty()) {
				return "Name cannot be empty. Please enter a name.";
			}
			
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			exhibitStateData.put("name", input.trim());
			transitionTo(MenuState.CREATE_EXHIBIT_DESCRIPTION);
			return "";
		}
		
		
		private String handleCreateExhibitDescription(String input) {
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			
			if (input.trim().isEmpty()) {
				return "Description cannot be empty. Please enter a description.";
			}
			
			exhibitStateData.put("description", input.trim());
			
			transitionTo(MenuState.MANAGE_EXHIBITS);
			return createExhibit(exhibitStateData.get("name") , exhibitStateData.get("description"));

			
		}
		
		// -------------------- CREATING EXHIBITS -----------------------------
		
		private String handleGettingExhibitId(String input) {
			
			if(input.trim().equals("EXIT"))
			{
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}
			
			if (input.trim().isEmpty()) {
				return "Id cannot be empty. Please enter an Id.";
			}
			
			if(stateType == MenuState.UPDATE_ANNUAL_PLAN_HALL_EXHIBIT && input.trim().equals("REMOVE"))
			{
				this.remove = true;
				updateAnnualPlanInfo(planStateData.get("id"), planStateData.get("infoName"), planStateData.get("newValue") + input);
				transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
				this.remove = false;
				return "";
			}
			
			boolean found = false;
			for (Exhibit exhibit : exhibits)
			{
				if(exhibit.getExhibitId().equals(input))
				{
					found = true;
					exhibitStateData.put("id", input.trim());
				}
			}
			
			if(found == false)
			{
				System.out.println("\nInvalid Response - Try Again");
				System.out.println("Here is a list of valid exhibitIds:");
				if(exhibits.size() >= 2)
				{
					for (int index = 0; index < exhibits.size() - 1;index++)
					{
						System.out.print(exhibits.get(index).getExhibitId() + " ,");
					}
					System.out.print(exhibits.get(exhibits.size() - 1).getExhibitId());
					System.out.println("\n");
					return"";
				}
				else if (exhibits.size() != 0)
				{
					System.out.print(exhibits.get(0).getExhibitId());
					System.out.println("\n");
					return"";
				}
				else
				{
					System.out.println("\nThere are no Exhibits Made");
					return"";
				}
				
			}
			else
			{
				switch (stateType)
				{
					case REMOVE_EXHIBIT_ARTIFACT:
						transitionTo(MenuState.HANDLE_ARTIFACT_ID);
						nextState = MenuState.REMOVE_EXHIBIT_ARTIFACT;
						return "";
						
					case ADD_EXHIBIT_ARTIFACT:
						transitionTo(MenuState.HANDLE_ARTIFACT_ID);
						nextState = MenuState.ADD_EXHIBIT_ARTIFACT;
						return "";
						
					case VIEW_EXHIBIT_ARTIFACTS:
						ArrayList<String> returnArtifactIds = getExhibitArtifacts(exhibitStateData.get("id"));
						String artifactIds ="";
						if (returnArtifactIds.size() >= 2)
						{
							
							for (int index = 0; index < returnArtifactIds.size() -1;index++)
							{
								artifactIds += returnArtifactIds.get(index) + " , ";
							}
							artifactIds += returnArtifactIds.get(returnArtifactIds.size() - 1);
							transitionTo(MenuState.MANAGE_EXHIBITS);
							return artifactIds;
						}
						else if (returnArtifactIds.size() <= 0)
						{
							transitionTo(MenuState.MANAGE_EXHIBITS);
							return "There are no Artifacts in " + exhibitStateData.get("id");
						}
						else
						{
							transitionTo(MenuState.MANAGE_EXHIBITS);
							return returnArtifactIds.get(0);
						}
					
				
					case GET_ARTIFACT_SIGN:
						transitionTo(MenuState.HANDLE_ARTIFACT_ID);
						nextState = MenuState.GET_ARTIFACT_SIGN;
						return "";
						
					case HANDLE_EXHIBIT_NEW_VALUE:
						transitionTo(nextState);
						return "";
						
					
					case ADD_ANNUAL_PLAN_EXHIBIT:
						addExhibitToAnnualPlan(input, planStateData.get("id"),planStateData.get("hall"),Integer.parseInt(planStateData.get("month")));
						transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
						return "";
						

				}
				
				if(nextState == MenuState.REORDER_EXHIBIT_ARTIFACTS)
				{
					//System.err.println("REORDERING IS BEGINNING");
					if(getExhibitArtifacts(exhibitStateData.get("id")).size() <= 1)
					{
						transitionTo(MenuState.MANAGE_EXHIBITS);
						return "Not enough artifacts in " + exhibitStateData.get("id") ;
					}
					newArtifactIdList = new ArrayList<String>();
					reOrderArtifactIds = new ArrayList<String>();
					for(String art : getExhibitArtifacts(exhibitStateData.get("id")))
					{
						reOrderArtifactIds.add(art);
					}
				}
				
				transitionTo(nextState);
				return "";
			}
			
			
		}
		
		private String handleDeletingExhibits(String input)
		{
			switch (input)
			{
			case "1":
				deleteExhibit(exhibitStateData.get("id"));
				transitionTo(MenuState.MANAGE_EXHIBITS);
				return "";
			case "2":
				transitionTo(MenuState.MAIN_MENU);
				return"";
			}
			System.out.println("Invalid Response - Try Again");
			return"";

		}
		
		private String handleGettingInfoNameExhibit(String input)
		{
			String valid = "pass";
			switch (input) {
			case "1":
				exhibitStateData.put("infoName", "name");
				if(stateType != MenuState.GET_EXHIBIT_INFO) { stateType= MenuState.UPDATE_EXHIBIT_NAME;}
				break;

			case "2":
				exhibitStateData.put("infoName", "description");
				if(stateType != MenuState.GET_EXHIBIT_INFO) { stateType= MenuState.UPDATE_EXHIBIT_DESCRIPTION;}
				break;
				
			case "3":
				if(stateType == MenuState.GET_EXHIBIT_INFO) 
				{
					transitionTo(MenuState.MAIN_MENU);
					return "Total Engagement Time: " + Integer.toString(getExhibitEngagementTime(exhibitStateData.get("id")));
				}
				else
				{
					valid = "";
					break;
				}
				
				


			default:
				valid = "";
				break;
			}
			

			
			if(valid == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";

			}
			else
			{
				switch (stateType)
				{
				case GET_EXHIBIT_INFO:
					transitionTo(MenuState.MAIN_MENU);
					return getExhibitInfo(exhibitStateData.get("id") , exhibitStateData.get("infoName"));
				case UPDATE_EXHIBIT_NAME:
					transitionTo(MenuState.UPDATE_EXHIBIT_NAME);
					return "";
				case UPDATE_EXHIBIT_DESCRIPTION:
					transitionTo(MenuState.UPDATE_EXHIBIT_DESCRIPTION);
					return "";

					
					
					
				}
				System.err.println("StateType broke :( ");
				return "";
				
			}
			
			
			
			
		}
		
		private String handleUpdateNameExhibit(String input)
		{
			if (input.trim().isEmpty()) {
				return "Name cannot be empty. Please enter a name.";
			}
			
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			
			exhibitStateData.put("newValue", input.trim());
			updateExhibitInfo(exhibitStateData.get("id"), exhibitStateData.get("infoName"), exhibitStateData.get("newValue"));
			transitionTo(MenuState.MANAGE_EXHIBITS);
			return "";
		}
		
		private String handleUpdateDescriptionExhibit(String input)
		{
			
			if (input.trim().isEmpty()) {
				return "Description cannot be empty. Please enter a description.";
			}
			
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			
			exhibitStateData.put("newValue", input.trim());
			updateExhibitInfo(exhibitStateData.get("id"), exhibitStateData.get("infoName"), exhibitStateData.get("newValue"));
			transitionTo(MenuState.MANAGE_EXHIBITS);
			return "";
		}
		
		private String handleRemovingExhibitArtifact(String input)
		{
			switch (input)
			{
			case "1":
				removeArtifactFromExhibit(artifactStateData.get("id"), exhibitStateData.get("id"));
				transitionTo(MenuState.MANAGE_EXHIBITS);
				return "";
			case "2":
				transitionTo(MenuState.MAIN_MENU);
				return"";
			}
			System.out.println("Invalid Response - Try Again");
			return"";

		}
		
		private String handleAddingExhibitArtifact(String input)
		{
			if (input.trim().isEmpty()) {
				return "Sign cannot be empty. Please enter a sign.";
			}
			
			if(!checkSplittingChar(input.trim()))
			{
				return "Text cannot contain \"#\"";
			}
			
			exhibitStateData.put("sign", input);
			
			addArtifactToExhibit(artifactStateData.get("id"), exhibitStateData.get("id"), exhibitStateData.get("sign"));
			transitionTo(MenuState.MANAGE_EXHIBITS);
			return"";

		}
		
		private String handleSearchCriteriaExhibit(String input)
		{
			switch (input) {
			case "1":
				exhibitStateData.put("searchCriteria", "name=");
				transitionTo(MenuState.HANDLE_EXHIBIT_SEARCH_NAME);
				return "";
			case "2":
				exhibitStateData.put("searchCriteria", "name_contains=");
				transitionTo(MenuState.HANDLE_EXHIBIT_SEARCH_NAME_CONTAINS);
				return "";
			case "3":
				exhibitStateData.put("searchCriteria", "id=");
				transitionTo(MenuState.HANDLE_EXHIBIT_SEARCH_ID);
				return "";
			case "4":
				exhibitStateData.put("searchCriteria", "=");
				transitionTo(MenuState.HANDLE_EXHIBIT_SORTBY);
				return "";
			}
			System.out.println("Invalid Response - Try Again");
			return"";
		}
		
		private String handleSearchNameExhibit(String input)
		{
			if (input.trim().isEmpty()) {
				return "Text cannot be empty. Please enter text.";
			}
			
			exhibitStateData.put("searchCriteria", exhibitStateData.get("searchCriteria") + input.trim());
			transitionTo(MenuState.HANDLE_EXHIBIT_SORTBY);
			return "";
		}
		
		private String handleSearchNameContainsExhibit(String input)
		{
			if (input.trim().isEmpty()) {
				return "Text for name contains cannot be empty. Please enter non empty text.";
			}
			
			exhibitStateData.put("searchCriteria", exhibitStateData.get("searchCriteria") + input.trim());
			transitionTo(MenuState.HANDLE_EXHIBIT_SORTBY);
			return "";
		}

		private String handleSearchIdExhibit(String input)
		{
			boolean found = false;
			for (Exhibit exhibit : exhibits)
			{
				if(exhibit.getExhibitId().equals(input))
				{
					found = true;
					exhibitStateData.put("id", input.trim());
				}
			}
			if(!found)
			{
				System.out.println("\nInvalid Response - Try Again");
				System.out.println("Here is a list of valid exhibitIds:");
				if(exhibits.size() >= 2)
				{
					for (int index = 0; index < exhibits.size() - 1;index++)
					{
						System.out.print(exhibits.get(index).getExhibitId() + " ,");
					}
					System.out.print(exhibits.get(exhibits.size() - 1).getExhibitId());
					System.out.println("\n");
					return"";
				}
				else
				{
					System.out.print(exhibits.get(0).getExhibitId());
					System.out.println("\n");
					return"";
				}
				
			}
			else
			{
				exhibitStateData.put("searchCriteria", exhibitStateData.get("searchCriteria") + input.trim());
				transitionTo(MenuState.HANDLE_EXHIBIT_SORTBY);
				return "";
			}
			
		}
		
		private String handleSortByExhibit(String input)
		{
			String valid = "pass";
			String returnString = "| ";
			switch (input) {
			case "1":
				exhibitStateData.put("sortBy", "name");
				break;
			case "2":
				exhibitStateData.put("sortBy", "id");
				break;
			case "3":
				exhibitStateData.put("sortBy", "id");
				break;

			default:
					valid = "";
					break;
			}
			
			//System.err.println("Search Criteria: " + exhibitStateData.get("searchCriteria") + "\nSort By: " + exhibitStateData.get("sortBy"));
			if(valid == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			else
			{
				for(String exhibitId : findExhibits(exhibitStateData.get("searchCriteria"), exhibitStateData.get("sortBy")))
				{
					returnString += exhibitId + " | ";
				}
				
				transitionTo(MenuState.MANAGE_EXHIBITS);
				return returnString;
			}
			
		}
		
		private String handleReorderExhibitArtifacts(String input)
		{
			
			if(getExhibitArtifacts(exhibitStateData.get("id")).size() <= 1)
			{
				transitionTo(MenuState.MANAGE_EXHIBITS);
				return "There is not enough artifacts in " + exhibitStateData.get("id");
			}
			else
			{
				for(int index = 1; index <= reOrderArtifactIds.size() ; index++)
				{
					//System.err.println(index + " <---- index");
					if(Integer.toString(index).equals(input.trim()))
					{
						newArtifactIdList.add(reOrderArtifactIds.get(index - 1));
						//System.err.println(reOrderArtifactIds.get(index - 1) + " is getting added to the new order");
						reOrderArtifactIds.remove(index - 1);
						//System.err.println(reOrderArtifactIds.size() + " <---- size of reOrderArtifactIds");
						//System.err.println(newArtifactIdList.size() + " <---- size of new list");
						//System.err.println(getExhibitArtifacts(exhibitStateData.get("id")).size() + " <---- size of list in exhibit");
						break;
					}
				}
				
				if(reOrderArtifactIds.size() > 0)
				{
					return "";
				}
				else
				{
					reorderExhibitArtifacts(exhibitStateData.get("id") , newArtifactIdList);
					transitionTo(MenuState.MANAGE_EXHIBITS);
					return exhibitStateData.get("id") + " has been successfully reordered";
				}

				
			}
			
		}
		// -------------------------------- EXHIBITS ---------------------
		
		private String handleViewPlan(String input) {
			
			switch (input) {
			case "1":
				String returnPrompt ="";
				for(AnnualPlan plan : plans)
				{
					returnPrompt+=
							"Id: " + plan.getPlanId() +"\n"
						+ "Year: " + plan.getYear() + "\n"
						+ "Total Exhibits: " + plan.getTotalExhibits() + "\n";
					
				}
				return returnPrompt;
				
			// get annual plan
			case "2":
				transitionTo(MenuState.GET_ANNUAL_PLAN);
				return "";
			// get annual plan info
			case "3":
				transitionTo(MenuState.HANDLE_ANNUAL_PLAN_ID);
				nextState = MenuState.HANDLE_ANNUAL_PLAN_INFO_NAME;
				stateType = MenuState.GET_ANNUAL_PLAN_INFO;
				return "";
				
			// get annual plan exhibit
			case "4":
				transitionTo(MenuState.HANDLE_ANNUAL_PLAN_ID);
				nextState = MenuState.GET_ANNUAL_PLAN_HALL;
				stateType = MenuState.GET_ANNUAL_PLAN_EXHIBIT;
				return "";
				
			// return to main menu
			case "5":
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}  
			
			System.out.println("Invalid Response - Try Again");
			return"";
		}
		
		private String handleManagePlan(String input) {
			Scanner inputIn = new Scanner(System.in);
			
			switch (input) {
			case "1":
				transitionTo(MenuState.CREATE_ANNUAL_PLAN);
				return "";
			case "2":
				transitionTo(MenuState.VIEW_ANNUAL_PLANS);
				
				return "";
				
			// Delete Annual Plan
			case "3":
				transitionTo(MenuState.HANDLE_ANNUAL_PLAN_ID);
				nextState = MenuState.DELETE_ANNUAL_PLAN;
				stateType = MenuState.MAIN_MENU;
				return "";
				
			// Update Annual Plan
			case "4":
				transitionTo(MenuState.HANDLE_ANNUAL_PLAN_ID);
				nextState = MenuState.HANDLE_ANNUAL_PLAN_INFO_NAME;
				stateType = MenuState.HANDLE_ANNUAL_PLAN_NEW_VALUE;
				return "";
				
			// add exhibit to an annual plan
			case "5":
				transitionTo(MenuState.HANDLE_ANNUAL_PLAN_ID);
				nextState = MenuState.ADD_ANNUAL_PLAN_EXHIBIT_HALL;
				stateType = MenuState.MAIN_MENU;
				return "";
				
			case "6":
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}  
			
			System.out.println("Invalid Response - Try Again");
			return"";
			
		}
		
		// --------------- ANNUAL PLAN MENUS -------------------
		
		private String handleCreatePlan(String input) {
			try
			{
				if (input.trim().isEmpty()) {
					return "Year cannot be empty. Please enter a valid year.";
				}
				else if (Integer.parseInt(input) <= 2025 )
				{
					return "Year must be later than 2025";
				}
				
				if(usedYears.size() > 0)
				{
					for(int y : usedYears)
					{
						if(y == Integer.parseInt(input))
						{
							return "A plan with year: " + input +" has already been made";
						}
					}
				}
				
				usedYears.add(Integer.parseInt(input));

				planStateData.put("year", input.trim());
				
				transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
				return createAnnualPlan(Integer.parseInt(planStateData.get("year")));
			}
			catch(Exception ex)
			{
				System.err.println(ex);
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			
		}
		
		// --------------- ANNUAL PLAN CREATION ---------------
		private String handleGettingPlanId(String input) {
			
			if(input.trim().equals("EXIT"))
			{
				transitionTo(MenuState.MAIN_MENU);
				return "";
			}
			
			if (input.trim().isEmpty()) {
				return "Id cannot be empty. Please enter an Id.";
			}
			
			
			
			boolean found = false;
			for (AnnualPlan plan : plans)
			{
				if(plan.getPlanId().equals(input))
				{
					found = true;
					planStateData.put("id", input.trim());
				}
			}
			
			if(found == false)
			{
				System.out.println("\nInvalid Response - Try Again");
				System.out.println("Here is a list of valid planIds:");
				if(exhibits.size() >= 2)
				{
					for (int index = 0; index < plans.size() - 1;index++)
					{
						System.out.print(plans.get(index).getPlanId() + " ,");
					}
					System.out.print(plans.get(plans.size() - 1).getPlanId());
					System.out.println("\n");
					return"";
				}
				else if (plans.size() != 0)
				{
					System.out.print(plans.get(0).getPlanId());
					System.out.println("\n");
					return"";
				}
				else
				{
					System.out.println("\nThere are no Annual Plans Made");
					return"";
				}
			}
			else
			{
				
				transitionTo(nextState);
				return "";
			}
			
			
		}
		
		private String handleDeletingPlan(String input)
		{
			switch (input)
			{
			case "1":
				System.err.println(planStateData.get("id") + " is being deleted");
				deleteAnnualPlan(planStateData.get("id"));
				transitionTo(MenuState.MAIN_MENU);
				return "";
			case "2":
				transitionTo(MenuState.MAIN_MENU);
				return"";
			}
			System.out.println("Invalid Response - Try Again");
			return"";

		}
		
		private String handleGettingInfoNamePlan(String input)
		{
			String valid = "pass";
			
			switch (input) {
			case "1":
				
				if(stateType != MenuState.GET_ANNUAL_PLAN_INFO) 
				{ 
					//System.err.println("Choice 1 has been input");
					planStateData.put("infoName", "year");
					nextState = MenuState.UPDATE_ANNUAL_PLAN_YEAR;
				}
				else
				{
					transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
					return getAnnualPlanInfo(planStateData.get("id"), "year");
				}
				break;

			case "2":
				
				if(stateType != MenuState.GET_ANNUAL_PLAN_INFO) 
				{ 
					//System.err.println("Choice 2 has been input");
					planStateData.put("infoName", "hall");
					nextState = MenuState.UPDATE_ANNUAL_PLAN_HALL;
				}
				else
				{
					transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
					return getAnnualPlanInfo(planStateData.get("id"), "total_exhibits");
				}
				break;
				


			default:
				valid = "";
				break;
			}
			

			
			if(valid == "")
			{
				System.out.println("Invalid Response - Try Again");
				return"";

			}
			else
			{
				transitionTo(nextState);
				return "";
				
			}
			
			
			
			
		}
		
		
		private String handleUpdateYearPlan(String input)
		{
			try
			{
				if (input.trim().isEmpty()) {
					return "Year cannot be empty. Please enter a valid year.";
				}
				else if (Integer.parseInt(input) <= 2025)
				{
					return "Year must be later than 2025";
				}
				
				if(usedYears.size() > 0)
				{
					for(int y : usedYears)
					{
						if(y == Integer.parseInt(input))
						{
							return "A plan with year: " + input +" has already been made";
						}
					}
				}

				planStateData.put("newValue", input.trim());
				updateAnnualPlanInfo(planStateData.get("id"), planStateData.get("infoName"), planStateData.get("newValue"));
				transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
				return "";
			}
			catch(Exception ex)
			{
				System.out.println("Invalid Response - Try Again");
				return"";
			}
		}
		
		private String handleUpdateHallPlan(String input)
		{
			boolean valid = true;
			switch (input) {
			case "1":
				planStateData.put("newValue", "HALL_A");
				break;
				
			// get annual plan
			case "2":
				planStateData.put("newValue", "HALL_B");
				break;
			
			// get annual plan info
			case "3":
				planStateData.put("newValue", "HALL_C");
				break;
			
			default:
				valid = false;
				break;
				
			}  
			if(!valid)
			{
				System.out.println("Invalid Response - Try Again");
				return "";
			}
			else
			{
				transitionTo(MenuState.UPDATE_ANNUAL_PLAN_MONTH);
				return "";
			}
			
		}
		
		private String handleUpdateMonthPlan(String input)
		{
			try
			{
				if(Integer.parseInt(input) >=1 && Integer.parseInt(input) <=12)
				{
					planStateData.put("newValue", planStateData.get("newValue") + "-" + input + "-");
				}
				else
				{
					System.out.println("Invalid Response - Try Again");
					return "";
				}
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				stateType = MenuState.UPDATE_ANNUAL_PLAN_HALL_EXHIBIT;
				return "";
			}
			catch (Exception ex)
			{
				System.out.println("Invalid Response - Try Again");
				return "";
			}
		}
		
		private String handleCreateHallPlan(String input)
		{
			boolean valid = true;
			switch (input) {
			case "1":
				planStateData.put("hall", "HALL_A");
				break;
				
			// get annual plan
			case "2":
				planStateData.put("hall", "HALL_B");
				break;
			
			// get annual plan info
			case "3":
				planStateData.put("hall", "HALL_C");
				break;
			
			default:
				valid = false;
				break;
				
			}  
			if(!valid)
			{
				System.out.println("Invalid Response - Try Again");
				return "";
			}
			else
			{
				transitionTo(MenuState.ADD_ANNUAL_PLAN_EXHIBIT_MONTH);
				return "";
			}
			
		}
		
		private String handleCreateMonthPlan(String input)
		{
			try
			{
				if(Integer.parseInt(input) >=1 && Integer.parseInt(input) <=12)
				{
					planStateData.put("month", input);
				}
				else
				{
					System.out.println("Invalid Response - Try Again");
					return "";
				}
				transitionTo(MenuState.HANDLE_EXHIBIT_ID);
				stateType = MenuState.ADD_ANNUAL_PLAN_EXHIBIT;
				return "";
			}
			catch (Exception ex)
			{
				System.out.println("Invalid Response - Try Again");
				return "";
			}
		}
		
		private String handleGettingPlan(String input)
		{
			try
			{
				//----------------- CHECKS -------------------
				if (input.trim().isEmpty()) {
					return "Year cannot be empty. Please enter a valid year.";
				}
				else if (Integer.parseInt(input) <= 2025 )
				{
					return "Year must be later than 2025";
				}
				
				boolean valid = false;
				if(usedYears.size() > 0)
				{
					for(int y : usedYears)
					{
						if(y == Integer.parseInt(input))
						{
							valid = true;
						}
					}
				}
				
				// ----------------- CHECKS ---------------------
				
				if(!valid)
				{
					transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
					return "Year: " + input + " does not have an Annual Plan";
				}
				else
				{
					transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
					return getAnnualPlan(Integer.parseInt(input));
				}

			}
			catch(Exception ex)
			{
				System.err.println(ex);
				System.out.println("Invalid Response - Try Again");
				return"";
			}
			
		
		}
		
		private String handleGettingHallPlan(String input)
		{
			boolean valid = true;
			switch (input) {
			case "1":
				planStateData.put("hall", "HALL_A");
				break;
				
			// get annual plan
			case "2":
				planStateData.put("hall", "HALL_B");
				break;
			
			// get annual plan info
			case "3":
				planStateData.put("hall", "HALL_C");
				break;
			
			default:
				valid = false;
				break;
				
			}  
			if(!valid)
			{
				System.out.println("Invalid Response - Try Again");
				return "";
			}
			else
			{
				transitionTo(MenuState.GET_ANNUAL_PLAN_MONTH);
				return "";
			}
			
		}
		
		private String handleGettingMonthPlan(String input)
		{
			try
			{
				if(Integer.parseInt(input) >=1 && Integer.parseInt(input) <=12)
				{
					planStateData.put("month", input);
				}
				else
				{
					System.out.println("Invalid Response - Try Again");
					return "";
				}
				transitionTo(MenuState.MANAGE_ANNUAL_PLANS);
				return getAnnualPlanExhibit(planStateData.get("id"),planStateData.get("hall"),Integer.parseInt(planStateData.get("month")));
			}
			catch (Exception ex)
			{
				System.out.println("Invalid Response - Try Again");
				return "";
			}
		}
		
		
		
		
		
		// --------------- additional features ---------------------
		
		/*
		 * Stores the current data of QUBMuseum in the format
		 * line 1 - keeps record of what the next artifact id to be created is
		 * line 2 - keeps record of what the next exhibit id to be created is
		 * line 3 - keeps record of what the next plan id to be created is
		 * 
		 * After line 3 existing artifact data is saved,
		 * where each artifact is saved on separate lines.
		 * 
		 * The end of artifact data is when the next line is "END"
		 * This will be used to separate the exhibit and annual plans as well
		 * 
		 */
		public void saveData(String filePath) throws java.io.IOException
		{
			try 
			{
				FileWriter saveData = new FileWriter(filePath);
				saveData.write(this.nextArtifactId + "\n");
				saveData.write(this.nextExhibitId + "\n");
				saveData.write(this.nextPlanId + "\n");
				saveData.write(saveArtifacts());
				saveData.write(saveExhibits());
				saveData.write(saveAnnualPlans());
				saveData.close();
				System.out.println("Data has been saved");

		    } 
			catch (java.io.IOException e) 
			{
				System.out.println(e);
			}
		}
		
		/*
		 * Saves each artifact property : name ,description ,type ,engagement time and id
		 * Each property is separated using a "#" 
		 * Each artifact is saved on separate lines using \n
		 */
		
		public String saveArtifacts()
		{
			String saveArtifacts = "";
			
			for (Artifact art : this.artifacts)
			{
				saveArtifacts += art.getArtifactId() + "#" + art.getName() + "#" + art.getArtifactDescription() + "#" + art.getArtifactType() + "#" + art.getArtifactEngagementTime() + "\n";
			}
			
			saveArtifacts += "END\n";
			return saveArtifacts;
		}
		
		/*
		 * Saves each Exhibit property : name ,description ,artifacts saved in exhibit and id
		 * Each property is separated using a "#" 
		 * Each exhibit is saved on separate lines using \n
		 * 
		 * Artifacts which are saved in the exhibit are split using "!" 
		 * to split an artifact and their sign.
		 * 
		 * Then a ":" is used to split the artifact id linked with the sign
		 *
		 */
		
		public String saveExhibits()
		{
			String saveExhibits = "";
			String exhibitArtifacts = "";

			
			for (Exhibit ex : this.exhibits)
			{
				exhibitArtifacts = "";
				for(String artifactId : ex.getExhibitArtifacts())
				{
					exhibitArtifacts+= artifactId + ":" + ex.getExhibitArtifactSign(artifactId) + "!";
				}
				saveExhibits += ex.getExhibitId() + "#" + ex.getName() + "#" + ex.getExhibitDescription() + "#" + exhibitArtifacts +"\n";
			}
			saveExhibits += "END\n";
			return saveExhibits;
		}
		
		/*
		 * Saves each Annual Plan property : year ,,exhibits saved in plan and id
		 * Each property is separated using a "#" 
		 * Each exhibit is saved on separate lines using \n
		 * 
		 * Exhibits which are saved in the exhibit are split using "!" 
		 * to split an exhibit and the Hall and month it is in.
		 * 
		 * Then a ":" is used to split the exhibit id linked with the hall and month
		 *
		 */
		
		public String saveAnnualPlans()
		{
			String savePlans = "";
			String  planExhibits= "";

			
			for (AnnualPlan plan : this.plans)
			{
				planExhibits= "";
				for(String hall : plan.getexhibitMonthHalls().keySet())
				{
					planExhibits += hall + ":" + plan.getexhibitMonthHalls().get(hall) + "!";
				}
				savePlans += plan.getPlanId() + "#" + plan.getYear() + "#" + planExhibits + "\n";
			}
			savePlans += "END\n";
			return savePlans;
			
		}
		/*
		 * replaces all current data with the data in the save file
		 */
		public void loadData(String filePath) throws java.io.IOException
		{
			//System.err.println("LOAD DATA IS BEING RAN");
			File loadFile = new File(filePath);
			this.artifacts = new ArrayList<Artifact>();
			this.exhibits = new ArrayList<Exhibit>();
			this.plans = new ArrayList<AnnualPlan>();
			
			try(Scanner fileReader = new Scanner(loadFile))
			{
				int stage = 0;
				while(fileReader.hasNextLine())
				{
					String currentLine = fileReader.nextLine();
					
					switch (stage) 
					{
					case 0:
						//System.err.println("nextArtifactId: " + currentLine);
						this.nextArtifactId = Integer.parseInt(currentLine);
						stage++;
						break;
					case 1:
						//System.err.println("nextExhibitId: " + currentLine);
						this.nextExhibitId = Integer.parseInt(currentLine);
						
						stage++;
						break;
					case 2:
						//System.err.println("nextPlanId: " + currentLine);
						this.nextPlanId = Integer.parseInt(currentLine);
						
						stage++;
						break;
					case 3:
						if(currentLine.trim().equals("END"))
						{
							stage++;
							break;
						}
						else
						{
							//System.err.println("LOADING ARTIFACTS : " + currentLine);
							loadArtifactData(currentLine);
						}
						break;
					case 4:
						if(currentLine.trim().equals("END"))
						{
							stage++;
							break;
						}
						else
						{
							//System.err.println("LOADING EXHIBITS : " + currentLine);
							loadExhibitData(currentLine);
						}
						break;
					case 5:
						if(currentLine.trim().equals("END"))
						{
							stage++;
							break;
						}
						else
						{
							//System.err.println("LOADING PLANS : " + currentLine);
							loadPlanData(currentLine);
						}
						break;
					}
					
				}
			}
			catch (java.io.IOException e)
			{
				System.err.println(e);
			}
			
		}
		/*
		 * Loads the Artifacts 
		 */
		public void loadArtifactData(String fileInput)
		{
			try
			{
				//System.err.println("ArtifactId:" + fileInput.split("#")[0].trim());
				//System.err.println("TYPE:" + fileInput.split("#")[3].trim());
				//System.err.println("NAME:" + fileInput.split("#")[1].trim());
				//System.err.println("DESCRIPTION:" + fileInput.split("#")[2].trim());
				//System.err.println("TIME:" + fileInput.split("#")[2].trim());
				Artifact arti = new Artifact(fileInput.split("#")[0].trim(), fileInput.split("#")[3].trim(), fileInput.split("#")[1].trim(), fileInput.split("#")[2], Integer.parseInt(fileInput.split("#")[4]));
				artifacts.add(arti);
			}
			catch (Exception ex)
			{
				System.err.println("LOAD ART " + ex);
			}
			
			
		}
		/*
		 * Loads the Exhibits with their artifacts
		 */
		public void loadExhibitData(String fileInput)
		{
			try
			{
				//System.err.println("ExhibitId:" + fileInput.split("#")[0].trim());
				//System.err.println("NAME:" + fileInput.split("#")[1].trim());
				//System.err.println("DESCRIPTION:" + fileInput.split("#")[2].trim());
				//System.err.println("ARTIFACTS IN EXHIBIT: " + fileInput.split("#")[3].split("!")[0]);
				//System.err.println("(SEPERATED) ARTIFACT EXHIBIT: " + fileInput.split("#")[3].split("!")[0].split(":")[0]);
				//System.err.println("(SEPERATED) ARTIFACT SIGN: " + fileInput.split("#")[3].split("!")[0].split(":")[1]);
				
				Exhibit exhibit = new Exhibit(fileInput.split("#")[0].trim(),fileInput.split("#")[1].trim(),fileInput.split("#")[2].trim());
				
				if(fileInput.split("#").length >= 4)
				{
					for(String artId : fileInput.split("#")[3].split("!"))
					{
						exhibit.addArtifact(artId.split(":")[0].trim(), artId.split(":")[1].trim());
					}	
				}
				
				exhibits.add(exhibit);
			}
			catch (Exception ex)
			{
				System.err.println("LOAD exhibit " + ex);
			}
			
			
		}
		/*
		 * Loads the plans with their exhibits
		 */
		public void loadPlanData(String fileInput)
		{
			//System.err.println("PLANID:" + fileInput.split("#")[0]);
			//System.err.println("STORED EXHIBITS:" + fileInput.split("#")[2].split("!")[0]);
			//System.err.println("STORED HALL:" + fileInput.split("#")[2].split("!")[0].split(":")[0].split("-")[0].trim());
			//System.err.println("STORED MONTH:" + fileInput.split("#")[2].split("!")[0].split(":")[0].split("-")[1].trim());
			try
			{
				AnnualPlan plan = new AnnualPlan(fileInput.split("#")[0].trim(), Integer.parseInt(fileInput.split("#")[1].trim()));
				if(fileInput.split("#").length > 2)
				{
					for(String exhibitId : fileInput.split("#")[2].split("!"))
					{
						plan.addExhibitHalls(exhibitId.split(":")[1].trim(), exhibitId.split(":")[0].trim().split("-")[0].trim(), Integer.parseInt(exhibitId.split(":")[0].trim().split("-")[1].trim()));
					}
				}
				plans.add(plan);
			}
			catch (Exception ex)
			{
				System.err.println("LOAD PLANS " + ex);
			}
			
			
		}
		/*
		 * calls save data which saves the current data
		 */
		private String handleSaveData(String input)
		{
			try
			{
				switch (input) 
				{
				case "1":
					saveData("UlsterMuseumSave");
					transitionTo(MenuState.MAIN_MENU);
					return "";
					
				case "2":
					transitionTo(MenuState.MAIN_MENU);
					return "";
				}
				return "Invalid Response";
			}
			catch(java.io.IOException e)
			{
				return "Something went wrong";
			}
			
		}
		/*
		 * calls load data which loads all the data in a save file
		 * and replaces current data
		 */
		private String handleLoadData(String input)
		{
			try
			{
				switch (input) 
				{
				case "1":
					loadData("UlsterMuseumSave");
					transitionTo(MenuState.MAIN_MENU);
					return "";
					
				case "2":
					transitionTo(MenuState.MAIN_MENU);
					return "";
				}
				return "Invalid Response";
			}
			catch (java.io.IOException e)
			{
				return "Something went wrong";
			}
		}
	
}


package part01;
import java.util.ArrayList;
import java.util.HashMap;

public class Exhibit {
	private ArrayList<String> artifacts = new ArrayList<String>();
	private HashMap<String , String> artifactSigns = new HashMap<String,String>(); 
	String name , description, exhibitId;
	

	
	public Exhibit(String id, String name, String description) throws Exception
	{
		if(!setName(name) || !setDescription(description))
		{
			throw new Exception("Bad Data: " + "Name: " + name + "Description: " + description);
		}
		this.exhibitId =  id;

		
	}
	
	private boolean setName(String name)
	{
		if (name.trim().isEmpty()) {
			return false;
		}
		else
		{
			this.name = name;
			return true;
		}
		
	}
	
	private boolean setArtifactSign(String artifactId , String sign)
	{
		if (sign.trim().isEmpty()) {
			return false;
		}
		else
		{
			artifactSigns.put(artifactId, sign);
			return true;
		}
		
	}
	
	private boolean setDescription(String description)
	{
		if (description.trim().isEmpty()) {
			return false;
		}
		else
		{
			this.description = description;
			return true;
		}
		
	}

	public String getName()
	{
		return this.name;
	}
	
	public String getExhibitDescription()
	{
		return this.description;	
	}
	
	public String getExhibitId()
	{
		return this.exhibitId;
	}
	
	public ArrayList<String> getExhibitArtifacts()
	{
		return this.artifacts;
	}
	
	
	
	public String getExhibitInfo(String infoName) throws Exception
	{
		switch (infoName)
		{
		case "name":
			return "Name: " +  getName();

		case "description":
			return "Description: " + getExhibitDescription();
			
		default:
			throw new Exception("Invalid infoName");
			
		}
		
	}
	
	public void updateExhibitInfo(String infoName, String newValue) throws Exception
	{
		switch (infoName)
		{
		case "name":
			if(!setName(newValue))
			{
				throw new Exception("Name cannot be empty");
			}
			break;

		
		case "description":
			if(!setDescription(newValue))
			{
				throw new Exception("Description cannot be empty");
			}
			break;
			
			
		default:
			throw new Exception("Bad data: Invalid infoName");
			
			
		}
		
	}
	
	public String getExhibitArtifactSign(String artifactId)
	{
		for (String art : this.artifacts)
		{
			if(art.equals(artifactId))
			{
				return artifactSigns.get(artifactId);
			}
		}
		// System.err.println("Failed to get Artifact Sign in Exhibit: " + this.exhibitId);
		return null;
	}
	
	
	
	public boolean removeArtifact(String artifactId) throws Exception
	{
		if(artifacts.remove(artifactId))
		{
			artifactSigns.remove(artifactId);
			return true;
		}
		else
		{
			throw new Exception("Artifact with Id: " + artifactId + " could not be found");
		}
		
	}
	
	public String addArtifact(String artifactId , String sign) throws Exception
	{
		for(String art : artifacts)
		{
			if(art.equals(artifactId))
			{
				return artifactId + " is already in " + this.exhibitId;
			}
		}
		if(! setArtifactSign(artifactId , sign))
		{
			throw new Exception ("Artifact Sign cannot be empty");
		}
		else
		{
			artifacts.add(artifactId);
		}
		
		
		return "\n" + artifactId +" has been successfully added into " + this.exhibitId + "!\n";
	}
	
	
	
	public void reorderArtifacts(ArrayList<String> newArtifactList) throws Exception
	{
		int check = 0 ;
		for(String newArt : newArtifactList)
		{
			for (String art  : this.artifacts)
			{
				if(art.equals(newArt))
				{
					check++;
					break;
				}
			}
		}
		if(check == artifacts.size())
		{
			this.artifacts = newArtifactList;
		}
		
	}
	
	
	
}

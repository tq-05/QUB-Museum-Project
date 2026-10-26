package part01;



public class Artifact {
	
	
	
	
	private static String[] allowedTypes = {"Painting", "Sculpture", "Pottery", "Jewelry", "Textile", "Weapon", "Tool", "Fossil", "Coin", "Photograph", "Document", "Other"};
	String type, name, description, artifactId;
	int engagementMinutes;
	
	public Artifact(String id,String type, String name,String description,int engagementMinutes) throws Exception
	{

		
		
		if (!setEngagementMinutes(engagementMinutes) || !setName(name) || !setDescription(description)|| !setType(type))
		{
			throw new Exception("Bad Data: " + "Name: " + name + "Description: " + description + "Type: " + type +"Engagement: " + engagementMinutes + "mins");
		}
		this.artifactId = id;
		
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
	
	private boolean setType(String type)
	{
		for(String t : allowedTypes)
		{
			if(t.equals(type))
			{
				this.type = type;
				return true;
			}
		}
		return false;
		
	}
	
	
	
	
	private boolean setEngagementMinutes(int engagementMinutes) throws Exception
	{
		if(engagementMinutes > 0)
		{
			this.engagementMinutes = engagementMinutes;
			return true;
		}
		return false;


	}
	
	
	public int getArtifactEngagementTime()
	{
		return this.engagementMinutes;
	}
	
	public String getArtifactType()
	{
		return this.type;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getArtifactDescription()
	{
		return this.description;	
	}
	
	public String getArtifactId()
	{
		return this.artifactId;
	}
	
	public String getArtifactInfo(String infoName) throws Exception
	{
		switch (infoName)
		{
		case "name":
			return "Name: " + this.name;

		
		case "type":
			return "Type: " + this.type;

		
		case "description":
			return "Description: " + this.description;


		case "engagement_minutes":
			return "Engagement Time: " + this.engagementMinutes + "mins";
			
		default:
			throw new Exception("Invalid infoName");
			
			
		}
		
	}
	
	public void updateArtifactInfo(String infoName, String newValue) throws Exception
	{
		switch (infoName)
		{
		case "name":
			if(!setName(newValue))
			{
				throw new Exception("Name cannot be empty");
			}
			break;
			
		case "type":
			if(!setType(newValue))
			{
				throw new Exception("Invalid type");
			}
			break;

		case "description":
			if(!setDescription(newValue))
			{
				throw new Exception("Description cannot be empty");
			}
			break;

		case "engagement_minutes":
			if(!setEngagementMinutes(Integer.parseInt(newValue)))
			{
				throw new Exception("Bad data: EngagementMinutes: " + newValue);
			}
			
			break;
			
		default:
			throw new Exception("Invalid infoName");
			
			
		}
		
	}

	
	

}

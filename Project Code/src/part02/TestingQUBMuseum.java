package part02;

import api.TestingQUBMuseumAPI;
import part01.QUBMuseum;
import java.util.ArrayList;

public class TestingQUBMuseum implements TestingQUBMuseumAPI {
	
	//Tests can replace this instance with a new one if you want a clean start
    QUBMuseum api = new QUBMuseum();
    
    public static void main(String[] args) {	
        TestingQUBMuseum tester = new TestingQUBMuseum();
    	
        // UNIT TESTS
        
        // Run comprehensive test suite covering all API methods and edge cases, including error usage
        tester.runArtifactCRUDTests();
    	tester.runExhibitCRUDTests();
    	tester.runAnnualPlanCRUDTests();

        //Add additional tests unit tests to cover any advanced functionality
        
        // END-TO-END TESTS

    	// Simulate user inputs to test plausible end to end tests that a user might perform
    	// Use to validate both correct and incorrect actions being handled correctly by the system
    	// Validate both the menu responses and the internal state of the system using the api
        tester.runMenuTests();
        
        // FUZZING TESTS
        // Randomly simulate user inputs with and without errors to validate that the user interface
        // is working correctly
        tester.runFuzzingTests();
    }
    
	@Override
	public void runArtifactCRUDTests() 
    {
	    /*
	     * Results of the following: 
	     * 
	     * EXPECTED - is the result of a logic error happening but other parts of
	     * the system is used to prevent such errors, such as a incorrect input,
	     * but still passing
	     * 
	     * PASS - The result of an input and valid output/processing
	     * 
	     * FAILED - The result of correct input but failed output
	     * 
	     * 
	     */

	    String result;
	    
	    int numberOfErrors = 0;
	    int numberOfTests = 0;
	    System.out.println("\n---------- ARTIFACT CREATION ---------------\n");
	    // ------------------- testing artifact creation -------------------
	    // --------- valid inputs -----------
	    api = new QUBMuseum(); 
	    String expectedResult = "Artifact with artifactId: artifact_0 has been successfully created";
	    
	    try {
		    result = api.createArtifact("Painting", "Artifact", "Description", 10);
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Created");
		    } 
		    else 
		    {
		    	System.out.println("FAIL: Failed to create artifact");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // --------- valid inputs -----------
	    
	    
	    
	    // inputting an empty description
	    api = new QUBMuseum(); 
	    try {
	    	
		    result = api.createArtifact("Painting", "Artifact", "", 10);
		    
		    if (result == null) 
		    {
		      System.out.println("PASS: Artifact has not been created (Description Empty) ");
		    } 
		    else {
		      System.err.println("FAIL: Artifact was created");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //inputting an empty name
	    api = new QUBMuseum(); 
	    try {
		    result = api.createArtifact("Painting", "", "Description", 10);
		    if (result == null) 
		    {
		      System.out.println("PASS: Artifact has not been created (Name Empty) ");
		    } 
		    else {
		      System.err.println("FAIL: Artifact was created");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // inputting an empty type
	    api = new QUBMuseum(); 
	    try {
	    	
		    result = api.createArtifact("", "Artifact", "Description", 10);
		    if (result == null) 
		    {
		      System.out.println("PASS: Artifact has not been created (Type Empty) ");
		    } 
		    else {
		      System.err.println("FAIL: Artifact was created");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // inputting an invalid engagement time
	    api = new QUBMuseum(); 
	    try {
	    	
		    result = api.createArtifact("Painting", "Artifact", "Description", 0);
		    if (result == null) 
		    {
		    	System.out.println("PASS: Artifact has not been created (Engagement Time Invalid) ");
		    } 
		    else 
		    {
		    	numberOfErrors = numberOfErrors+1;
		    	System.err.println("FAIL: Artifact has been created (Engagement Time Invalid) ");
		    }
		    
		
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- ARTIFACT GET INFO ---------------\n");
	    // ---------------- testing get info name ---------
	    
	    // ----------- testing valid inputs -----------
	    // get name
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Artifact";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = api.getArtifactInfo("artifact_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Name was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifact name");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //get type
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Type: Painting";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = api.getArtifactInfo("artifact_0", "type");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Type was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifact type");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //get description
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Description: Description";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = api.getArtifactInfo("artifact_0", "description");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Description was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifact description");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    api = new QUBMuseum(); 
	    // get engagement_Time
	    try {
	    	expectedResult = "Engagement Time: 10mins";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = api.getArtifactInfo("artifact_0", "engagement_minutes");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Engagement Time was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: getArtifactInfo returned incorrectly (engagement_time)");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	 // ----------- testing valid inputs -----------
	    // invalid artifact Id
	    api = new QUBMuseum(); 
	    try {
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = api.getArtifactInfo("artifact_1", "name");
		    if (result == null) 
		    {
		      System.out.println("PASS: getArtifactInfo returned correctly (invalid artifactid)");
		    }
		    else {
		      System.err.println("FAIL: Found artifactId when not supposed to");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // invalid infoName
	    api = new QUBMuseum(); 
	    try {
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = api.getArtifactInfo("artifact_0", "");
		    if (result == null) 
		    {
		      System.out.println("PASS: getArtifactInfo returned correctly (invalid infoname)");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifact description");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    // ------------- testing get engagement time --------
	    // ----------- testing valid inputs -----------
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "10";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = Integer.toString(api.getArtifactEngagementTime("artifact_0"));
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact EngagementTime was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: getArtifactEngagementTime failed");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    // ----------- testing valid inputs -----------
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "-1";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    result = Integer.toString(api.getArtifactEngagementTime("artifact_1"));
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: getArtifactEngagementTime returned correctly (Invalid artifactid)");
		    } 
		    else {
		      System.err.println("FAIL: getArtifactEngagementTime returned incorrectly");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- ARTIFACT UPDATE INFO ---------------\n");
	 // ------------- testing update artifact --------
	 // ----------- testing valid inputs -----------
	    // Updating NAME
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: ChangedName";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.updateArtifactInfo("artifact_0", "name", "ChangedName");
		    result = api.getArtifactInfo("artifact_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact name has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update artifact name");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // updating DESCRIPTION
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Description: Desc";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.updateArtifactInfo("artifact_0", "description", "Desc");
		    result = api.getArtifactInfo("artifact_0", "description");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Description has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update artifact description");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // updating TYPE
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Type: Sculpture";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.updateArtifactInfo("artifact_0", "type", "Sculpture");
		    result = api.getArtifactInfo("artifact_0", "type");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact type has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update artifact type");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // updating engagement time
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Engagement Time: 5mins";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.updateArtifactInfo("artifact_0", "engagement_minutes", "5");
		    result = api.getArtifactInfo("artifact_0", "engagement_minutes");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Engagement Time has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update artifact engagement time");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	 // ----------- testing valid inputs -----------
	    
	 // test invalid artifactid
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Artifact";
	    	
		    api.createArtifact("Painting", "Artifact", "Description", 10); // artifact_0
		    

		    api.updateArtifactInfo("artifact_1", "name", "ChangedName");
		    
		    result = api.getArtifactInfo("artifact_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact name has not been changed (Invalid artifactId)");
		    } 
		    else 
		    {
		    	System.err.println(result);
		    	System.err.println("FAIL: updateArtifactInfo has processed incorrectly");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test updating invalid infoName
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Description: Description";
	    	
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    
		    api.updateArtifactInfo("artifact_0", "", "Desc");
		    
		    result = api.getArtifactInfo("artifact_0", "description");
		    
		    
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Description has not updated (invalid infoName)");
		    } 
		    else 
		    {
		    	System.err.println(result);
		    	System.err.println("FAIL: Failed to update artifact description");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test invalid type
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Type: Painting";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.updateArtifactInfo("artifact_0", "type", "notAtype");
		    result = api.getArtifactInfo("artifact_0", "type");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Type has not updated (invalid type)");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Artifact type has been updated");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // testing invalid engagement time
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Engagement Time: 10mins";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.updateArtifactInfo("artifact_0", "engagement_minutes", "0");
		    result = api.getArtifactInfo("artifact_0", "engagement_minutes");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Engagement Time has not been updated");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update artifact engagement time");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- DELETE ARTIFACT ---------------\n");
	  // ------------- testing Delete artifacts --------
	  // ----------- testing valid inputs -----------
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Artifact";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.deleteArtifact("artifact_0");
		    result = api.getArtifactInfo("artifact_0", "name");
		    if (result == null ) 
		    {
		      System.out.println("PASS: Artifact has been successfully delete");
		    } 
		    else {
		      System.err.println("FAIL: Failed to delete artifact");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // ----------- testing valid inputs -----------  
	    // inputting invalid artifactid
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Artifact";
		    api.createArtifact("Painting", "Artifact", "Description", 10);
		    api.deleteArtifact("artifact_1");
		    result = api.getArtifactInfo("artifact_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact has not been deleted");
		    } 
		    else {
		      System.err.println("FAIL: Artifact was deleted");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- ARTIFACT FIND ARTIFACTS ---------------\n");
	 // ------------- testing Find artifacts --------
	    ArrayList<String> expectedListResult = new ArrayList<String>();
	    ArrayList<String> listResult = new ArrayList<String>();
	    
	    System.out.println("\n---------- Testing Sort By ---------------\n");
	    //  ----------- default search criteria ---------------
	    // sort by name
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_3");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Painting", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Painting", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("type=Painting", "name");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (name) Artifacts have been sorted successfully");
		    } 
		    else {
		      System.err.println("FAIL: (name) Sorting Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // sort by id
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	
	    	api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Painting", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Painting", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("type=Painting", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (id) Artifacts have been sorted successfully");
		    } 
		    else {
		      System.err.println("FAIL: (id) Sorting Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // sort by type
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	
	    	api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Painting", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Painting", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("type=Painting", "type");
		    
		    

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (type) Artifacts have been sorted successfully");
		    } 
		    else {
		      System.err.println("FAIL: (type)  Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // sort by engagement time
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_0");
	    	
	    	api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Painting", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Painting", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("type=Painting", "engagement");
		    

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (engagement time) Artifacts have been sorted successfully ");
		    } 
		    else {
		      System.err.println("FAIL: (engagement time) Sorting Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    
	    System.out.println("\n---------- Testing Search Criteria ---------------\n");
	    // test name=
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	//expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "godly apple", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Apple", "noob apple", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("name=Apple", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: name) Artifacts have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: name) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test type=
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	//expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "godly apple", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Apple", "noob apple", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("type=Painting", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: type) Artifacts have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: type) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test name_contains=
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	//expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	//expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Ma not on", "godly apple", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man in the sky", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Man flying", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("name_contains=Man", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: name_contains) Artifacts have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: name_contains) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test name_contains=
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	//expectedListResult.add("artifact_2");
	    	//expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Ma not on", "godly apple", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man in the sky", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Man flying", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("id=artifact_0", "");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: id) Artifacts have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: id) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test ""
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "godly apple", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Apple", "noob apple", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: \"\" or null) Artifacts have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: \"\" or null) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) { }
	    
	  //invalid search criteria (name)
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	//expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	//expectedListResult.add("artifact_2");
	    	//expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    
		    
		    listResult = api.findArtifacts("name=ERROR", "name");
		    
		    for (String r : listResult)
		    {
		    	System.err.println(r);
		    }

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Invalid Search Criteria : name=) no artifacts retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria : name=) Artifacts retrieved");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //invalid search criteria (name_contains)
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	//expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	//expectedListResult.add("artifact_2");
	    	//expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    
		    
		    listResult = api.findArtifacts("name_contains=ERROR", "name");
		    
		    for (String r : listResult)
		    {
		    	System.err.println(r);
		    }

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Invalid Search Criteria : name=) no artifacts retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria : name=) Artifacts retrieved");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //invalid search criteria (type)
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	//expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	//expectedListResult.add("artifact_2");
	    	//expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    
		    
		    listResult = api.findArtifacts("type=ERROR", "name");
		    
		    for (String r : listResult)
		    {
		    	System.err.println(r);
		    }

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Invalid Search Criteria : name=) no artifacts retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria : name=) Artifacts retrieved");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //invalid search criteria (name)
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	//expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	//expectedListResult.add("artifact_2");
	    	//expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    
		    
		    listResult = api.findArtifacts("id=ERROR", "name");
		    
		    for (String r : listResult)
		    {
		    	System.err.println(r);
		    }

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Invalid Search Criteria : name=) no artifacts retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria : name=) Artifacts retrieved");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- Testing Invalid Inputs ---------------\n");
	    
	    
	    //invalid Sort By
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	//expectedListResult.add("artifact_1");
	    	//expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("type=Painting", "ERROR");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("EXPECTED: (Invalid Sort By) Artifacts have been sorted");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Sort By) Sorting Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //invalid search criteria
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("ERROR", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("EXPECTED: (Invalid Search Criteria) Artifacts have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //invalid search criteria & invalid Sort By
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_3");
	    	
		    api.createArtifact("Painting", "Apple", "Description", 10); // artifact_0
		    api.createArtifact("Sculpture", "Man", "Description", 8); // artifact_1
		    api.createArtifact("Photograph", "Sunrise", "Description", 2); // artifact_2
		    api.createArtifact("Painting", "Banna", "Description", 5); // artifact_3
		    
		    listResult = api.findArtifacts("ERROR", "ERROR");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("EXPECTED: (Invalid Search Criteria & Sort By) Artifacts have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria & Sort By) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
    }

	@Override
    public void runExhibitCRUDTests() 
    {
		
		/*
	     * Results of the following: 
	     * 
	     * EXPECTED - is the result of a logic error happening but other parts of
	     * the system is used to prevent such errors, such as a incorrect input,
	     * but still passing
	     * 
	     * PASS - The result of an input and valid output/processing
	     * 
	     * FAILED - The result of correct input but failed output
	     * 
	     * 
	     */
		
		String result;
	    
	    int numberOfErrors = 0;
	    int numberOfTests = 0;
	    System.out.println("\n---------- EXHIBIT CREATION ---------------\n");
	    // ------------------- testing exhibit creation -------------------
	    // --------- valid inputs -----------
	    api = new QUBMuseum(); 
	    String expectedResult = "Exhibit with exhibitId: exhibit_0 has been successfully created";
	    
	    try {
		    result = api.createExhibit("Exhibit", "Description");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit Created");
		    } 
		    else 
		    {
		    	System.out.println("FAIL: Failed to create Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // --------- valid inputs -----------
	    
	    
	    // inputting an empty description
	    api = new QUBMuseum(); 
	    try {
	    	
		    result = api.createExhibit("Exhibit","");
		    
		    if (result == null) 
		    {
		      System.out.println("PASS: Exhibit was not created (Description Empty) ");
		    } 
		    else {
		      System.err.println("FAIL: Failed to create Exhibit");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //inputting an empty name
	    api = new QUBMuseum(); 
	    try {
		    result = api.createExhibit("", "Description");
		    if (result == null) 
		    {
		      System.out.println("PASS: Exhibit was not created (Name Empty) ");
		    } 
		    else {
		      System.err.println("FAIL: Failed to create Exhibit");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- EXHIBIT GET INFO ---------------\n");
	    // ---------------- testing get info name ---------
	    
	    // ----------- testing valid inputs -----------
	    // get name
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Exhibit";
		    api.createExhibit("Exhibit", "Description");
		    result = api.getExhibitInfo("exhibit_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit Name was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get Exhibit name");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //get description
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Description: Description";
		    api.createExhibit("Exhibit", "Description");
		    result = api.getExhibitInfo("exhibit_0", "description");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit Description was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get Exhibit description");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    api = new QUBMuseum(); 
	    
	 // ----------- testing valid inputs -----------
	    // invalid Exhibit Id
	    api = new QUBMuseum(); 
	    try {
		    api.createExhibit("Exhibit", "Description");
		    result = api.getExhibitInfo("exhibit_1", "name");
		    if (result == null) 
		    {
		      System.out.println("PASS: getExhibitInfo returned correctly (invalid Exhibitid)");
		    }
		    else {
		      System.err.println("FAIL: Found ExhibitId when not supposed to");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // invalid infoName
	    api = new QUBMuseum(); 
	    try {
		    api.createExhibit("Exhibit", "Description");
		    result = api.getExhibitInfo("exhibit_0", "");
		    if (result == null) 
		    {
		      System.out.println("PASS: getExhibitInfo returned correctly (invalid infoname)");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get Exhibit description");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    // ------------- testing get engagement time --------
	    // ----------- testing valid inputs -----------
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "0";
		    api.createExhibit("Exhibit", "Description");
		    result = Integer.toString(api.getExhibitEngagementTime("exhibit_0"));
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit EngagementTime was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: getExhibitEngagementTime failed");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    // ----------- testing valid inputs -----------
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "-1";
		    api.createExhibit("Exhibit", "Description");
		    result = Integer.toString(api.getExhibitEngagementTime("exhibit_1"));
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: getExhibitEngagementTime returned correctly (Invalid Exhibitid)");
		    } 
		    else {
		      System.err.println("FAIL: getExhibitEngagementTime returned incorrectly");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- EXHIBIT UPDATE INFO ---------------\n");
	 // ------------- testing update Exhibit --------
	 // ----------- testing valid inputs -----------
	    // Updating NAME
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: ChangedName";
		    api.createExhibit("Exhibit", "Description");
		    api.updateExhibitInfo("exhibit_0", "name", "ChangedName");
		    result = api.getExhibitInfo("exhibit_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit name has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update Exhibit name");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // updating DESCRIPTION
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Description: Desc";
		    api.createExhibit("Exhibit", "Description");
		    api.updateExhibitInfo("exhibit_0", "description", "Desc");
		    result = api.getExhibitInfo("exhibit_0", "description");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit Description has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update Exhibit description");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    
	 // ----------- testing valid inputs -----------
	    
	 // test invalid Exhibitid
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Exhibit";
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    

		    api.updateExhibitInfo("exhibit_1", "name", "ChangedName");
		    
		    result = api.getExhibitInfo("exhibit_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit name has not been changed (Invalid ExhibitId)");
		    } 
		    else 
		    {
		    	System.err.println(result);
		    	System.err.println("FAIL: updateExhibitInfo has processed incorrectly");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test updating invalid infoName
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Description: Description";
	    	
		    api.createExhibit("Exhibit", "Description");
		    
		    api.updateExhibitInfo("exhibit_0", "", "Desc");
		    
		    result = api.getExhibitInfo("exhibit_0", "description");
		    
		    
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit Description has not updated (invalid infoName)");
		    } 
		    else 
		    {
		    	System.err.println(result);
		    	System.err.println("FAIL: Failed to update Exhibit description");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- DELETE EXHIBIT ---------------\n");
	  // ------------- testing Delete Exhibits --------
	  // ----------- testing valid inputs -----------
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Exhibit";
		    api.createExhibit("Exhibit", "Description");
		    api.deleteExhibit("exhibit_0");
		    result = api.getExhibitInfo("exhibit_0", "name");
		    if (result == null ) 
		    {
		      System.out.println("PASS: Exhibit has been successfully delete");
		    } 
		    else {
		      System.err.println("FAIL: Failed to delete Exhibit");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // ----------- testing valid inputs -----------  
	    // inputting invalid Exhibitid
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Name: Exhibit";
		    api.createExhibit("Exhibit", "Description");
		    api.deleteExhibit("exhibit_1");
		    result = api.getExhibitInfo("exhibit_0", "name");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit has not been deleted");
		    } 
		    else {
		      System.err.println("FAIL: Exhibit was deleted");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- EXHIBIT FIND Exhibits ---------------\n");
	 // ------------- testing Find Exhibits --------
	    ArrayList<String> expectedListResult = new ArrayList<String>();
	    ArrayList<String> listResult = new ArrayList<String>();
	    
	    System.out.println("\n---------- Testing Sort By ---------------\n");
	    //  ----------- default search criteria ---------------
	    // sort by name
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	expectedListResult.add("exhibit_3");
	    	expectedListResult.add("exhibit_1");
	    	expectedListResult.add("exhibit_2");
	    	
		    api.createExhibit("Air Force test", "Description"); // exhibit_0
		    api.createExhibit("Statues test", "Description"); // exhibit_1
		    api.createExhibit("Zoo test", "Description"); // exhibit_2
		    api.createExhibit("Banana Garden test", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("name_contains=test", "name");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (name) Exhibits have been sorted successfully");
		    } 
		    else {
		      System.err.println("FAIL: (name) Sorting Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // sort by id
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	expectedListResult.add("exhibit_1");
	    	expectedListResult.add("exhibit_2");
	    	expectedListResult.add("exhibit_3");
	    	
	    	api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("name=Exhibit", "id");
		    

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (id) Exhibits have been sorted successfully");
		    } 
		    else {
		      System.err.println("FAIL: (id) Sorting Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) { System.err.println(e);}
	    
	    
	    System.out.println("\n---------- Testing Search Criteria ---------------\n");
	    // test name=
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	//expectedListResult.add("exhibit_1");
	    	//expectedListResult.add("exhibit_2");
	    	//expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Jungle", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("name=Jungle", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: name) Exhibits have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: name) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test name_contains=
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	expectedListResult.add("exhibit_1");
	    	//expectedListResult.add("exhibit_2");
	    	//expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit in the sky", "Description"); // exhibit_0
		    api.createExhibit("sun in the sky", "Description"); // exhibit_1
		    api.createExhibit("Jungle", "Description"); // exhibit_2
		    api.createExhibit("Garden", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("name_contains=sky", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: name_contains) Exhibits have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: name_contains) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test id=
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	//expectedListResult.add("exhibit_1");
	    	//expectedListResult.add("exhibit_2");
	    	//expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("id=exhibit_0", "");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: id) Exhibits have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: id) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // test ""
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	expectedListResult.add("exhibit_1");
	    	expectedListResult.add("exhibit_2");
	    	expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Search Criteria: \"\" or null) Exhibits have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Search Criteria: \"\" or null) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) { }
	    
	 
	    
	  //invalid search criteria (name)
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	//expectedListResult.add("exhibit_0");
	    	//expectedListResult.add("exhibit_1");
	    	//expectedListResult.add("exhibit_2");
	    	//expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    
		    
		    listResult = api.findExhibits("name=ERROR", "name");
		    
		    for (String r : listResult)
		    {
		    	System.err.println(r);
		    }

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Invalid Search Criteria : name=) no Exhibits retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria : name=) Exhibits retrieved");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //invalid search criteria (name_contains)
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	//expectedListResult.add("exhibit_0");
	    	//expectedListResult.add("exhibit_1");
	    	//expectedListResult.add("exhibit_2");
	    	//expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    
		    
		    listResult = api.findExhibits("name_contains=ERROR", "name");
		    
		    for (String r : listResult)
		    {
		    	System.err.println(r);
		    }

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Invalid Search Criteria : name_contains=) no Exhibits retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria : name_contains=) Exhibits retrieved");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //invalid Sort By
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	//expectedListResult.add("exhibit_1");
	    	//expectedListResult.add("exhibit_2");
	    	expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Not an exhibit", "Description"); // exhibit_1
		    api.createExhibit("ex hib it", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("name=Exhibit", "ERROR");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("EXPECTED: (Invalid Sort By) Exhibits have been sorted");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Sort By) Sorting Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //invalid search criteria
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	expectedListResult.add("exhibit_1");
	    	expectedListResult.add("exhibit_2");
	    	expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("ERROR", "id");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("EXPECTED: (Invalid Search Criteria) Exhibits have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //invalid search criteria & invalid Sort By
	    api = new QUBMuseum(); 
	    try {
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("exhibit_0");
	    	expectedListResult.add("exhibit_1");
	    	expectedListResult.add("exhibit_2");
	    	expectedListResult.add("exhibit_3");
	    	
		    api.createExhibit("Exhibit", "Description"); // exhibit_0
		    api.createExhibit("Exhibit", "Description"); // exhibit_1
		    api.createExhibit("Exhibit", "Description"); // exhibit_2
		    api.createExhibit("Exhibit", "Description"); // exhibit_3
		    
		    listResult = api.findExhibits("ERROR", "ERROR");

		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("EXPECTED: (Invalid Search Criteria & Sort By) Exhibits have been retrieved");
		    } 
		    else {
		      System.err.println("FAIL: (Invalid Search Criteria & Sort By) Filter Processing has broke");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- EXHIBIT ADD ARTIFACTS/GET EXHIBIT ARTIFACTS ---------------\n");
	  // ------------- testing Add Artifact to Exhibits --------
	    // normal inputs
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign");
		    

		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: Artifact added to Exhibit");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to add Artifact to Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // invalid artifact id
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = new ArrayList<String>();

	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    //api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    

		    

		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Invalid artifactid)Artifact was not added to Exhibit");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Added Artifact to Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //Invalid exhibit id
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = null;
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    

		    

		    
		    listResult = api.getExhibitArtifacts("exhibit_10");
		    

		    
		    if (listResult == null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("EXPECTED: (Invalid exhibit id) getExhibitArtifacts processed correctly");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: getExhibitArtifacts added an Artifact to an Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // attempt to add same artifact in Exhibit
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    

		    

		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: (Same Artifact Input)Artifact was not added to Exhibit");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Added Artifact to Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // Invalid Sign input
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "");
		    

		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (!listResult.equals(expectedListResult)) 
		    {
		      System.out.println("PASS: (Invalid Sign)Artifact was not added to Exhibit");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Artifact was added to Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    System.out.println("\n---------- EXHIBIT REMOVE ARTIFACTS ---------------\n");
	 // normal inputs
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_2");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign");
		    
		    api.removeArtifactFromExhibit("artifact_1", "exhibit_0");
		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: Correct Artifact has been removed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to remove Artifact from Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // Invalid artifact id
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign");
		    
		    api.removeArtifactFromExhibit("artifact_10", "exhibit_0");
		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null) 
		    {
		      System.out.println("PASS: (Invalid artifact id)Artifact was not removed from Exhibit");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Artifact was removed from Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // Invalid exhibit id
	    api = new QUBMuseum();
	    try {
	    	


	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign");
		    
		    api.removeArtifactFromExhibit("artifact_1", "exhibit_0");
		    
		    listResult = api.getExhibitArtifacts("exhibit_10");
		    

		    
		    if (listResult == null ) 
		    {
		      System.out.println("PASS:(Invalid exhibit id) Exhibit was not found");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Exhibit was found");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- EXHIBIT GET ARTIFACT SIGN ---------------\n");
	 // normal inputs
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedResult = "Sign1";
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign0");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign1");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign2");
		    

		    
		    result = api.getExhibitArtifactSign("exhibit_0", "artifact_1");
		    

		    
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact Sign has been retrieved");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to retrieve Artifact Sign from Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // invalid artifact id
	    api = new QUBMuseum();
	    try {
	    	
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign0");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign1");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign2");
		    

		    
		    result = api.getExhibitArtifactSign("exhibit_0", "artifact_10");
		    

		    
		    if (result == null ) 
		    {
		      System.out.println("PASS: (Invalid artifact id)Artifact Sign was not retrieved");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Retrieved Artifact Sign from Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // invalid exhibit id
	    api = new QUBMuseum();
	    try {
	    	
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign0");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign1");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign2");
		    

		    
		    result = api.getExhibitArtifactSign("exhibit_1", "artifact_1");
		    

		    
		    if (result == null ) 
		    {
		      System.out.println("PASS: (Invalid exhibit id))Artifact Sign was not retrieved");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Retrieved Artifact Sign from Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    System.out.println("\n---------- EXHIBIT REORDER ARTIFACTS ---------------\n");
	  // ------------- testing reorder Artifacts in Exhibits --------
	  // normal inputs
	    ArrayList<String> inputList = new ArrayList<String>();
	    api = new QUBMuseum();
	    try {
	    	
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_2");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_0");
	    	
	    	inputList.add("artifact_2");
	    	inputList.add("artifact_1");
	    	inputList.add("artifact_0");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign");
		    

		    api.reorderExhibitArtifacts("exhibit_0", inputList);
		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: Exhibit artifacts have been Reordered");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to re order Artifacts in Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // invalid exhibit ids
	    api = new QUBMuseum();
	    try {
	    	inputList = new ArrayList<String>();
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	
	    	inputList.add("artifact_2");
	    	inputList.add("artifact_1");
	    	inputList.add("artifact_0");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign");
		    

		    api.reorderExhibitArtifacts("exhibit_10", inputList);
		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS: Exhibit artifacts have not been Reordered");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Artifacts have been re ordered in Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // invalid artifact ids
	    try {
	    	inputList = new ArrayList<String>();
	    	expectedListResult = new ArrayList<String>();
	    	expectedListResult.add("artifact_0");
	    	expectedListResult.add("artifact_1");
	    	expectedListResult.add("artifact_2");
	    	
	    	inputList.add("artifact_29");
	    	inputList.add("artifact_02");
	    	inputList.add("artifact_13");
	    	
	    	api.createExhibit("Exhibit", "Description");
	    	
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_2", "exhibit_0", "Sign");
		    

		    api.reorderExhibitArtifacts("exhibit_0", inputList);
		    
		    listResult = api.getExhibitArtifacts("exhibit_0");
		    

		    
		    if (listResult != null && expectedListResult.equals(listResult)) 
		    {
		      System.out.println("PASS:(invalid artifact id) Exhibit artifacts have not been Reordered");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed Artifacts have been Reordered in Exhibit");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
    }
    
	@Override
    public void runAnnualPlanCRUDTests() 
    {
		/*
	     * Results of the following: 
	     * 
	     * EXPECTED - is the result of a logic error happening but other parts of
	     * the system is used to prevent such errors, such as a incorrect input,
	     * but still passing
	     * 
	     * PASS - The result of an input and valid output/processing
	     * 
	     * FAILED - The result of correct input but failed output
	     * 
	     * 
	     */
		
		String result;
	    
	    int numberOfErrors = 0;
	    int numberOfTests = 0;
	    System.out.println("\n---------- Plan CREATION ---------------\n");
	    // ------------------- testing Plan creation -------------------
	    // --------- valid inputs -----------
	    api = new QUBMuseum(); 
	    String expectedResult = "Annual plan with Id: plan_0 has been successfully created";
	    
	    try {
		    result = api.createAnnualPlan(2026);
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan Created");
		    } 
		    else 
		    {
		    	System.out.println("FAIL: Failed to create Plan");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // --------- invalid inputs -----------
	    
	    // inputting an invalid year
	    api = new QUBMuseum(); 
	    try {
	    	
		    result = api.createAnnualPlan(0);
		    
		    if (result == null) 
		    {
		      System.out.println("PASS: Plan was not created (Year Invalid) ");
		    } 
		    else {
		      System.err.println("FAIL: Created Plan");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    System.out.println("\n---------- DELETE Plan ---------------\n");
	  // ------------- testing Delete Plans --------
	  // ----------- testing valid inputs -----------
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Year: 2026";
		    api.createAnnualPlan(2026);
		    api.deleteAnnualPlan("plan_0");
		    result = api.getAnnualPlanInfo("plan_0", "year");
		    if (result == null ) 
		    {
		      System.out.println("PASS: Plan has been successfully delete");
		    } 
		    else {
		      System.err.println("FAIL: Failed to delete Plan");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // ----------- testing valid inputs -----------  
	    // inputting invalid Planid
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Year: 2026";
		    api.createAnnualPlan(2026);
		    api.deleteAnnualPlan("Plan_1");
		    result = api.getAnnualPlanInfo("plan_0", "year");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan has not been deleted");
		    } 
		    else {
		      System.err.println("FAIL: Plan was deleted");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    System.out.println("\n---------- Plan GET PLAN ---------------\n");
	    // ---------------- testing get annualPlan ---------
	    //normal inputs
	    try {
	    	expectedResult = "plan_0";
		    api.createAnnualPlan(2026);
		    result = api.getAnnualPlan(2026);
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan was found");
		    } 
		    else 
		    {
		    	System.out.println("FAIL: Plan was not found");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //invalid input
	    try {
		    api.createAnnualPlan(2026);
		    result = api.getAnnualPlan(2030);
		    if (result == null) 
		    {
		      System.out.println("PASS: Plan was not found (invalid year)");
		    } 
		    else 
		    {
		    	System.out.println("FAIL: Plan was found");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    System.out.println("\n---------- Plan GET INFO ---------------\n");
	    // ---------------- testing get info ---------
	    
	    // ----------- testing valid inputs -----------
	    // get year
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Year: 2026";
		    api.createAnnualPlan(2026);
		    result = api.getAnnualPlanInfo("plan_0", "year");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan Name was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get Plan name");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //get total exhibits
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Total Exhibits: 1";
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 0);
		    
		    result = api.getAnnualPlanInfo("plan_0", "total_exhibits");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan Description was returned correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get Plan description");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    api = new QUBMuseum(); 
	    
	 // ----------- testing valid inputs -----------
	    // invalid Plan Id
	    api = new QUBMuseum(); 
	    try {
		    api.createAnnualPlan(2026);
		    result = api.getAnnualPlanInfo("plan_1", "total_exhibits");
		    if (result == null) 
		    {
		      System.out.println("PASS: getAnnualPlanInfo returned correctly (invalid Planid)");
		    }
		    else {
		      System.err.println("FAIL: Found PlanId when not supposed to");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // invalid infoName
	    api = new QUBMuseum(); 
	    try {
		    api.createAnnualPlan(2026);
		    result = api.getAnnualPlanInfo("plan_0", "");
		    if (result == null) 
		    {
		      System.out.println("PASS: getAnnualPlanInfo returned correctly (invalid infoname)");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get Plan description");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    	    
	    System.out.println("\n---------- Plan UPDATE INFO ---------------\n");
	 // ------------- testing update Plan --------
	 // ----------- testing valid inputs -----------
	    // Updating year
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Year: 2027";
		    api.createAnnualPlan(2026);
		    api.updateAnnualPlanInfo("plan_0", "year", "2027");
		    result = api.getAnnualPlanInfo("plan_0", "year");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan year has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update Plan year");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // updating Hall (replacing)
	    api = new QUBMuseum();  //TESTS getAnnualPlanExhibit as well
	    try {
	    	expectedResult = "exhibit_1 is in HALL_A-1";
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 1);
		    api.updateAnnualPlanInfo("plan_0", "hall", "HALL_A-1-exhibit_1");
		    result = api.getAnnualPlanExhibit("plan_0", "HALL_A",1);
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan Hall has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update Plan Hall");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // updating Hall (removing)
	    api = new QUBMuseum();  //TESTS getAnnualPlanExhibit as well
	    try {
	    	expectedResult = "Exhibit not found in plan_0";
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 1);
		    //api.remove=true;
		    api.updateAnnualPlanInfo("plan_0", "hall", "HALL_A-1-REMOVE");
		    //api.remove=false; // the static version of remove was created to allow this test to happen.
		    result = api.getAnnualPlanExhibit("plan_0", "HALL_A",1);
		    System.err.println(result);
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan Hall has been successfully changed");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to update Plan Hall");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	    //------------------ invalid inputs --------------
	 // test updating invalid infoName
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Year: 2026";
	    	
		    api.createAnnualPlan(2026);
		    
		    api.updateAnnualPlanInfo("plan_0", "", "2027");
		    
		    result = api.getAnnualPlanInfo("plan_0", "year");
		    
		    
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan year has not updated (invalid infoName)");
		    } 
		    else 
		    {
		    	System.err.println(result);
		    	System.err.println("FAIL: Updated Plan year");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //test invalid plan id
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Year: 2026";
	    	
		    api.createAnnualPlan(2026);
		    
		    api.updateAnnualPlanInfo("plan_1", "year", "2027");
		    
		    result = api.getAnnualPlanInfo("plan_0", "year");
		    
		    
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Plan year has not updated (invalid planId)");
		    } 
		    else 
		    {
		    	System.err.println(result);
		    	System.err.println("FAIL: Updated Plan year");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    System.out.println("\n---------- Plan ADD EXHIBIT ---------------\n");
	    //------------------ test adding exhibits
	 // normal input
	    api = new QUBMuseum();  
	    try {
	    	expectedResult = "exhibit_0 is in HALL_A-1";
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 1);
		    result = api.getAnnualPlanExhibit("plan_0", "HALL_A",1);
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit has been successfully added to plan");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Failed to add exhibit to plan");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //invalid inputs
	    //test lower boundary
	    api = new QUBMuseum();  
	    try {
	    	expectedResult = "Exhibit not found in plan_0";
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 0);
		    result = api.getAnnualPlanExhibit("plan_0", "HALL_A",1);  
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit has not been added to plan (Lower Boundary Month Test)");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Exhibit has been added to plan");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	  //test upper boundary
	    api = new QUBMuseum();  
	    try {
	    	expectedResult = "Exhibit not found in plan_0";
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 13);
		    result = api.getAnnualPlanExhibit("plan_0", "HALL_A",1);  
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit has not been added to plan (Upper Boundary Month Test)");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Exhibit has been added to plan");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  //test invalid Hall
	    api = new QUBMuseum();  
	    try {
	    	expectedResult = "Exhibit not found in plan_0";
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 0);
		    result = api.getAnnualPlanExhibit("plan_0", "HALL_D",1);  
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Exhibit has not been added to plan (Invalid Hall)");
		    } 
		    else 
		    {
		    	System.err.println("FAIL: Exhibit has been added to plan");
		    	numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}


    }

    
	@Override
    public void runMenuTests() 
    {
		String prompt;
	    String result, expectedResult;
	    ArrayList<String> expectedListResult, listResult;
	    
	    int numberOfErrors = 0;
	    int numberOfTests = 0;
	    
	    String displayedPrompt = "\nUlster Museum Menu\n" +
				"++++++++++\n" +
				"1. Manage Artifacts\n" +
				"2. Manage Exhibits\n" +
				"3. Manage Annual Plans\n" +
				"4. Save Data\n" +
				"5. Load Data\n" +
				"6. Exit\n" +
				"Enter Selection: ";
	    //--------------------- MAIN MENU OPTIONS -----------------
	    System.out.println("----------------MAIN MENU OPTIONS----------------"); 
	    // normal input
	    try {
		    prompt = api.getCurrentPrompt();
		    
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Main Menu Loaded correctly");
		    } 
		    else {
		      System.out.println("FAIL: Failed to describe main menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}  
	    // Invalid input
	    try {
	    	api.processInput("10");
		    prompt = api.getCurrentPrompt();
		    
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Main Menu did not change");
		    } 
		    else {
		      System.err.println("FAIL:Main Menu Changed");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}  
	    // normal input
	    try {
	    	result = api.processInput("6");
		    prompt = api.getCurrentPrompt();
		    
		    if (result.equals("EXIT") ) 
		    {
		      System.out.println("PASS: User has exit program");
		    } 
		    else {
		      System.err.println("FAIL: User has not exit program");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}  
	    
	    System.out.println("----------------MANAGE ARTIFACTS MENU OPTIONS----------------"); 
	 // Manage Artifacts
	    displayedPrompt ="\nUlster Museum Manage Artifacts\n" +
				"++++++++++\n" +
				"1. Create Artifact\n" +
				"2. View Artifacts\n" +
				"3. Delete Artifact\n" +
				"4. Update Artifact\n" +
				"5. Return to Main Menu\n" +
				"Enter Selection: ";
	    api = new QUBMuseum(); 
	    try {

		    
		    result = api.processInput("1");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Manage Artifacts Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe manage artifact menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}  
	    api = new QUBMuseum();
	    // invalid input
	    try {
		    api.processInput("1");
		    api.processInput("999");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Invalid input was handled correctly");
		    } 
		    else {
		      System.err.println("FAIL: Invalid input was NOT handled correctly");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}  
	    // ----------------- CREATE ARTIFACTS --------------
	 // Create Artifacts (name)
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nEnter Artifact name: ";

	    	
	    	api.processInput("1");
		    api.processInput("1");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Create Artifacts Name Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe create artifact name menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	 // Create Artifacts (Description)
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nEnter Artifact description: ";

	    	
	    	api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Create Artifacts Description Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe create artifact description menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	 // Create Artifacts (Engagement Time)
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nEnter Artifact engagement time in minutes: ";

	    	
	    	api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Create Artifacts Engagement Time Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe create artifact engagement time menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	 // Create Artifacts (Type)
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nUlster Museum Available Artifact Types\n"+
					"++++++++++\n" +
					"1. PAINTING"+ "		7. TOOL\n"+
					"2. SCULPTURE"+ "		8. FOSSIL\n"+
					"3. POTTERY"+ "		9. COIN\n"+
					"4. JEWELRY"+  "		10. PHOTOGRAPH\n"+
					"5. TEXTILE"+ "		11. DOCUMENT\n"+
					"6. WEAPON"+ "		12. OTHER\n"+
					"Enter Selection:";


	    	
	    	api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Create Artifacts Type Menu Loaded correctly");
		    } 
		    else {
		      System.out.println("FAIL: Failed to describe create artifact type menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	 // Check if Artifact Created (Type)
	    api = new QUBMuseum(); 
	    try {
	    	expectedResult = "Artifact with artifactId: artifact_0 has been successfully created";

	    	
	    	api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    result = api.processInput("1");
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifact was created ");
		    } 
		    else {
		      System.err.println("FAIL: Artifact was not created ");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	/*
	 *  Handle Getting Artifact id
	 *  The following all go to this state are:
	 *  	Get artifact info
	 *  	get artifact engagement time
	 *  	find artifacts
	 *  	update artifacts
	 *  	delete artifacts
	 */
	    api = new QUBMuseum(); 
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu \nEnter an artifact id : ";


	    	
	    	api.processInput("1");
		    api.processInput("3");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Getting Artifact Id Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe getting artifact id menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	    //delete Artifacts
	    api = new QUBMuseum(); 
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt = "\nArtifact Deletion Confirmation\n"+
					"++++++++++\n" +
					"1.To delete\n"+
					"2.To return to main menu\n"+
					"Enter Selection: ";


	    	
	    	api.processInput("1");
		    api.processInput("3");
		    api.processInput("artifact_0");
		    result = api.getArtifactInfo("artifact_0", "name;");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt) && result == null) 
		    {
		      System.out.println("PASS: Delete Artifact Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe delete artifact menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	    //update Artifact
	    api = new QUBMuseum(); 
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    displayedPrompt = "\nUlster Museum Available Info Names\n" +
				"++++++++++\n" +
				"1. Name\n" +
				"2. Type \n" +
				"3. Description\n" +
				"4. Engagement_minutes \n" +
				"Enter Selection: ";
	    try {

	    	
	    	api.processInput("1");
		    api.processInput("4");
		    api.processInput("artifact_0");
		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: (update Info) Artifacts InfoName Menu Loaded correctly");
		    } 
		    else 
		    {
		      System.err.println("FAIL: (update Info) Failed to describe artifact InfoName menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	  //update Artifact (name)
	    api = new QUBMuseum(); 
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt ="\nEnter new name: ";
	    	expectedResult = "Name: changedName";
	    	
	    	api.processInput("1");
		    api.processInput("4");
		    api.processInput("artifact_0");
		    api.processInput("1");
		    prompt = api.getCurrentPrompt();
		    api.processInput("changedName");
		    result = api.getArtifactInfo("artifact_0", "name");
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Update Artifacts Menu (name) Loaded correctly");
		    } 
		    else 
		    {
		      System.err.println("FAIL: Failed to describe update artifact (name) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	  //update Artifact (type)
	    api = new QUBMuseum(); 
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt = "\nUlster Museum Available Artifact Types\n"+
					"++++++++++\n" +
					"1. PAINTING"+ "		7. TOOL\n"+
					"2. SCULPTURE"+ "		8. FOSSIL\n"+
					"3. POTTERY"+ "		9. COIN\n"+
					"4. JEWELRY"+  "		10. PHOTOGRAPH\n"+
					"5. TEXTILE"+ "		11. DOCUMENT\n"+
					"6. WEAPON"+ "		12. OTHER\n"+
					"Enter Selection:";
	    	
	    	
	    	api.processInput("1");
		    api.processInput("4");
		    api.processInput("artifact_0");
		    api.processInput("2");
		    prompt = api.getCurrentPrompt();
		    
		    expectedResult = "Type: Sculpture";
		    api.processInput("2");
		    result = api.getArtifactInfo("artifact_0", "type");
		    if (prompt != null && displayedPrompt.equals(prompt) && result.equals(expectedResult)) 
		    {
		      System.out.println("PASS: Update Artifacts Menu (type) Loaded correctly");
		    } 
		    else 
		    {
		      System.err.println("FAIL: Failed to describe update artifact (type) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	  //update Artifact (description)
	    api = new QUBMuseum(); 
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt ="\nEnter new description: ";
	    	
	    	api.processInput("1");
		    api.processInput("4");
		    api.processInput("artifact_0");
		    api.processInput("3");
		    prompt = api.getCurrentPrompt();
		    
		    expectedResult = "Description: newDescription";
		    api.processInput("newDescription");
		    result = api.getArtifactInfo("artifact_0", "description");
		    if (prompt != null && displayedPrompt.equals(prompt) && result.equals(expectedResult)) 
		    {
		      System.out.println("PASS: Update Artifacts Menu (description) Loaded correctly");
		    } 
		    else 
		    {
		      System.err.println("FAIL: Failed to describe update artifact (description) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	  //update Artifact (engagement time)
	    api = new QUBMuseum(); 
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt ="\nEnter engagement time in minutes: ";
	    	
	    	api.processInput("1");
		    api.processInput("4");
		    api.processInput("artifact_0");
		    api.processInput("4");
		    
		    prompt = api.getCurrentPrompt();
		    expectedResult = "Engagement Time: 99";
		    api.processInput("99");
		    result = api.getArtifactInfo("artifact_0", "engagement_time");
		    if (prompt != null && displayedPrompt.equals(prompt) && result.equals(expectedResult)) 
		    {
		      System.out.println("PASS: Update Artifacts Menu (engagement time) Loaded correctly");
		    } 
		    else 
		    {
		      System.err.println("FAIL: Failed to describe update artifact (engagement time) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	 // Return to main menu
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nUlster Museum Menu\n" +
					"++++++++++\n" +
					"1. Manage Artifacts\n" +
					"2. Manage Exhibits\n" +
					"3. Manage Annual Plans\n" +
					"4. Save Data\n" +
					"5. Load Data\n" +
					"6. Exit\n" +
					"Enter Selection: ";


	    	
	    	api.processInput("1");
		    api.processInput("5");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Return to Main Menu (Artifacts Menu) Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe (from Artifacts Menu) main menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	 // View Artifacts 
	    System.out.println("----------------VIEW ARTIFACTS MENU OPTIONS----------------"); 
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    displayedPrompt = "\nUlster Museum View Artifacts\n" +
				"++++++++++\n" +
				"1. View all artifacts\n" +
				"2. Get Artifact Info \n" +
				"3. Get Artifact Engagement Time\n" +
				"4. Find Artifacts \n" +
				"5. Return to Main Menu \n" +
				"Enter Selection: ";
	    try {
	    	expectedResult = "\nId: artifact_0\n"
					+ "Name: Artifact\n"
					+ "Type: Painting\n"
					+ "Description: Description\n"
					+ "Engagement Time: 10 minutes" +"\n";
	    	api.processInput("1");
		    api.processInput("2");
		    
		    result = api.processInput("1");
		    prompt = api.getCurrentPrompt();
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: View Artifacts Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe view artifact menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    //invalid input
	    api = new QUBMuseum();
	    try {
	    	displayedPrompt = "\nUlster Museum Menu\n" +
					"++++++++++\n" +
					"1. Manage Artifacts\n" +
					"2. Manage Exhibits\n" +
					"3. Manage Annual Plans\n" +
					"4. Save Data\n" +
					"5. Load Data\n" +
					"6. Exit\n" +
					"Enter Selection: ";
	    	api.processInput("1");
		    api.processInput("2");
		    
		    result = api.processInput("5");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS:(View Artifacts) Return to Main Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: (View Artifacts)Failed to describe view main menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    displayedPrompt = "\nUlster Museum Available Info Names\n" +
				"++++++++++\n" +
				"1. Name\n" +
				"2. Type \n" +
				"3. Description\n" +
				"4. Engagement_minutes \n" +
				"Enter Selection: ";
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
		    
	    	api.processInput("1");
		    api.processInput("2");
		    
		    api.processInput("2");
		    api.processInput("artifact_0");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: (Get Info) Artifacts InfoName Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: (Get Info) Failed to describe artifact InfoName menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // get engagement time
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
		    expectedResult = "\nEngagement Time: 10 minutes";
	    	api.processInput("1");
		    api.processInput("2");
		    
		    api.processInput("3");
		    result = api.processInput("artifact_0");
		    prompt = api.getCurrentPrompt();
		    if (result != null && expectedResult.equals(result)) 
		    {
		      System.out.println("PASS: Artifacts engagement time Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifact engagement time menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // find artifacts (Search Criteria)
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    displayedPrompt = "\nUlster Museum Available Search Criterias\n" +
				"++++++++++\n" +
				"1. Name\n" +
				"2. Name contains \n" +
				"3. Id\n" +
				"4. Type\n" +
				"5. No Search criteria \n" +
				"Enter Selection: ";
	    try {
	    	
	    	api.processInput("1");
		    api.processInput("2");
		    api.processInput("4");

		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Artifacts Search Criteria Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifacts search criteria menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // find artifacts (Search Criteria (name))
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt = "\nEnter Artifact name to search: ";

	    	api.processInput("1");
		    api.processInput("2");
		    api.processInput("4");
		    api.processInput("1");

		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Artifacts Search Criteria (name) Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifacts search criteria (name) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // find artifacts (Search Criteria (name contains))
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt = "\nEnter containing text in artifact name to search: ";

	    	api.processInput("1");
		    api.processInput("2");
		    api.processInput("4");
		    api.processInput("2");

		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Artifacts Search Criteria (name contains) Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifacts search criteria (name contains) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	  // find artifacts (Search Criteria (Id))
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt = "\nArtifact Id to search: ";

	    	api.processInput("1");
		    api.processInput("2");
		    api.processInput("4");
		    api.processInput("3");

		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Artifacts Search Criteria (Id) Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifacts search criteria (Id) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // find artifacts (Search Criteria (type))
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt = "\nUlster Museum Available Artifact Types To Search\n"+
					"++++++++++\n" +
					"1. PAINTING"+ "		7. TOOL\n"+
					"2. SCULPTURE"+ "		8. FOSSIL\n"+
					"3. POTTERY"+ "		9. COIN\n"+
					"4. JEWELRY"+  "		10. PHOTOGRAPH\n"+
					"5. TEXTILE"+ "		11. DOCUMENT\n"+
					"6. WEAPON"+ "		12. OTHER\n"+
					"Enter Selection:";

	    	api.processInput("1");
		    api.processInput("2");
		    api.processInput("4");
		    api.processInput("4");

		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Artifacts Search Criteria (type) Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifacts search criteria (type) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    // find artifacts (Search Criteria (no criteria))
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    displayedPrompt = "\nQUB How would you like to sort the result?\n" +
				"++++++++++\n" +
				"1. Name\n" +
				"2. Type \n" +
				"3. Id\n" +
				"4. Engagement \n" +
				"5. Default \n" +
				"Enter Selection: ";
	    try {

	    	api.processInput("1");
		    api.processInput("2");
		    api.processInput("4");
		    api.processInput("5");

		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Artifacts Search Criteria (no criteria) Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifacts search criteria (no criteria) menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	 // find artifacts (Sort By)
	    api = new QUBMuseum();
	    api.createArtifact("Painting", "Artifact", "Description", 10);
	    try {
	    	displayedPrompt ="\nUlster Museum Manage Artifacts\n" +
					"++++++++++\n" +
					"1. Create Artifact\n" +
					"2. View Artifacts\n" +
					"3. Delete Artifact\n" +
					"4. Update Artifact\n" +
					"5. Return to Main Menu\n" +
					"Enter Selection: ";

	    	api.processInput("1");
		    api.processInput("2");
		    api.processInput("4");
		    api.processInput("5");
		    api.processInput("1");

		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Artifacts Sort By Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to get artifacts sort by menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}
	    
	 // -------------------- MANAGE EXHIBIT MENU------------------
	    System.out.println("----------------MANAGE EXBHIBITS MENU OPTIONS----------------"); 
	    displayedPrompt ="\nUlster Museum Manage Exhibits\n" +
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
	    //normal input
	    api = new QUBMuseum(); 
	    try {
		    
		    api.processInput("2");
		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Manage Exhibits Menu Loaded correctly");
		    } 
		    else {
		      System.err.println("FAIL: Failed to describe manage exhibit menu");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}  
	  //Invalid input
	    api = new QUBMuseum(); 
	    try {
		    api.processInput("2");
		    api.processInput("999");
		    
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Invalid input was handled correctly");
		    } 
		    else {
		      System.err.println("FAIL: Invalid input was NOT handled correctly");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {}  
	 // ----------------- CREATE EXHIBITS --------------
		 // Create Exhibits (name)
		    api = new QUBMuseum(); 
		    try {
		    	displayedPrompt = "\nEnter Exhibit name: ";

		    	
		    	api.processInput("2");
			    api.processInput("1");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Create Exhibits Name Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe create exhibit name menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Create Exhibits (Description)
		    api = new QUBMuseum(); 
		    try {
		    	displayedPrompt = "\nEnter Exhibit description: ";

		    	
		    	api.processInput("2");
			    api.processInput("1");
			    api.processInput("1");
			    prompt = api.getCurrentPrompt();
			    expectedResult = "Name: 1";
			    api.processInput("1");
			    result = api.getExhibitInfo("exhibit_0", "name");
			    if (prompt != null && displayedPrompt.equals(prompt) && result.equals(expectedResult)) 
			    {
			      System.out.println("PASS: Create Exhibits Description Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe create exhibits description menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    api = new QUBMuseum(); 
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an exhibit id : ";


		    	
		    	api.processInput("2");
			    api.processInput("3");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Getting Exhibit Id Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe getting exhibit id menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    //delete Exhibits
		    api = new QUBMuseum(); 
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt = "\nExhibit Deletion Confirmation\n"+
						"++++++++++\n" +
						"1.To delete\n"+
						"2.To return to main menu\n"+
						"Enter Selection: ";



		    	
		    	api.processInput("2");
			    api.processInput("3");
			    api.processInput("exhibit_0");
			    prompt = api.getCurrentPrompt();
			    expectedResult = "Name: 1";
			    api.processInput("1");
			    result = api.getExhibitInfo("exhibit_0", "name");
			    if (prompt != null && displayedPrompt.equals(prompt) && result.equals(null))  
			    {
			      System.out.println("PASS: Delete Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe delete exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    //update Exhibit
		    api = new QUBMuseum(); 
		    api.createExhibit("Exhibit","Description");
		    displayedPrompt = "\nUlster Museum Available Info Names\n" +
					"++++++++++\n" +
					"1. Name\n" +
					"2. Description\n" +
					"Enter Selection: ";
		    try {

		    	
		    	api.processInput("2");
			    api.processInput("4");
			    api.processInput("exhibit_0");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (update Info) Exhibits InfoName Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (update Info) Failed to describe exhibit InfoName menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		  //update Exhibit (name)
		    api = new QUBMuseum(); 
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt ="\nEnter new name: ";
		    	
		    	api.processInput("2");
			    api.processInput("4");
			    api.processInput("exhibit_0");
			    api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    expectedResult = "Name: changedName";
			    api.processInput("changedName");
			    result = api.getExhibitInfo("exhibit_0", "name");
			    if (prompt != null && displayedPrompt.equals(prompt) && result.equals(expectedResult)) 
			    {
			      System.out.println("PASS: Update Exhibits Menu (name) Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: Failed to describe update exhibit (name) menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		  //update Exhibit (description)
		    api = new QUBMuseum(); 
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt ="\nEnter new description: ";
		    	
		    	api.processInput("2");
			    api.processInput("4");
			    api.processInput("exhibit_0");
			    api.processInput("2");
			    
			    prompt = api.getCurrentPrompt();
			    expectedResult = "Description: newDescription";
			    api.processInput("newDescription");
			    result = api.getExhibitInfo("exhibit_0", "description");
			    if (prompt != null && displayedPrompt.equals(prompt) && result.equals(expectedResult)) 
			    {
			      System.out.println("PASS: Update Exhibits Menu (description) Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: Failed to describe update exhibit (description) menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // remove artifact from exhibit (stage1)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an exhibit id : ";



		    	
		    	api.processInput("2");
			    api.processInput("5");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage1) Remove Artifact From Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage1) Failed to describe remove artifact from exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // remove artifact from exhibit (stage2)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu \nEnter an artifact id : ";

		    	api.processInput("2");
			    api.processInput("5");
			    api.processInput("exhibit_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage2) Remove Artifact From Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage2) Failed to describe remove artifact from exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // remove artifact from exhibit (stage3)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nExhibit Artifact Removal Confirmation\n"+
						"++++++++++\n" +
						"1.To Remove\n"+
						"2.To return to main menu\n"+
						"Enter Selection: ";

		    	api.processInput("2");
			    api.processInput("5");
			    api.processInput("exhibit_0");
			    api.processInput("artifact_0");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage3) Remove Artifact From Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage3) Failed to describe remove artifact from exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // remove artifact from exhibit (stage4)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nUlster Museum Manage Exhibits\n" +
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

		    	api.processInput("2");
			    api.processInput("5");
			    api.processInput("exhibit_0");
			    api.processInput("artifact_0");
			    api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    expectedListResult = new ArrayList<String>();


			    listResult = api.getExhibitArtifacts("exhibit_0");
			    if (prompt != null && displayedPrompt.equals(prompt) && listResult.equals(expectedListResult)) 
			    {
			      System.out.println("PASS:(stage4) Remove Artifact From Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage4) Failed to describe remove artifact from exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add artifact to exhibit (stage1)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an exhibit id : ";
		    	
		    	api.processInput("2");
			    api.processInput("6");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage1) Add Artifact To Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage1) Failed to describe add artifact to exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add artifact to exhibit (stage2)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu \nEnter an artifact id : ";
		    	
		    	api.processInput("2");
			    api.processInput("6");
			    api.processInput("exhibit_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage2) Add Artifact To Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage2) Failed to describe add artifact to exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add artifact to exhibit (stage3)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    try {
		    	displayedPrompt = "\nEnter Artifact Signage: ";
		    	
		    	api.processInput("2");
			    api.processInput("6");
			    api.processInput("exhibit_0");
			    api.processInput("artifact_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage3) Add Artifact To Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage3) Failed to describe add artifact to exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add artifact to exhibit (stage4)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    try {
		    	displayedPrompt = "\nUlster Museum Manage Exhibits\n" +
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
		    	
		    	api.processInput("2");
			    api.processInput("6");
			    api.processInput("exhibit_0");
			    api.processInput("artifact_0");
			    api.processInput("Sign");
			    prompt = api.getCurrentPrompt();
			    expectedListResult = new ArrayList<String>();
			    expectedListResult.add("artifact_0");

			    listResult = api.getExhibitArtifacts("exhibit_0");
			    if (prompt != null && displayedPrompt.equals(prompt) && listResult.equals(expectedListResult))
			    {
			      System.out.println("PASS:(stage4) Add Artifact To Exhibit Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage4) Failed to describe add artifact to exhibit menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		  // Re order Artifacts (stage1)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an exhibit id : ";



		    	
		    	api.processInput("2");
			    api.processInput("7");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage1) ReOrder Exhibit Artifacts Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage1) Failed to describe ReOrder Exhibit Artifacts menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    // Re order Artifacts (stage2)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nQUB Artifact Order\n"
		    			+ "1. artifact_1\n"
		    			+ "2. artifact_0\n"
		    			+ "Please enter artifacts starting from the first to be shown: ";

		    	
		    	api.processInput("2");
			    api.processInput("7");
			    api.processInput("exhibit_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage2) ReOrder Exhibit Artifacts Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage2) Failed to describe ReOrder Exhibit Artifacts menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Re order Artifacts (stage3)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nQUB Artifact Order\n"
		    			+ "1. artifact_1\n"
		    			+ "Please enter artifacts starting from the first to be shown: ";

		    	
		    	api.processInput("2");
			    api.processInput("7");
			    api.processInput("exhibit_0");
			    api.processInput("2");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage3) ReOrder Exhibit Artifacts Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage3) Failed to describe ReOrder Exhibit Artifacts menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Re order Artifacts (stage4)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_1", "exhibit_0", "Sign");
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nUlster Museum Manage Exhibits\n" +
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

		    	
		    	api.processInput("2");
			    api.processInput("7");
			    api.processInput("exhibit_0");
			    api.processInput("2");
			    api.processInput("1");
			    prompt = api.getCurrentPrompt();
			    
			    
			    expectedListResult = new ArrayList<String>();
			    expectedListResult.add("artifact_0");
			    expectedListResult.add("artifact_1");

			    listResult = api.getExhibitArtifacts("exhibit_0");
			    if (prompt != null && displayedPrompt.equals(prompt) && listResult.equals(expectedListResult))
			    {
			      System.out.println("PASS:(stage4) ReOrder Exhibit Artifacts Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage4) Failed to describe ReOrder Exhibit Artifacts menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    
		   
		 // Return to main menu
		    api = new QUBMuseum(); 
		    try {
		    	displayedPrompt = "\nUlster Museum Menu\n" +
						"++++++++++\n" +
						"1. Manage Artifacts\n" +
						"2. Manage Exhibits\n" +
						"3. Manage Annual Plans\n" +
						"4. Save Data\n" +
						"5. Load Data\n" +
						"6. Exit\n" +
						"Enter Selection: ";


		    	
		    	api.processInput("2");
			    api.processInput("8");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Return to Main Menu (Exhibit Menu) Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe (from Exhibit Menu) main menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    
		 // View Exhibits 
		    System.out.println("----------------VIEW EXHIBITS MENU OPTIONS----------------"); 
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    displayedPrompt =  "\nUlster Museum View Exhibits\n" +
					"++++++++++\n" +
					"1. View all Exhibits\n" +
					"2. Get Exhibits Info \n" +
					"3. Find Exhibits \n" +
					"4. Get Exhibit Artifact Signage \n" +
					"5. Get Exhibit Artifacts \n" +
					"6. Return to Main Menu \n" +
					"Enter Selection: ";
		    // invalid input
		    try {
			    api.processInput("2");
			    api.processInput("2");
			    api.processInput("999");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Invalid input was handled correctly");
			    } 
			    else {
			      System.err.println("FAIL: Invalid input was NOT handled correctly");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}  
		    // view all exhibits
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    try {
		    	expectedResult = "\nId: exhibit_0\n"
						+ "Name: Exhibit\n"
						+ "Description: Description\n"
						+ "Total Engagement Time: 0 minutes" +"\n";
		    	api.processInput("2");
			    api.processInput("2");
			    
			    result = api.processInput("1");
			    prompt = api.getCurrentPrompt();
			    if (result != null && expectedResult.equals(result)) 
			    {
			      System.out.println("PASS: View Exhibits Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe view exhibits menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		    // return to main menu
		    api = new QUBMuseum();
		    try {
		    	displayedPrompt = "\nUlster Museum Menu\n" +
						"++++++++++\n" +
						"1. Manage Artifacts\n" +
						"2. Manage Exhibits\n" +
						"3. Manage Annual Plans\n" +
						"4. Save Data\n" +
						"5. Load Data\n" +
						"6. Exit\n" +
						"Enter Selection: ";
		    	api.processInput("2");
			    api.processInput("2");
			    
			    result = api.processInput("6");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(View Exhibits) Return to Main Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (View Exhibits)Failed to describe view main menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		    displayedPrompt = "\nUlster Museum Available Info Names\n" +
					"++++++++++\n" +
					"1. Name\n" +
					"2. Description\n" +
					"3. Total Engagement time\n" +
					"Enter Selection: ";

		    // get info
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    try {
			    
		    	api.processInput("2");
			    api.processInput("2");
			    
			    api.processInput("2");
			    api.processInput("exhibit_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Get Info) Exhibits InfoName Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (Get Info) Failed to describe Exhibit InfoName menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		    
		    
		 // find Exhibits (Search Criteria)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    displayedPrompt = "\nUlster Museum Available Search Criterias\n" +
					"++++++++++\n" +
					"1. Name\n" +
					"2. Name contains \n" +
					"3. Id\n" +
					"4. No Search criteria \n" +
					"Enter Selection: ";
		    try {
		    	
		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("3");

			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Exhibits Search Criteria Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to get Exhibits search criteria menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		 // find Exhibits (Search Criteria (name))
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt = "Enter Exhibit name to search: ";

		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("1");

			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Exhibits Search Criteria (name) Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to get Exhibits search criteria (name) menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		 // find Exhibits (Search Criteria (name contains))
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt = "\nEnter containing text in exhibit name to search: ";

		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("2");

			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Exhibits Search Criteria (name contains) Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to get Exhibits search criteria (name contains) menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		  // find Exhibits (Search Criteria (Id))
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt = "\nExhibit Id to search: ";

		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("3");

			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Exhibits Search Criteria (Id) Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to get Exhibits search criteria (Id) menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		    // find Exhibits (Search Criteria (no criteria))
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    displayedPrompt = "\nQUB How would you like to sort the result?\n" +
					"++++++++++\n" +
					"1. Name\n" +
					"2. Id\n" +
					"3. Default \n" +
					"Enter Selection: ";
		    try {

		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("4");

			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Exhibits Search Criteria (no criteria) Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to get Exhibits search criteria (no criteria) menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		 // find Exhibits (Sort By)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit","Description");
		    try {
		    	displayedPrompt ="\nUlster Museum Manage Exhibits\n" +
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

		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("4");
			    api.processInput("1");

			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Exhibits Sort By Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to get Exhibits sort by menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		    
		 // get artifact sign (stage1)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an exhibit id : ";
		    	api.processInput("2");
			    api.processInput("2");
			    
			    api.processInput("4");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(Stage1) Get Artifact Sign Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(Stage1) Failed to describe Get Artifact Sign Menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		    
		 // get artifact sign (stage2)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu \nEnter an artifact id : ";
		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("4");
			    api.processInput("exhibit_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(Stage2) Get Artifact Sign Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(Stage2) Failed to describe Get Artifact Sign Menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		 // get artifact sign (stage3)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    api.createArtifact("Painting","Artifact", "Description",5);
		    api.addArtifactToExhibit("artifact_0", "exhibit_0", "Sign");
		    try {
		    	displayedPrompt = "\nUlster Museum Manage Exhibits\n" +
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
		    	
		    	api.processInput("2");
			    api.processInput("2");
			    api.processInput("4");
			    api.processInput("exhibit_0");
			    api.processInput("artifact_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(Stage3) Get Artifact Sign Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(Stage3) Failed to describe Get Artifact Sign Menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		  // get Exhibit Artifacts (stage 1)
		    api = new QUBMuseum();
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an exhibit id : ";
		    	api.processInput("2");
			    api.processInput("2");
			    
			    result = api.processInput("5");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage1)Get Exhibit Artifacts Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage1)Failed to describe Get Exhibit Artifacts Menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		    
		 // get Exhibit Artifacts (stage 2)
		    api = new QUBMuseum();
		    api.createExhibit("Exhibit", "Description");
		    try {
		    	displayedPrompt = "\nUlster Museum Manage Exhibits\n" +
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
		    	api.processInput("2");
			    api.processInput("2");
			    
			    result = api.processInput("5");
			    result = api.processInput("exhibit_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS:(stage2)Get Exhibit Artifacts Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(stage2)Failed to describe Get Exhibit Artifacts Menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}
		  // ----------------------- EXHIBITS -------------------
		    
		    System.out.println("----------------MANAGE ANNUAL PLANS MENU OPTIONS----------------"); 
		 // Manage Artifacts
		    displayedPrompt ="\nUlster Museum Manage Annual Plans\n" +
					"++++++++++\n" +
					"1. Create Annual Plan\n" +
					"2. View Annual Plans\n" +
					"3. Delete Annual Plan\n" +
					"4. Update Annual Plan\n" +
					"5. Add Exhibit to Annual Plan\n" +
					"6. Return to Main Menu\n" +
					"Enter Selection: ";

		    api = new QUBMuseum(); 
		    try {
			    result = api.processInput("3");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Manage Annual Plans Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe manage annual plans menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    api = new QUBMuseum();
		 // invalid input
		    try {
			    api.processInput("3");
			    api.processInput("999");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Invalid input was handled correctly");
			    } 
			    else {
			      System.err.println("FAIL: Invalid input was NOT handled correctly");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    // ----------------- CREATE ANNUAL PLAN --------------
		  // Create Annual Plan 
		    api = new QUBMuseum(); 
		    try {
		    	displayedPrompt = "\nPlease enter a year: ";;

		    	
		    	api.processInput("3");
			    api.processInput("1");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Create Annual Plan Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe create annual plan menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		  // Delete Annual Plan
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nAnnual Plan Deletion Confirmation\n"+
						"++++++++++\n" +
						"1.To delete\n"+
						"2.To return to main menu\n"+
						"Enter Selection: ";
		    	
		    	api.processInput("3");
			    api.processInput("3");
			    api.processInput("plan_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Delete Annual Plan Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe delete annual plan menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Updating Year (handle Annual Plan Id)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt ="\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an plan id : ";
		    	
		    	api.processInput("3");
			    api.processInput("4");
			    //api.processInput("plan_0");
			    //api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Handle Plan Id Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: Failed to describe handle plan id menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Updating Year (stage1)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt ="\nUlster Museum Available Info Names\n"+
						"++++++++++\n" +
						"1.Year\n"+
						"2.Hall\n"+
						"Enter Selection: ";
		    	
		    	api.processInput("3");
			    api.processInput("4");
			    api.processInput("plan_0");
			    //api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage1) Update Annual Plan Year Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage1) Failed to describe update annual plan year menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Updating Year (stage2)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt ="\nPlease enter a year: ";

		    	
		    	api.processInput("3");
			    api.processInput("4");
			    api.processInput("plan_0");
			    api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage2) Update Annual Plan Year Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage2) Failed to describe update annual plan year menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Updating Hall (stage1)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt ="\nUlster Museum Available Halls\n"+
						"++++++++++\n" +
						"1.Hall A\n"+
						"2.Hall B\n"+ 
						"3.Hall C\n"+
						"Enter Selection: ";
		    	
		    	api.processInput("3");
			    api.processInput("4");
			    api.processInput("plan_0");
			    api.processInput("2");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage1) Update Annual Plan Hall Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage1) Failed to describe update annual plan hall menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Updating Hall (stage2)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nUlster Museum Months\n"+
						"++++++++++\n" +
						"1. January"+ "	7. July\n"+
						"2. February"+ "	8. August\n"+
						"3. March"+ "	9. September\n"+
						"4. April"+  "	10. October\n"+
						"5. May"+ "		11. November\n"+
						"6. June"+ "		12. December\n"+
						"Enter Selection:";

		    	
		    	api.processInput("3");
			    api.processInput("4");
			    api.processInput("plan_0");
			    api.processInput("2");
			    api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage2) Update Annual Plan Hall Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage2) Failed to describe update annual plan hall menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Updating Hall (stage3)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n"
						+ "Enter \"REMOVE\" to remove the exhibit in the hall\n" 
						+"Enter an exhibit id : ";

		    	
		    	api.processInput("3");
			    api.processInput("4");
			    api.processInput("plan_0");
			    api.processInput("2");
			    api.processInput("1");
			    api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage3) Update Annual Plan Hall Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage3) Failed to describe update annual plan hall menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add exhibit to Annual Plan (stage1)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an plan id : ";

		    	
		    	api.processInput("3");
			    api.processInput("5");
			    //api.processInput("plan_0");
			    //api.processInput("2");
			    //api.processInput("1");
			    //api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage1) Add Exhibit to Annual Plan Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage1) Failed to describe add exhibit to annual plan menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add exhibit to Annual Plan (stage2)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    try {
		    	displayedPrompt = "\nUlster Museum Available Halls\n"+
						"++++++++++\n" +
						"1.Hall A\n"+
						"2.Hall B\n"+ 
						"3.Hall C\n"+
						"Enter Selection: ";

		    	
		    	api.processInput("3");
			    api.processInput("5");
			    api.processInput("plan_0");
			    //api.processInput("1");
			    //api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage2) Add Exhibit to Annual Plan Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage2) Failed to describe add exhibit to annual plan menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add exhibit to Annual Plan (stage3)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    try {
		    	displayedPrompt = "\nUlster Museum Months\n"+
						"++++++++++\n" +
						"1. January"+ "	7. July\n"+
						"2. February"+ "	8. August\n"+
						"3. March"+ "	9. September\n"+
						"4. April"+  "	10. October\n"+
						"5. May"+ "		11. November\n"+
						"6. June"+ "		12. December\n"+
						"Enter Selection:";

		    	
		    	api.processInput("3");
			    api.processInput("5");
			    api.processInput("plan_0");
			    api.processInput("1");
			    //api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage3) Add Exhibit to Annual Plan Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage3) Failed to describe add exhibit to annual plan menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Add exhibit to Annual Plan (stage4)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    api.createExhibit("Exhibit", "Description");
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an exhibit id : ";

		    	
		    	api.processInput("3");
			    api.processInput("5");
			    api.processInput("plan_0");
			    api.processInput("1");
			    api.processInput("1");
			    
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage4) Add Exhibit to Annual Plan Menu Loaded correctly");
			    } 
			    else 
			    {
			      System.err.println("FAIL: (Stage4) Failed to describe add exhibit to annual plan menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Return to Main Menu
		    api = new QUBMuseum(); 
		    try {
		    	displayedPrompt ="\nUlster Museum Menu\n" +
						"++++++++++\n" +
						"1. Manage Artifacts\n" +
						"2. Manage Exhibits\n" +
						"3. Manage Annual Plans\n" +
						"4. Save Data\n" +
						"5. Load Data\n" +
						"6. Exit\n" +
						"Enter Selection: ";
		    	api.processInput("3");
			    api.processInput("6");

			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Returned to Main Menu");
			    } 
			    else {
			      System.err.println("FAIL: Did not return to main menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    System.out.println("----------------VIEW ANNUAL PLANS MENU OPTIONS----------------"); 
		 // ----------------- VIEW ANNUAL PLANS --------------
		    displayedPrompt = "\nUlster Museum View Annual Plans\n" +
					"++++++++++\n" +
					"1. View Annual Plans\n" +
					"2. Get Annual Plan\n" +
					"3. Get Annual Plan Info\n" +
					"4. Get Annual Plan Exhibit\n" +
					"5. Return to Main Menu\n" +
					"Enter Selection: ";
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {

		    	
		    	api.processInput("3");
			    api.processInput("2");

			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: View Annual Plans Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe view annual plans menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    api = new QUBMuseum();
			 // invalid input
		    try {
			    api.processInput("3");
			    api.processInput("2");
			    api.processInput("999");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Invalid input was handled correctly");
			    } 
			    else {
			      System.err.println("FAIL: Invalid input was NOT handled correctly");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    // View Annual Plans (OPTION1)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "Id: plan_0\n"
						+ "Year: 2026\n"
						+ "Total Exhibits: 0\n";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    result = api.processInput("1");
			    if (result != null && displayedPrompt.equals(result)) 
			    {
			      System.out.println("PASS:(OPTION1) View Annual Plans Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL:(OPTION1) Failed to describe view annual plans menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plan
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nPlease Enter a year to search: ";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("2");

			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Get Annual Plan Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: Failed to describe get annual plan menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plan Info (stage1)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an plan id : ";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("3");

			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage1) Get Annual Plan Info Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (Stage1)Failed to describe get annual plan info menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plan Info (stage2)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nUlster Museum Available Info Names\n"+
						"++++++++++\n" +
						"1.Year\n"+
						"2.Total Exhibits\n"+
						"Enter Selection: ";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("plan_0");

			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage2) Get Annual Plan Info Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (Stage2) Failed to describe get annual plan info menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plan Info (year)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "Year: 2026";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("plan_0");
			    result = api.processInput("1");

			    
			    if (result != null && displayedPrompt.equals(result)) 
			    {
			      System.out.println("PASS: (year) Get Annual Plan Info Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (year) Failed to describe get annual plan info menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plan Info (total exhibits)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "Total Exhibits: 0";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("3");
			    api.processInput("plan_0");
			    result = api.processInput("2");
			    
			    
			    if (result != null && displayedPrompt.equals(result)) 
			    {
			      System.out.println("PASS: (total Exhibits) Get Annual Plan Info Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (total Exhibits) Failed to describe get annual plan info menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plans Exhibit (stage1)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nEnter \"EXIT\" to go back to main menu\n" +
						"Enter an plan id : ";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("4");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage1) Get Annual Plans Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (Stage1) Failed to describe get annual plans menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plans Exhibit (stage2)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nUlster Museum Available Halls\n"+
						"++++++++++\n" +
						"1.Hall A\n"+
						"2.Hall B\n"+ 
						"3.Hall C\n"+
						"Enter Selection: ";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("4");
			    api.processInput("plan_0");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage2) Get Annual Plans Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (Stage2) Failed to describe get annual plans menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Get Annual Plans Exhibit (stage3)
		    api = new QUBMuseum(); 
		    api.createAnnualPlan(2026);
		    try {
		    	displayedPrompt = "\nUlster Museum Months\n"+
						"++++++++++\n" +
						"1. January"+ "	7. July\n"+
						"2. February"+ "	8. August\n"+
						"3. March"+ "	9. September\n"+
						"4. April"+  "	10. October\n"+
						"5. May"+ "		11. November\n"+
						"6. June"+ "		12. December\n"+
						"Enter Selection:";
						
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("4");
			    api.processInput("plan_0");
			    api.processInput("1");
			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: (Stage3) Get Annual Plans Menu Loaded correctly");
			    } 
			    else {
			      System.err.println("FAIL: (Stage3) Failed to describe get annual plans menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		 // Return to Main Menu
		    api = new QUBMuseum(); 
		    try {
		    	displayedPrompt ="\nUlster Museum Menu\n" +
						"++++++++++\n" +
						"1. Manage Artifacts\n" +
						"2. Manage Exhibits\n" +
						"3. Manage Annual Plans\n" +
						"4. Save Data\n" +
						"5. Load Data\n" +
						"6. Exit\n" +
						"Enter Selection: ";
		    	
		    	api.processInput("3");
			    api.processInput("2");
			    api.processInput("5");

			    prompt = api.getCurrentPrompt();
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Returned to Main Menu");
			    } 
			    else {
			      System.err.println("FAIL: Did not return to main menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    //Test the result is what you expect
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {} 
		    
		    System.out.println("----------------LOADING/SAVING MENU OPTIONS----------------"); 
		    // test save menu
		    api = new QUBMuseum(); 
		    try {
		    	displayedPrompt = "\nUlster Museum Save Data\n"+
						"++++++++++\n" +
						"1.Save Data\n"+
						"2.Return to Main Menu \n"+ 
						"Enter Selection: ";
		    	
		    	api.processInput("4");
			    prompt = api.getCurrentPrompt();
			    
			    if (prompt != null && displayedPrompt.equals(prompt)) 
			    {
			      System.out.println("PASS: Save Menu Loaded correctly");
			    } 
			    else {
			      System.out.println("FAIL: Failed to describe save menu");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}  
		    //testing save and loading functionality and overall system functionality
		    api = new QUBMuseum(); 
		    try {
		    	// test data:
		    	api.createAnnualPlan(2026);
		    	api.createArtifact("Painting", "Artifact0", "This is artifact_0", 20);
		    	api.createArtifact("Sculpture", "Artifact1", "This is artifact_1", 15);
		    	api.createArtifact("Weapon", "Artifact2", "This is artifact_2", 10);
		    	api.createExhibit("Exhibit0", "This is exhibit_0");
		    	api.createExhibit("Exhibit1", "This is exhibit_1");
		    	api.addArtifactToExhibit("artifact_1", "exhibit_0", "10");// artifact is 1 and exhibit is 0 so 1+0 =10
		    	api.addArtifactToExhibit("artifact_2", "exhibit_0", "20");
		    	api.addArtifactToExhibit("artifact_0", "exhibit_1", "01");
		    	api.addExhibitToAnnualPlan("exhibit_0", "plan_0", "HALL_A", 1);
		    	api.processInput("4");
			    prompt = api.getCurrentPrompt();
			    api.processInput("1");
			    
			    api = new QUBMuseum(); // create new instance to see if it saved
			    api.processInput("5"); //load data menu
			    api.processInput("1"); //confirm load
			    
			    String expectedDisplayedArtifacts =
			    		"\nId: artifact_0\n"
			    		+ "Name: Artifact0\n"
			    		+ "Type: Painting\n"
			    		+ "Description: This is artifact_0\n"
			    		+ "Engagement Time: 20 minutes\n"
			    		
			    		+ "\nId: artifact_1\n"
			    		+ "Name: Artifact1\n"
			    		+ "Type: Sculpture\n"
			    		+ "Description: This is artifact_1\n"
			    		+ "Engagement Time: 15 minutes\n"
			    		
			    		+ "\nId: artifact_2\n"
			    		+ "Name: Artifact2\n"
			    		+ "Type: Weapon\n"
			    		+ "Description: This is artifact_2\n"
			    		+ "Engagement Time: 10 minutes\n";
			    		
			    String expectedDisplayedExhibits = 
			    		"\nId: exhibit_0\n"
			    		+ "Name: Exhibit0\n"
			    		+ "Description: This is exhibit_0\n"
			    		+ "Total Engagement Time: 25 minutes\n"
			    		
			    		+ "\nId: exhibit_1\n"
			    		+ "Name: Exhibit1\n"
			    		+ "Description: This is exhibit_1\n"
			    		+ "Total Engagement Time: 20 minutes\n";
			    
			    String expectedDisplayedPlans ="Id: plan_0\n"
			    		+ "Year: 2026\n"
			    		+ "Total Exhibits: 1\n";
			    
			    
			    String expectedArtifactsInExhibit1 = "artifact_0";
			    String expectedArtifactsInExhibit0 = "artifact_1 , artifact_2";
			    String expectedExhibitInPlan0 = "exhibit_0 is in HALL_A-1";
			    String expectedArtifact1SignageInExhibit0 = "Signage: 10";
			    
			    
			    
			    api.processInput("1");
			    api.processInput("2");
			    String displayedArtifacts = api.processInput("1");
			    //System.err.println("DISPLAYED ARTIFACTS: " + displayedArtifacts.equals(expectedDisplayedArtifacts));
			    api.processInput("5");
			    api.processInput("2");
			    api.processInput("2");
			    String displayedExhibits = api.processInput("1");
			    //System.err.println("DISPLAYED EXHIBITS: " + displayedExhibits.equals(expectedDisplayedExhibits));
			    api.processInput("5");
			    String artifactsInExhibit0 = api.processInput("exhibit_0");
			    //System.err.println("ARTIFACTS IN EXHIBIT 0: " + artifactsInExhibit0.equals(expectedArtifactsInExhibit0));
			    api.processInput("2");
			    api.processInput("5");
			    String artifactsInExhibit1 = api.processInput("exhibit_1");
			    //System.err.println("ARTIFACTS IN EXHIBIT 1: " + artifactsInExhibit1.equals(expectedArtifactsInExhibit1));
			    api.processInput("8");
			    api.processInput("3");
			    api.processInput("2");
			    String displayedPlans = api.processInput("1");
			    //System.err.println("DISPLAYED PLANS: " + displayedPlans.equals(expectedDisplayedPlans));
			    api.processInput("4");
			    api.processInput("plan_0");
			    api.processInput("1");
			    String exhibitInPlan0 = api.processInput("1");
			    //System.err.println("EXHIBIT IN PLAN 0: " + exhibitInPlan0.equals(expectedExhibitInPlan0));
			    api.processInput("6");
			    api.processInput("2");
			    api.processInput("2");
			    api.processInput("4");
			    api.processInput("exhibit_0");
			    String Artifact1SignageInExhibit0 = api.processInput("artifact_1");
			 

			    //System.err.println("ARTIFACT SIGNAGE: " + Artifact1SignageInExhibit0.equals(expectedArtifact1SignageInExhibit0));
			    
			    if (displayedArtifacts != null && displayedExhibits != null && artifactsInExhibit0 != null && artifactsInExhibit1 != null && displayedPlans != null && displayedPlans != null && exhibitInPlan0 !=null && Artifact1SignageInExhibit0 != null
			    		&& displayedArtifacts.equals(expectedDisplayedArtifacts)
			    		&& displayedExhibits.equals(expectedDisplayedExhibits)
			    		&& artifactsInExhibit0.equals(expectedArtifactsInExhibit0)
			    		&& artifactsInExhibit1.equals(expectedArtifactsInExhibit1)
			    		&& displayedPlans.equals(expectedDisplayedPlans)
			    		&& exhibitInPlan0.equals(expectedExhibitInPlan0)
			    		&& Artifact1SignageInExhibit0.equals(expectedArtifact1SignageInExhibit0)) 
			    {
			      System.out.println("PASS: SAVING AND LOADING HAS PROCESSED CORRECTLY");
			    } 
			    else {
			      System.err.println("FAIL: SAVING AND LOADING BROKE");
			      numberOfErrors = numberOfErrors+1;
			    }
			    
			    
			    numberOfTests = numberOfTests+1;
		    } catch (Exception e) {}  
    }

    
	@Override
    public void runFuzzingTests() 
    {
		String result, prompt, displayedPrompt;
		
	    
	    int numberOfErrors = 0;
	    int numberOfTests = 0;
	    /* Test Any Multiple Choice Input (switch case which uses a STRING) including:
	     * Menus : Main , Manage Artifacts/Exhibits/Plans , View Artifacts/Exhibits/Plans, Save, Load
	     * Creating/updating artifact type
	     * Selecting Search criteria/sort by
	     * Selecting which month/hall an exhibit should go in.
	     * Re ordering artifacts in exhibit
		 */
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nUlster Museum Menu\n" +
					"++++++++++\n" +
					"1. Manage Artifacts\n" +
					"2. Manage Exhibits\n" +
					"3. Manage Annual Plans\n" +
					"4. Save Data\n" +
					"5. Load Data\n" +
					"6. Exit\n" +
					"Enter Selection: ";

	    	
	    	api.processInput("&^&^&*^*&##&#*@*(&^&^&#*^*#*%^&%&*@*&(*&@&@^&%@^&%#%#*&^*&*@&*@&@(");

		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: String was Accepted");
		    } 
		    else {
		      System.err.println("FAIL: String was not accepted");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 
	 /* Test Any String Input for any case including:
	  * Find Artifacts/Exhibits by name or name_contains
	  * Create Artifact/Exhibit Name and description
	  * Update Artifact/Exhibit Name and description
	  * Add Artifact to exhibit (SIGN)
	  */
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nEnter Artifact description: ";

	    	
	    	api.processInput("1");
		    api.processInput("1");
		    api.processInput("2gryebsgryu wgirh3we hrwuie gh8o gqrg 3vtweu 3gwg rweg 28g gr3wgeys u3g2qrgwg 1e2quygyu2qrgwyg gsdyut2873qgwusz 2gqw 7wr8i wrgeudg wds7g82qgwag27r%RTT&*T8 T* gT^h yisd gfsdugsavsdwyfbsg rwg8egeysT 375BRR");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: String was Accepted");
		    } 
		    else {
		      System.err.println("FAIL: String was not accepted");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) {} 

	 // Test Any Integer Input
	    api = new QUBMuseum(); 
	    try {
	    	displayedPrompt = "\nEnter Artifact engagement time in minutes: ";

	    	
	    	api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    api.processInput("1");
		    api.processInput("98973979723875239753728527395723587239572563763528652675236567238726");
		    prompt = api.getCurrentPrompt();
		    if (prompt != null && displayedPrompt.equals(prompt)) 
		    {
		      System.out.println("PASS: Integer was not accepted");
		    } 
		    else {
		      System.err.println("FAIL: Integer was accepted");
		      numberOfErrors = numberOfErrors+1;
		    }
		    
		    //Test the result is what you expect
		    
		    numberOfTests = numberOfTests+1;
	    } catch (Exception e) { System.err.println("PASS: Integer was not accepted");} 
    	
    }
}
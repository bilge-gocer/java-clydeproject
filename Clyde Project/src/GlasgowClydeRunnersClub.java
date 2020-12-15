import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class GlasgowClydeRunnersClub {

	public static void main(String[] args) throws IOException {
		

		Scanner in = new Scanner(System.in);
		
		String password = "clyderunners";
		int fail = 3;
		
		
		System.out.println("*****  Welcome to Glasgow Clyde Runners Club!  *****");
		System.out.println();
            	
            	do {    
            		    
            		System.out.println("Please enter your password to continue: ");    
            		String login = in.nextLine(); 
            		if (login.equals(password)) {        
            			System.out.println("Password Validated");
            			System.out.println();
            			Menu(); 
            			}    
            		else 
            		{        
            			fail--;        
            			System.out.println("Your Password is incorrect"); //print message to user 
            			       
            			System.out.println("You have: " + fail + " attempts left.");   
            			}
            		}while(fail!=0);
            	System.out.println("Number of attempts exceeded. You are now locked out.");
            	System.exit(0);
            		}
            	
				

	private static void Menu() throws IOException {
		
			int choice=0;
			Scanner input = new Scanner(System.in);
			
			while (choice!=8) 
			{
				System.out.println("***************** MENU ****************");
				System.out.println();
				System.out.println("Please enter a number from Menu below");
				System.out.println();
				System.out.println("1. Read and Display File");
				System.out.println("2. Sort and Print Recorded Times");
				System.out.println("3. Find and Print Fastest Time");
				System.out.println("4. Find and Print the Slowest Time");
				System.out.println("5. Search");
				System.out.println("6. Time Occurrence");
				System.out.println("7. Exit");

				choice = input.nextInt();
				input.nextLine();
            
            
            
            if (choice == 1)
            {
            	ArrayList<String[]> lines = new ArrayList<>();
            	Scanner in = new Scanner(new File("src//raceResults.txt"));
            	
            	while(in.hasNextLine()) 
            	{
            	    String line = in.nextLine().trim();
            	    String[] splitted = line.split(", ");
            	    for(int i = 0; i<splitted.length; i++) {
            	        
            	        splitted[i] = splitted[i].substring(0,splitted[i].length());
            	    }
            	    lines.add(splitted);
            	}
            	//now convert List<String[]> to String[][]
            	String[][] result = new String[lines.size()][];
            	
            	for(int i = 0; i<result.length; i++) {
            	    result[i] = lines.get(i);
            	}

            	System.out.println("Race Results: "+"\n" + Arrays.deepToString(result)+"\n");
               
            }
            
            else if (choice == 2)
            {

            	class firstNameCompare implements Comparator<GlasgowClydeRunnersClub>
                      {
            			@Override
            			public int compare(GlasgowClydeRunnersClub o1, GlasgowClydeRunnersClub o2) 
            			{

            				return o1.firstName.compareTo(o2.firstName);
            			}

                      }
                  class secondNameCompare implements Comparator<GlasgowClydeRunnersClub>
                            {
            			@Override
            			public int compare(GlasgowClydeRunnersClub o1, GlasgowClydeRunnersClub o2) 
            			{

            				return o1.secondName.compareTo(o2.secondName);
            			}

                        	}

                  class timesCompare implements Comparator<GlasgowClydeRunnersClub> 
                        {
                    	public int compare(GlasgowClydeRunnersClub o1, GlasgowClydeRunnersClub o2)
                    	{
                    				
                    		 return o1.time-o2.time;
                    	}

                    		}
                        BufferedReader reader = new BufferedReader(new FileReader("src//raceResults.txt"));
                        
                        ArrayList<GlasgowClydeRunnersClub> runnerRecords = new ArrayList<GlasgowClydeRunnersClub>();
                        
                            String currentLine = reader.readLine();
                            
                            while (currentLine != null)
                            {
                            	String[] runnerDetail =currentLine.split(" ");
                            	String firstName = runnerDetail[0];
                            	String secondName = runnerDetail[1];
                            	int time = Integer.valueOf(runnerDetail[2]);
                            	runnerRecords.add(new GlasgowClydeRunnersClub(firstName,secondName, time));
                            	currentLine = reader.readLine();
                            	
                            	}
                            
                            Collections.sort(runnerRecords, new timesCompare());
                            
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src//choice2.txt"));
                            for (GlasgowClydeRunnersClub student : runnerRecords)
                            {
                            	writer.write(student.firstName);
                            	writer.write(" "+student.secondName);
                            	writer.write(" "+student.time);
                            	writer.newLine();
                            	System.out.println(student.time);
                            }
                            reader.close();
                            writer.close();
            }
			
            else if (choice == 3)
            {

            	ArrayList<Integer> list = new ArrayList<Integer>();
            	Scanner file = null;

                try {
                        file = new Scanner(new File("src//raceResults.txt"));
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                
                while(file.hasNext()){
                        if (file.hasNextInt())
                        {
                                list.add(file.nextInt());
                        	
                        }
                        else
                        {
                                file.next();
                        }
                }
        
                System.out.println("Fastest Time: " + Collections.max(list));
                       
            	   PrintWriter out = null;
            	   try 
            	   {
            		   out = new PrintWriter(new FileWriter("src//choice3.txt"));
            		   
            	   } catch (IOException e) {
            		   e.printStackTrace();
            	   }
            	   out.println("Fastest Time: " + Collections.max(list));
            	   System.out.println();
            	   out.close();
                
               
            }
            
            else if (choice == 4)
            {
            	ArrayList<Integer> list = new ArrayList<Integer>();
            	Scanner file = null;
            	
                try {
                        file = new Scanner(new File("src//raceResults.txt"));
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                
                while(file.hasNext()){
                        if (file.hasNextInt())
                        {
                                list.add(file.nextInt());
                        }
                        else
                        {
                                file.next();
                        }
                }
        
                System.out.println("Slowest Time: " + Collections.min(list));
                          
            	   PrintWriter out = null;
            	   try 
            	   {
            		   out = new PrintWriter(new FileWriter("src//choice4.txt"));
            		   
            	   } catch (IOException e) {
            		   e.printStackTrace();
            	   }
            	   out.println("Slowest Time: " + Collections.min(list));
            	   System.out.println();
            	   out.close();
                
            }
            
            else if (choice == 5)
            {

            	ArrayList<Integer> list = new ArrayList<Integer>();
            	
             	Scanner input2 = new Scanner(System.in);
            	System.out.println("Please enter a time(second) to search :");
            	int number = input2.nextInt();
            	
            	Scanner file = null;
            
            	try {
            		file = new Scanner(new File("src//raceResults.txt"));
            	} catch (FileNotFoundException e) {
            		e.printStackTrace();
            	}
        
            	while(file.hasNext())
            	{
            		if (file.hasNextInt())
            		{
                        list.add(file.nextInt());
            		}
            		else
            		{
                        file.next();
            		}
            	}
            	
            	PrintWriter out = null;
        
            	try 
            		{
            			out = new PrintWriter(new FileWriter("src//choice5.txt"));
  		   
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
            	for (int i =0; i<list.size();i++) 
            	{
            		if(list.get(i)==number)
            		  {
            			System.out.println("Match");
            			out.println(" Match");
            			
            		  }
            		else {
                        System.out.println("Not Match");
                        out.println(" Not Match");
                    }
            			
            	 }
            	out.close();
  
              }
            
           	else if (choice == 6)
            {
           		ArrayList<Integer> list = new ArrayList<Integer>();
           		
           		Scanner input3 = new Scanner(System.in);
            	System.out.println("Please enter a time(second) to search :");
            	int number = input3.nextInt();
            	
            	Scanner file = null;
          
            	try {
            		file = new Scanner(new File("src//raceResults.txt"));
            	} catch (FileNotFoundException e) {
            		e.printStackTrace();
            	}
        
            	while(file.hasNext())
            	{
            		if (file.hasNextInt())
            		{
                        list.add(file.nextInt());
            		}
            		else
            		{
                        file.next();
            		}
            	}

            	PrintWriter out = null;
        
            	try 
            		{
            			out = new PrintWriter(new FileWriter("src//choice6.txt"));
  		   
            		} catch (IOException e) {
            			e.printStackTrace();
            		}
            	int count = 0;
            	for (int i =0; i<list.size();i++) 
            	{
            		if(list.get(i)==number)
            		  {
            			count++;
            			
            		  }
            	 }
            	System.out.println("There are " +"'"+ count +"'"+ " Match Passes in the runners.");
            	out.println("There are " +"'"+ count +"'"+ " Match Passes in the runners.");
         	  
            	out.close();
         	   
            }
            
           	else if (choice == 7) 
           	{
           		System.out.println("Thank you for using Glasgow Clyde Runners Club. ");
           		System.exit(0);
           	}
            
		}
	}
	
		String firstName;
		String secondName;
		int time;

	public GlasgowClydeRunnersClub(String firstName, String secondName, int time)
	   {
		this.firstName = firstName;
		this.secondName = secondName;
		this.time = time;
	   }
	
} // end of class

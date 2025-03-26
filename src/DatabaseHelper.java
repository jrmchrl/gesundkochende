//Database Systems (Module IDS) 

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

// The DatabaseHelper class encapsulates the communication with our database
class DatabaseHelper {
    // Database connection info
    private static final String DB_CONNECTION_URL = "xxx";
    private static final String USER = "xxx";
    private static final String PASS = "xxx"; 

    // The name of the class loaded from the ojdbc14.jar driver file
    //private static final String CLASSNAME = "oracle.jdbc.driver.OracleDriver";

    // We need only one Connection and one Statement during the execution => class variable
    private static Statement stmt;
    private static Connection con;
    Random rand = new Random();
    
    //CREATE CONNECTION
    DatabaseHelper() {
        try {
            //Loads the class into the memory
            //Class.forName(CLASSNAME);

            // establish connection to database
            con = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASS);
            stmt = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //INSERT INTO RECIPE
    
    void insertIntoRecipe(int counter) {
    	if (counter!= 0) {
       		for (int i = 0; i<counter; ++i) {System.out.println("Recipe: " + (i+1));
    		
	    		try {
	    			String rectitle = RandomCreator.generaterectitle();
	    			String recdescription = RandomCreator.generaterecdescription();
	    			String recmonth = RandomCreator.generaterecmonth();
	    			
	    			String sql = "INSERT INTO RECIPE (RTITLE, RDESCRIPTION, RMONTH) VALUES ('" +
	    				rectitle +
	                    "', '" +
	                    recdescription +
	                    "', '" +
	                    recmonth + "')";
	    			System.out.println(sql);
	    			stmt.execute(sql);
	        } catch (Exception e) {
	            System.err.println("Error at: insertIntoRecipe\nmessage: " + e.getMessage());
	        }
    		}
	   }
       System.out.println(counter + " values inserted with 'insertIntoRecipe' (Table: Recipe)"); 
    }
    
   

    //INSERT INTO INGREDIENT
   
   void insertIntoIngredient() {
    	String[] ingredients = RandomCreator.getIngredientNames();
    	String ingredient;
    	String ingredienttype;
    	String ingprep;
    	int counter = 0;
    	for (int i = 0; i < ingredients.length; ++i) {
    		ingredient = ingredients[i];
    		ingredienttype = RandomCreator.generateingredienttype();
    		ingprep = RandomCreator.generateingprep();
    		try {
    			String sql = "INSERT INTO INGREDIENT VALUES ('" +
    					ingredient +
    					"', '" +
    					ingredienttype +
    					"', '" +
    					ingprep + "')";
    			stmt.execute(sql);
    			counter++;
    		} catch (Exception e) {
    			System.err.println("Error at: insertIntoIngredient\nmessage: " + e.getMessage());
    		}
    	}
    	System.out.print(counter+ " values inserted with 'insertIntoIngredient' (Table: Ingredient)");
    }
    	
    
    
   //INSERT INTO HAS
    
    void insertIntoHAS(int counter) {
    	if (counter!= 0) {
    		ArrayList<Integer> ListOfRecipeIDs = getColumnInt("RECIPE", "RID");
    		ArrayList<String>  ListOfIngNames = getColumn("INGREDIENT", "IINGREDIENTNAME");

    		for (int i = 0; i<counter; ++i) {
    			System.out.println("HAS: " + (i+1));
    			try {
    				String IINGREDIENTNAME = null;
    				Integer RID = null;
    				Integer ingramount = RandomCreator.generateingramount();
    				String ingrunit = RandomCreator.generateingrunit();

    				if (ingrunit == "GRAMM") {
    					ingramount = ingramount * 100;
    				}

    				IINGREDIENTNAME = ListOfIngNames.get(rand.nextInt(ListOfIngNames.size()-1));
    				RID = ListOfRecipeIDs.get(rand.nextInt(ListOfRecipeIDs.size()-1));

    				String sql = "INSERT INTO HAS VALUES ('" + 
    						RID + 
    						"', '" +
    						IINGREDIENTNAME + 
    						"', '" +
    						ingramount + 
    						"', '" + 
    						ingrunit + "')";

    				System.out.println(sql);

    				stmt.execute(sql);
    			} catch (Exception e) {
    				System.err.println("Error at: insertIntohas\nmessage: " + e.getMessage());
    			}
    		}	
    	}
    	System.out.println(counter + " values inserted with 'insertIntohas' (Table: Seasonality)"); 
    }

    //INSERT INTO Seasonality
    void insertIntoSeasonality() {
    	ArrayList<String>  SelectedIngNames = getColumn("INGREDIENT", "IINGREDIENTNAME");
    	String iingredientname;
    	String ijan = null;
    	String ifeb = null;
    	String imar = null;
    	String iapr = null;
    	String imay = null;
    	String ijun = null;
    	String ijul = null;
    	String iaug = null;
    	String isep = null;
    	String ioct = null;
    	String inov = null;
    	String idec = null;


    	for (int i = 0; i < SelectedIngNames.size()-1; ++i) {
    		iingredientname = SelectedIngNames.get(i);
    		ijan = RandomCreator.generateijan();
    		ifeb = RandomCreator.generateifeb();
    		imar = RandomCreator.generateimar();
    		iapr = RandomCreator.generateiapr();
    		imay = RandomCreator.generateimay();
    		ijun = RandomCreator.generateijun();
    		ijul = RandomCreator.generateijul();
    		iaug = RandomCreator.generateiaug();
    		isep = RandomCreator.generateisep();
    		ioct = RandomCreator.generateioct();
    		inov = RandomCreator.generateinov();
    		idec = RandomCreator.generateidec();

    		try {
    			String sql = "INSERT INTO SEASONALITY (IINGREDIENTNAME, INGJAN, INGFEB, INGMAR, INGAPR, INGMAY, INGJUN, INGJUL, INGAUG, INGSEP, INGOCT, INGNOV, INGDEC) VALUES ('" + 
    					iingredientname + 
    					"', '" +
    					ijan + 
    					"', '" + 
    					ifeb + 
    					"', '" + 
    					imar + 
    					"', '" + 
    					iapr + 
    					"', '" + 
    					imay + 
    					"', '" + 
    					ijun + 
    					"', '" + 
    					ijul + 
    					"', '" + 
    					iaug +
    					"','" + 
    					isep + 
    					"','" + 
    					ioct +
    					"', '" + 
    					inov + 
    					"', '" + 
    					idec + "')";
    			stmt.execute(sql);
    		} catch (Exception e) {
    			System.err.println("Error at: insertIntoSeasonality\nmessage: " + e.getMessage());
    		}

    	}
    }
    
 
    //SELECT
    
    ArrayList<String> getColumn(String Tablename, String Column) {
         ArrayList<String> TableEntries = new ArrayList<>();
         try {
             ResultSet rs = stmt.executeQuery("SELECT " + Column + " FROM " + Tablename);
             while (rs.next()) {
             	TableEntries.add(rs.getString(Column));
             }
             rs.close();
         } catch (Exception e) {
             System.err.println(("Error at: getColumn\n message: " + e.getMessage()).trim());
         }
         return TableEntries;
     }
    
    
    ArrayList<Integer> getColumnInt(String Tablename, String Column) {
    	ArrayList<Integer> TableEntries = new ArrayList<>();
    	try {
    		ResultSet rs = stmt.executeQuery("SELECT " + Column + " FROM " + Tablename);
    		while (rs.next()) {
    			TableEntries.add(rs.getInt(Column));
    		}
    		rs.close();
    	} catch (Exception e) {
    		System.err.println(("Error at: getColumnInt\n message:" + e.getMessage()).trim());
    		}
    	return TableEntries;
    }
    
    
    public void close()  {
        try {
            stmt.close(); //clean up
            con.close();
        } catch (Exception ignored) {
        }
    }
}

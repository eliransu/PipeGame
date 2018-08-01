package Management;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PipeCacheManager implements CacheManager<ArrayList<String>> {
	Integer hashToString = new Integer(0); // converting the prem int to Integer (TODO tostring method..)
	Map<Integer,ArrayList<String>> hashCache = new HashMap<Integer,ArrayList<String>>();
	Logger logger = Logger.getLogger(PipeCacheManager.class.getName());
	@Override
	public void save(Integer ProblemID,ArrayList<String> dataToSave) { 
		/*THIS FUNCTION WORKING WHEN THE SOLUTION DOESNT EXIST, THEN THE CLIENT-HANDLER SEND ME THE
		 * SOLUTION AFTER HE CALCULATE IT--->
		 * THEN IM WRITING THE NEW SOLUTION TO SOME FILE -->
		 * THEN WRITING THE SOLUTION TO THE MEMORY, TO THE HASH TABLE.
		 */
		try {
			hashToString = ProblemID;
			File file = new File(hashToString.toString());
			file.createNewFile();
			FileWriter fop = new FileWriter(file);
			for (int i=0;i<dataToSave.size();i++)
			{
				fop.write(dataToSave.get(i));
				fop.flush();
			}
			hashCache.put(hashToString,dataToSave);
			fop.flush();
			fop.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<String> load(ArrayList<String> userLookingFor) {
		/*
		 * THIS FUNCTION WORKING ON 2 TYPES OF LOADING:
		 * 1) IF SOLUTION EXISTS IN HASH TABLE
		 * 2) IF SOLUTION EXISTS IN FILE --> THEN READ IT TO HASH AND RETURN VALUE TO USER!
		 */
		if (hashCache.containsValue(userLookingFor))
			return hashCache.get(userLookingFor.hashCode());
		else // if the value isn't in the hash
		{

			hashToString = userLookingFor.hashCode();
			try {
				String line = new String();
				ArrayList<String> ar = new ArrayList<String>();
				File fileName = new File(hashToString.toString());
				BufferedReader read = new BufferedReader(new FileReader(fileName));
			    while ((line = read.readLine()) != null)
			    {
			      ar.add(line); // reading the solution.
			    }
			    logger.info("the data to load is :" + ar);
			    read.close();
			    hashCache.put(hashToString,ar); // insert to HASH.
			    return ar; // return the solution to user.
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null; // IF SOMETHING GOT WRONG
	}

	@Override
	public boolean isExist(ArrayList<String> ifDataIsExist) {
		/* THIS FUNCTION CHECKING 3 SITUATIONS:
		 * IF SOLUTION EXISTS IN HASH TABLE
		 * IF SOLUTION EXISTS IN SOME FILE
		 * ELSE --> RETURN FALSE.
		 */
		hashToString = ifDataIsExist.hashCode();
		if (hashCache.containsValue(ifDataIsExist)) 
			return true;
		else if (Files.exists(Paths.get(hashToString.toString())))
			return true;
		// THIS LINE WAS FOR TESTING !! else if (Files.exists(Paths.get(bla.toString())))
		//	return true;
		else
			return false;
	}
	

}

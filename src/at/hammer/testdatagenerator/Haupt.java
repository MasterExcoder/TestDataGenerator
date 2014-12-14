package at.hammer.testdatagenerator;

import java.time.LocalDate;
import java.util.LinkedList;
import java.io.*;

public class Haupt {

    public static void main(String[] args) {
        if(args.length != 0) {
            int dataCount = Integer.parseInt(args[0]);
            String filePath = args[1];

            generateAndWriteRandomDataToFile(dataCount,filePath);
        }
        else if (args.length == 0) {
            generateAndWriteRandomDataToFile(200, "RandomData.txt");
        }

    }

    /**
     * Generates random Test Data in a linkedList
     * @param count Count how many data sets should be created
     * @return
     */
    public static LinkedList<Titles> generate(int count)
    {
        LinkedList<Titles> data = new LinkedList<Titles>();

        for(int i=0;i<count;i++)
        {	
            String titleId = fillIntWithNullsAndReturnString(randBetween(1, 9999));
            
            String name = null;
            try {
                name = RandomStringGenerator.generateRandomString(15, RandomStringGenerator.Mode.ALPHA);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int duration = randBetween(120,250);
            
            String artist = null;
            try {
                artist = RandomStringGenerator.generateRandomString(15, RandomStringGenerator.Mode.ALPHA);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            String album = null;
            try {
                album = RandomStringGenerator.generateRandomString(15, RandomStringGenerator.Mode.ALPHA);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            LocalDate release = LocalDate.of(randBetween(1950,2014),randBetween(1,12),randBetween(1,28));
            
            int playedCount = randBetween(0, 30);
            
            int chartPosition = randBetween(1, 100);

            data.add(new Titles(titleId,name,duration,artist,album,release, playedCount, chartPosition));
        }

        return data;
    }

    public static void generateAndWriteRandomDataToFile(int dataCount, String filePath)
    {
        LinkedList<Titles> randData = generate(dataCount);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
//            out.write("TitleId,Name,Duration,Artist,Album,Release,Played,Chart Position" + "\n");
            for (Titles t: randData) {
                out.write(t.toString() + "\n");
            }
            out.close();
            System.out.println("File successfully saved to: " + filePath);
        } catch (IOException e) { e.printStackTrace();}
    }
    
    /**
     * Fills int which is smaller than 4 with nulls, but why? for the fucking sake of it!
     * @param inputInt
     * @return
     */
    public static String fillIntWithNullsAndReturnString(int inputInt) {
    	String inputString = String.valueOf(inputInt);
    	
    	String outputString = "";
    	
    	if(inputString.length() == 1) {
    		outputString = "000" + inputString;
    	} else if(inputString.length() == 2) {
    		outputString = "00" + inputString;
    	} else if(inputString.length() == 3) {
    		outputString = "0" + inputString;
    	} else if(inputString.length() == 4){
    		outputString = inputString;
    	}
    	
    	return outputString;
    }

    /**
     * Generates a random int in the specified range
     * @param start
     * @param end
     * @return
     */
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}

package at.hammer.testdatagenerator;

import java.time.LocalDate;
import java.util.LinkedList;
import java.io.*;

import at.hammer.testdatagenerator.RandomStringGenerator.Mode;

public class TestDataGenerator {

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



/**
 * Created by Samuel Hammer on 30.09.2014.
 */
class Titles {
    private String titleId;
    private String name;
    private int duration;
    private String artist;
    private String album;
    private LocalDate release;
    private int playedCount;
	private int chartPosition;

    public Titles(String titleId, String name, int duration, String artist, String album, 
    		LocalDate release, int playedCount, int chartPosition) {
        super();
        this.titleId = titleId;
        this.name = name;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
        this.release = release;
        this.playedCount = playedCount;
        this.chartPosition = chartPosition;
    }

    @Override
    public String toString() {
        String t = titleId + "\t" + name + "\t" + duration + "\t" + artist + "\t" + album + "\t" + 
        		release + "\t" + playedCount + "\t" + chartPosition;
        return t;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }
    
    public int getPlayedCount() {
		return playedCount;
	}

	public void setPlayedCount(int playedCount) {
		this.playedCount = playedCount;
	}

	public int getChartPosition() {
		return chartPosition;
	}

	public void setChartPosition(int chartPosition) {
		this.chartPosition = chartPosition;
	}
}



class RandomStringGenerator {

    public static enum Mode {
        ALPHA, ALPHANUMERIC, NUMERIC
    }

    public static String generateRandomString(int length, Mode mode) throws Exception {

        StringBuffer buffer = new StringBuffer();
        String characters = "";

        switch(mode){

            case ALPHA:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            case NUMERIC:
                characters = "1234567890";
                break;
        }

        int charactersLength = characters.length();

        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }
}

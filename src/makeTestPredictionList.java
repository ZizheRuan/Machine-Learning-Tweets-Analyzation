import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class makeTestPredictionList {
	
	static String[] outputArray1;
	static String[] outputArray2;
	static String[] outputArrayAll;

	public static void main(String[] args) throws IOException {
		
		String[] lines = readFile("test-labels-predict").split("\\n");
		outputArray1 = new String[lines.length-1];
		String[] temp =new String[5];
		String[] predictedTemp =new String[2];
		for(int i=0;i<lines.length-1; i++){
			temp = lines[i+1].split("\\,");
			predictedTemp = temp[2].split("\\:");
			outputArray1[i] = predictedTemp[1];
//			System.out.println(outputArray1[i]);
		}
		
		String[] linesT = readFile("test-tweets").split("\\n");
		outputArray2 = new String[linesT.length];
		String[] tempT =new String[2];
		String[] predictedTempT =new String[2];
		for(int i=0;i<lines.length; i++){
			tempT = linesT[i].split("\\s");
			outputArray2[i] = tempT[0];
//			System.out.println(outputArray2[i]);
		}
		
//		System.out.println(outputArray1.length);
//		System.out.println(linesT.length);
		
		outputArrayAll = new String[outputArray1.length+1];
		for(int i=0;i<outputArrayAll.length-1; i++){
			outputArrayAll[i] = outputArray2[i]+" "+outputArray1[i];
			System.out.println(outputArrayAll[i]);	
		}
		outputArrayAll[outputArray1.length]="802363859373694976"+" "+"negative";
		System.out.println(outputArrayAll.length);
		
		writeFile(outputArrayAll,"test-labels-predict-real");

	}
	
	
	
	
	
	
	
	/**
	 * This method reads a txt file and transform it to a String.
	 * @param fileName (address in this program)
	 * @return the content in String format
	 * @throws IOException
	 */
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader("/Users/zizheruan/OneDrive - The University of Melbourne/Knowledge Technology/Project 2/2017S1-KTproj2-data/"+fileName+".txt"));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	
	
	
	
	/**
	 * 
	 * @param linesArray
	 */
	public static void writeFile(String[] linesArray, String fileName){
		Path file = Paths.get("/Users/zizheruan/OneDrive - The University of Melbourne/Knowledge Technology/Project 2/2017S1-KTproj2-data/"+fileName+".txt");
		List<String> linesList = Arrays.asList(/*fl, "The second line"*/linesArray);
		try {
			Files.write(file, linesList, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
	}
	
	
	
	

}

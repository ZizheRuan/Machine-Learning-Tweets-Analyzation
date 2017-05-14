import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CompareFrequency {

	
	 static final int NoInstances = 22987;
	 static final String readPositive = "countPositiveResult";
	 static final String readNeutral = "countNeutralResult";
	 static final String readNegative = "countNegativeResult";
	 static final String writeTo = "MyTrain";
	 static final int NoSelect = 10000;
	 static final int majority = 34;
	 public static ArrayList<String> selectedAttributes = new ArrayList<String>();
	 
	 public static ArrayList<String> selectedAttributesP = new ArrayList<String>();
	 public static ArrayList<String> selectedAttributesNeu = new ArrayList<String>();
	 public static ArrayList<String> selectedAttributesNeg = new ArrayList<String>();
	 
	public static void main(String[] args) throws IOException {
		

		 
		 String pLines = AttTest.readFile(readPositive);
		 String[] pParseByLine = pLines.split("\\n");
		 String[] pCount = new String[NoSelect];
		 String[] pWord = new String[NoSelect];
		 for(int i=0;i<NoSelect;i++){
			 String[] labelTemp = pParseByLine[i].split("\\s");
			 pCount[i] = labelTemp[1];
			 pWord[i] = labelTemp[0];
//			 System.out.println(pWord[i]);
		 }

		 String labelLines = AttTest.readFile(readNeutral);
		 String[] labelParseByLine = labelLines.split("\\n");
		 String[] neuCount = new String[NoSelect];
		 String[] neuWord = new String[NoSelect];
		 for(int i=0;i<NoSelect;i++){
			 String[] labelTemp = labelParseByLine[i].split("\\s");
			 neuCount[i] = labelTemp[1];
			 neuWord[i] = labelTemp[0];
		 }
		 
		 String negLines = AttTest.readFile(readNegative);
		 String[] negParseByLine = negLines.split("\\n");
		 String[] negCount = new String[NoSelect];
		 String[] negWord = new String[NoSelect];
		 for(int i=0;i<NoSelect;i++){
			 String[] labelTemp = negParseByLine[i].split("\\s");
			 negCount[i] = labelTemp[1];
			 negWord[i] = labelTemp[0];
		 }
		 
		
		 for(int i=0;i<NoSelect;i++){
			 for(int j=0;j<NoSelect;j++)
				 for(int h=0;h<NoSelect;h++){
					 if(pWord[i].equals(neuWord[j])&&neuWord[j].equals(negWord[h])&&pWord[i].length()>2){
						 int p = Integer.parseInt(pCount[i]);
						 int neu = Integer.parseInt(neuCount[j]);
						 int neg = Integer.parseInt(negCount[h]);
						 int total = p+neu+neg;
						 int rP = p*100/total;
						 int rNeu = neu*100/total;
						 int rNeg = neg*100/total;

						 if(rP>majority||rNeu>majority||rNeg>majority){
							 selectedAttributes.add(pWord[i]);
							 System.out.println(pWord[i]+"            P: "+rP+"%    neu:"+rNeu+"%    neg: "+rNeg+"%"+"    total: "+total);
						 }
						 
						 if(rP>majority){
							 selectedAttributesP.add(pWord[i]);
//							 System.out.println(pWord[i]+"            P: "+rP+"%    neu:"+rNeu+"%    neg: "+rNeg+"%"+"    total: "+total);
						 }
						 
						 if(rNeu>majority){
							 selectedAttributesNeu.add(pWord[i]);
//							 System.out.println(pWord[i]+"            P: "+rP+"%    neu:"+rNeu+"%    neg: "+rNeg+"%"+"    total: "+total);
						 }
						 
						 if(rNeg>majority){
							 selectedAttributesNeg.add(pWord[i]);
//							 System.out.println(pWord[i]+"            P: "+rP+"%    neu:"+rNeu+"%    neg: "+rNeg+"%"+"    total: "+total);
						 }
						 
						 
						 
						 
						 
					 }
				 }
		 }
		 
		 String[] selectedAttributesArray = new String[selectedAttributes.size()];
		 String[] selectedAttributesArrayP = new String[selectedAttributesP.size()];
		 String[] selectedAttributesArrayNeu = new String[selectedAttributesNeu.size()];
		 String[] selectedAttributesArrayNeg = new String[selectedAttributesNeg.size()];
		 selectedAttributesArray = selectedAttributes.toArray(selectedAttributesArray);
		 selectedAttributesArrayP = selectedAttributesP.toArray(selectedAttributesArrayP);
		 selectedAttributesArrayNeu = selectedAttributesNeu.toArray(selectedAttributesArrayNeu);
		 selectedAttributesArrayNeg = selectedAttributesNeg.toArray(selectedAttributesArrayNeg);
		 writeFile(selectedAttributesArray,"selectedAttributes");
		 writeFile(selectedAttributesArrayP,"selectedAttributesP");
		 writeFile(selectedAttributesArrayNeu,"selectedAttributesNeu");
		 writeFile(selectedAttributesArrayNeg,"selectedAttributesNeg");
		 System.out.println(pParseByLine.length+"\n"+labelParseByLine.length+"\n"+negParseByLine.length+"\n");
		 
		 
		 
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

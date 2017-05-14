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

 
 /**
  * Write the MyTrain.txt and MyDev.txt
  *
  * @author FracPete
  */
 public class AttTest {
	 
//	 static final int NoAttributes = 20;
	 static int NoAttributes;
	
//	 static final int NoInstances = 22987;
//	 static final String readTweets = "train-tweets";
//	 static final String readLabels = "train-labels";
//	 static final String writeTo = "MyTrain";
	 
	 static final String readTweets = "dev-tweets";
	 static final String readLabels = "dev-labels";
	 static final String writeTo = "MyDev";
	 static final int NoInstances = 4926;
	 
//	 static String[][] valueMatrix;
	 static String[][] valueMatrix;
	 
		 
	 public static void main(String[] args)throws IOException{
		 
		 List<String> outputLines;
		 String[] linesArray;
		 int countTrump=0;
		 
		 String[] selectedAttributes = readFile("selectedAttributes").split("\\n");
		 NoAttributes = selectedAttributes.length;
		 valueMatrix = new String[NoInstances+1][NoAttributes];
		 System.out.println(NoAttributes);
		 
		 String labelLines = readFile(readLabels);
		 String[] labelParseByLine = labelLines.split("\\n");
		 String[] labels = new String[NoInstances];
		 valueMatrix[0][NoAttributes-1] = "sentiment";
		 for(int i=0;i<labelParseByLine.length;i++){
			 String[] labelTemp = labelParseByLine[i].split("\\s");
			 labels[i] = labelTemp[1];
			 valueMatrix[i+1][NoAttributes-1] = labels[i];//make room for header
		 }
		 
		 		 
		 String tweetLines = readFile(readTweets);
		 String[] tweetParseByLine = tweetLines.split("\\n");
		 //monday,sundan,tomorrow, like, we,saturday,well,good,tonight,happy,ice,
		 //should,white,back,new,night
//		 addNominalAttribute("rich",0,valueMatrix,tweetParseByLine);//注意空格;0-(No.-2)
//		 addNominalAttribute("love",1,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" mosuloffensive  ",2,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" fuck ",3,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" pgachampionship ",4,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" birthday ",5,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" shit ",6,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" steal ",7,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" stupid ",8,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" dog ",9,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" excited ",10,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" thank ",11,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" awesome ",12,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" happy ",13,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" fun ",14,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" gucci ",15,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" oliseh ",16,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" tonight ",17,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" eagles ",18,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" kasich ",19,valueMatrix,tweetParseByLine);
//		 addNominalAttribute(" madrid ",20,valueMatrix,tweetParseByLine);

		 for(int i=0;i<selectedAttributes.length-2;i++){
			 addNominalAttribute(" "+selectedAttributes[i]+" ",i,valueMatrix,tweetParseByLine);
		 }
		 
		 
		 //排出CSV格式的矩阵，准备write
		 linesArray = new String[NoInstances];
		 for(int i=0;i<NoInstances;i++){
			 linesArray[i] = valueMatrix[i][0]+",";//column 1#
			 for(int j=1;j<NoAttributes-2;j++){//column 2-倒数第二个#
				 linesArray[i] = linesArray[i]+valueMatrix[i][j]+",";
			 }
			 linesArray[i] = linesArray[i]+valueMatrix[i][NoAttributes-1]+"\n";//column last one#
		 }
		 
		 writeFile(linesArray, writeTo);	
//		 writeFile(linesArray, writeTo);
		 System.out.println("writting finished!");
		 

		 
		 
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
		
		
		/**
		 * 
		 * @param attributeName
		 * @param index
		 * @param valueMatrix
		 * @param tweetParseByLine
		 */
		 public static void addNominalAttribute(String attributeName, int index, String[][] valueMatrix, String[] tweetParseByLine){//index range from 0-(NoAttributes-2)
			 //contain "trump"->T. Otherwise ->F
			 //A pattern can be abstracted!	
			 if (index<0||index>(NoAttributes-2)){
				 System.out.println("assign attribute out of index, extend the No. of Attributes");
			 }
			 else{
				 valueMatrix[0][index] = attributeName;
				 for (int  i=0; i<tweetParseByLine.length; i++){
					 tweetParseByLine[i] = tweetParseByLine[i].toLowerCase(); 
					 if(tweetParseByLine[i].contains(attributeName)){//The name is special, so I didn't place spaces before and after the word.
						 valueMatrix[i+1][index]="T";//assign in matrix,留出一行给header
//				         String[] elements = tweetParseByLine[i].split("[^a-zA-Z0-9]+");  
//				         int count = 0;  
//				         for(String str:elements){
//				        	 if((" "+str+" ").equals(attributeName)){
//				        		 count+=1;
//				        	 }
//				         }
//				         System.out.println(count);
//				         valueMatrix[i+1][index]=Integer.toString(count);//assign in matrix,留出一行给header
					 }
					 else
						 valueMatrix[i+1][index]="F";
//						 valueMatrix[i+1][index]="0";
				 }
			 }
		 }
	 
	 
	 
	 
	 
 }
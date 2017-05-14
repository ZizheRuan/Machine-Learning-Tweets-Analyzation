import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAlgorithm {

	public static void main(String[] args) throws IOException {
		ArrayList<String> predictSentiment = new ArrayList<String>();
		String[] tweets = readFile("dev-tweets").split("\\n");
		String[] pWord = readFile("selectedAttributesP").split("\\n");
		String[] neuWord = readFile("selectedAttributesNeu").split("\\n");
		String[] negWord = readFile("selectedAttributesNeg").split("\\n");
		double correctCount = 0;
		double totalCount = 0;
		for(String str:tweets){
			int pCount=0;
			int neuCount=0;
			int negCount=0;
			String[] oneTweetTemp = str.split("\\s");
			for(String words:oneTweetTemp){
				for(String p:pWord){
					if(words.equals(p)){//positive
						pCount+=12;
						break;
					}
				}
				for(String neu:neuWord){
					if(words.equals(neu)){
						neuCount+=1;
						break;
					}
				}
				for(String neg:negWord){//negative
					if(words.equals(neg)){
						negCount+=4;
						break;
					}
				}
			}
			System.out.println(oneTweetTemp[0]);
			System.out.println(pCount+" "+neuCount+" "+negCount);
			if(pCount>neuCount&&pCount>negCount){
				predictSentiment.add(oneTweetTemp[0]+" "+"positive");
			}
			else if(negCount>neuCount&&negCount>pCount){
				predictSentiment.add(oneTweetTemp[0]+" "+"negative");
			}
			else{//include equals situation
				predictSentiment.add(oneTweetTemp[0]+" "+"neutral");
			}
//			if(negCount>0){
//				predictSentiment.add(oneTweetTemp[0]+" "+"negative");
//			}
//			else if(pCount>0){
//				predictSentiment.add(oneTweetTemp[0]+" "+"positive");
//			}
//			else{//include equals situation
//				predictSentiment.add(oneTweetTemp[0]+" "+"neutral");
//			}
		}
		String[] predictSentimentArray = new String[predictSentiment.size()];
		predictSentimentArray = predictSentiment.toArray(predictSentimentArray);
		writeFile(predictSentimentArray, "dev-labels-predict");

		String[] devLabels = readFile("dev-labels").split("\\n");
		String[] devLabelsPredict = readFile("dev-labels-predict").split("\\n");
		
		for(int i=0; i<devLabels.length;i++){boolean correct = false;
			String[] devLabelLine = devLabels[i].split("\\s");
			String[] devLabelPredictLine = devLabelsPredict[i].split("\\s");
			if(devLabelLine[0].equals(devLabelPredictLine[0])&&devLabelLine[1].equals(devLabelPredictLine[1])){
				correctCount+=1;correct = true;
			}
//			System.out.println(devLabelLine[1]+" "+devLabelPredictLine[1]+" "+correct);
		}
		totalCount=devLabels.length;
		double ratio = correctCount*100/totalCount;
		System.out.println("correctCount: "+correctCount+" totalCount: "+totalCount+" correctRatio: "+String.format("%.2f", ratio)+"%");
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

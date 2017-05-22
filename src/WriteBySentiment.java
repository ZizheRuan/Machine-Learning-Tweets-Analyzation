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
 * the WriteBySentiment.java reads both train-tweets.txt and train-labels.txt, 
 * then combine the tweets and their sentiments, divide them by sentiment, 
 * write to 3 different txt files(countPositive.txt, countNeutral.txt, countNegative.txt). 
 * In each output file, all of the tweets are belong to one the same sentiment.
 * @author zizheruan
 *
 */
public class WriteBySentiment {

	 static final int NoInstances = 22987;
	 static final String readTweets = "train-tweets";
	 static final String readLabels = "train-labels";
//	 static final String writeTo = "MyTrain";
	 static int NoPositiveTweets;
	 static int NoNeutralTweets;
	 static int NoNegativeTweets;
	 
	 
	public static void main(String[] args) throws IOException {
		
		ArrayList<String> tweetPositive = new ArrayList<String>();
		ArrayList<String> tweetNeutral = new ArrayList<String>();
		ArrayList<String> tweetNegative = new ArrayList<String>();
		
		 String tweetLines = AttTest.readFile(readTweets);
		 String[] tweetParseByLine = tweetLines.split("\\n");
		 
		 String labelLines = AttTest.readFile(readLabels);
		 String[] labelParseByLine = labelLines.split("\\n");
		 String[] labels = new String[NoInstances];
		 for(int i=0;i<labelParseByLine.length;i++){
			 String[] labelTemp = labelParseByLine[i].split("\\s");
			 labels[i] = labelTemp[1];
//			 System.out.println(labels[i]);
		 }
		 

		 for(int i=0; i<labels.length; i++){
			 switch(labels[i]){
			 case "positive": 
				 String tweetPositiveTemp = tweetParseByLine[i];
				 tweetPositive.add(tweetPositiveTemp);
//				 System.out.println(tweetParseByLine[i]+"positiveAdded");
				 break;
			 case "neutral":
				 String tweetNeutralTemp = tweetParseByLine[i];
				 tweetNeutral.add(tweetNeutralTemp);
//				 System.out.println(tweetParseByLine[i]+"neuAdded");
				 break;
			 case "negative":
				 String tweetNegativeTemp = tweetParseByLine[i];
				 tweetNegative.add(tweetNegativeTemp);
				 break;
			default:System.out.println("wrong:"+labels[i]);
				 break;
			 }
		 }
		 
		 
		 
//		 for(int i=0; i<tweetPositive.size(); i++){
//			 tweetPositiveArray[i]=tweetPositiveTemp;
//		 }

		 String[] tweetPositiveArray = new String[tweetPositive.size()];
		 String[] tweetNeutralArray = new String[tweetNeutral.size()];
		 String[] tweetNegativeArray = new String[tweetNegative.size()];
		 tweetPositiveArray = tweetPositive.toArray(tweetPositiveArray);
		 tweetNeutralArray = tweetNeutral.toArray(tweetNeutralArray);
		 tweetNegativeArray = tweetNegative.toArray(tweetNegativeArray);
		 
		 AttTest.writeFile(tweetPositiveArray, "countPositive");
		 AttTest.writeFile(tweetNeutralArray, "countNeutral");
		 AttTest.writeFile(tweetNegativeArray, "countNegative");
		 
		 NoPositiveTweets = tweetPositive.size();
		 NoNeutralTweets = tweetNeutral.size();
		 NoNegativeTweets = tweetNegative.size();
		 
		 System.out.println("writting finished");
		 System.out.println("Positive tweets: "+tweetPositive.size());
		 System.out.println("Neutral tweets: "+tweetNeutral.size());
		 System.out.println("Negative tweets: "+tweetNegative.size());
		 
		 
		 
	}

}

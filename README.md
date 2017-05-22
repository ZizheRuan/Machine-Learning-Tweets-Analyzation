# Machine-Learning-Tweets-Analyzation

The project analyses tweets and preprocesses them to be ready for machine learning with Weka.

The analyse is based on sentiments of the tweets.

The project is created for subject COMP90049_2017_SM1: Knowledge Technologies.
Zizhe Ruan is the only author of the project.


There are 6 java files. 4 of them are used to generate txt documents in a specific format to be tested in Weka. The makeTestPrediction.java is used for generating the output on test.txt. The MyAlgorithm.java is used to make prediction and calculate accuracy on my own method instead of using Weka.

The procedure of generating files for Weka:
First, the WriteBySentiment.java reads both train-tweets.txt and train-labels.txt, then combine the tweets and their sentiments, divide them by sentiment, write to 3 different txt files(countPositive.txt, countNeutral.txt, countNegative.txt). In each output file, all of the tweets are belong to one the same sentiment.

Second, the CountOccurrence.java[1] reads the three files generated in step 1 and calculates the occurrence of words in them. It also sort the words by occurrence and output the most frequently used words to three txt files: countPositiveResult.txt, countNeutralResult.txt, countNegativeResult.txt. The number of selection can be tuned through changing the “NoSelect” static int.

Next, in CompareFrequency.java, set the number of word occurrence threshold(NoSelect) and sentiment incline threshold(majority), the program will list the words that have obvious sentiment incline (over the “majority”). Then the selected words are write to selectedAttributes.txt, selectedAttributesP.txt,selectedAttributesNeu.txt,selectedAttributesNeg.txt to be further processed.

Last step is to generate the attribute-instance matrix for Weka to read. The program AttTest.java reads training data and dev-data seperately, detect the occurrence of the words in the attributes files we selected, then fill up the matrix. It eventually output MyTrain.txt and MyDev.txt. These two txt files are actually wrote in CSV format, so we just need to change the name of the txt files to csv. Then it can be imported to Weka to be tested. If in the “test set” section, the MyDev.csv can not be properly read, convert the MyDev.csv to MyDev.arff, then it will work. Note you need to comment/comment out between train and dev at the beginning of the program to generate the two files.

MyAlgorithm.java contains my own method, it can predict sentiments and clearly print out the accuracy.

MakeTestPredictionList.java is used to generate the prediction outcome on the test-tweets, in the format correspondence to dev-label.txt and train-label.txt.


[1]From Janice’s blog: http://blog.csdn.net/u014204432/article/details/40348839


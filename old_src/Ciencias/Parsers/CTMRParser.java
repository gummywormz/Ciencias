package Ciencias.Parsers;

import Ciencias.Core.AnsweredQuestion;
import Ciencias.GUI.MainWindow;
import Ciencias.Managers.TagMan;
import java.util.ArrayList;
import java.io.*;

/**
 * Parses CTMR files
 * @author Paul Alves
 */


public class CTMRParser {
    
    private static String testName;
    private static int enableUserNames;
    private static int avgScore;
    private static int highScore;
    private static int lowScore;
    private static int mostMissed;
    private static ArrayList<Integer> graphPosses;
    private static ArrayList<AnsweredQuestion> questions;
    //question stuffs
    private static String qText;
    private static int qNum;
    private static String correctAnswer;
    private static String qStyle;
    private static String qTag; //remember to add to tagman >_>
    private static String qImg;
    private static ArrayList<String[]> answers;
    
    /**
     * Parses the specified CTMR file
     * @param ctmr The CTMR file to parse
     * @throws java.io.IOException
     */
    public static void parseCTMR(File ctmr) throws IOException{
    graphPosses = new ArrayList<>();
    questions = new ArrayList<>();
    answers = new ArrayList<>();
    avgScore = 0;
    highScore = 0;
    lowScore = 0;
    mostMissed = 0;
    qNum = 0;
    try{
    BufferedReader in = new BufferedReader(new FileReader(ctmr));
    String ln = in.readLine();
    
    if(!ln.equals("[START_OVERVIEW]")){
    MainWindow.throwError("Invalid or altered CTMR file!");
    }
    
    ln = in.readLine();
    testName = ln.split("=")[1];
    ln=in.readLine();
    enableUserNames=Integer.parseInt(ln.split("=")[1]);
    ln=in.readLine();
    avgScore=Integer.parseInt(ln.split("=")[1]);
    ln=in.readLine();
    highScore=Integer.parseInt(ln.split("=")[1]);
    ln = in.readLine();
    lowScore=Integer.parseInt(ln.split("=")[1]);
    while((ln=in.readLine()).startsWith("QUESTIONGRAPHPOS=")){
    graphPosses.add(Integer.parseInt(ln.split("=")[1]));
    }
    mostMissed=Integer.parseInt(ln.split("=")[1]);
    ln = in.readLine(); // get rid of [END_OVERVIEW]
    //java really needs an until loop >_>
    while((ln = in.readLine()) != null){//[START_QUESTION]
    ln=in.readLine(); //QUESTIONSTYLE
    qStyle=ln.split("=")[1];
    ln=in.readLine(); //IMAGE
    qImg=ln.split("=")[1];
    ln=in.readLine(); //QUESTIONTEXT
    qText=ln.split("=")[1];
    ln=in.readLine(); //qnum
    qNum=Integer.parseInt(ln.split("=")[1]);
    ln=in.readLine(); //correctanswer
    correctAnswer=ln.split("=")[1];
    //begin answer stuffs
    while(!(ln=in.readLine()).startsWith("TAG=")){//answerchoice
    String[] tempAns = new String[3];
    tempAns[0] = ln.split("=")[1];
    ln = in.readLine();
    tempAns[1] = ln.split("=")[1];
    ln = in.readLine();
    try{tempAns[2] = ln.split("=")[1];}catch(ArrayIndexOutOfBoundsException e){tempAns[2] = "None";}
    answers.add(tempAns);
    }
    qTag = ln.split("=")[1];
    TagMan.addTag(qNum, qTag);
    questions.add(new AnsweredQuestion(qText,qImg,qTag,qNum,correctAnswer,qStyle,answers));
    answers.clear();
    ln=in.readLine(); //[END_QUESTION]
    }
    }
    catch(FileNotFoundException e){
    MainWindow.throwError("Unable to find the CTMR file!");
    }
    }
    
    /**
     * Returns the test name.
     * @return the test name.
     */
    public static String getTestName(){
    return testName;
    }
    
    /**
     * Returns the ENABLEUSERNAMES setting of this test
     * @return the ENABLEUSERNAMES setting of this test
     */
    public static int getEnableUserNames(){
    return enableUserNames;
    }
    
    /**
     * Returns the average score of the test
     * @return the average score of the test
     */
    public static int getAverageScore(){
    return avgScore;
    }
    
    /**
     * Returns the lowest score of this test
     * @return the lowest score of this test
     */
    public static int getLowestScore(){
    return lowScore;
    }
    
    /**
     * Returns the highest score of this test
     * @return the highest score of this test
     */
    public static int getHighestScore(){
    return highScore;   
    }
    
    /**
     * Returns the most missed question number
     * @return the most missed question number
     */
    public static int getMostMissed(){
    return mostMissed;
    }
    
    /**
     * Returns the number of students who took the test.
     * @return The number of students who took the test.
     */
    public static int getNumberOfStudents(){
    int tempNum = 0;
    for(String[] s: questions.get(0).getAnswers()){
    tempNum+=Integer.parseInt(s[1]);
    }
    return tempNum;
    }
    
    /**
     * Gets the QUESTIONGRAPHPOS (how many students answered correctly) of the specified question
     * @param q The question to get (question number - 1)
     * @return The questiongraphpos of the question
     */
    public static int getQuestionGraphPos(int q){
    return graphPosses.get(q);
    }
    
    /**
     * Gets the specified Answered Question 
     * @param q The index of the question (question number - 1)
     * @return The answered question
     */
    public static AnsweredQuestion getQuestion(int q){
    return questions.get(q);
    }
    
    /**
     * Returns a list of questions that have the specified tag
     * @param tag The tag to get
     * @return An array list of questions matching the tag.
     */
    public static ArrayList<AnsweredQuestion> getTaggedQuestions(String tag){
    ArrayList<AnsweredQuestion> taggedQuestions = new ArrayList<>();
    for(AnsweredQuestion q : questions){
    if(q.getTag().equals(tag)){
    taggedQuestions.add(q);
    }
    }
    return taggedQuestions;
    }
    
    /**
     * Returns the list of questions
     * @return The list of questions
     */
    public static ArrayList<AnsweredQuestion> getQuestions(){
    return questions;
    }
    
    }

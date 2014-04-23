package Ciencias.Core;

import Ciencias.Managers.QArray;
import java.util.ArrayList;

/**
 * A modification to the Question class for use in the results renderer.
 * @author Paul Alves
 */


public class AnsweredQuestion {
    
    private String qTag;
    private String qStyle;
    private int qNum;
    private String correctAnswer;
    private String qImg;
    private String qText;
    private ArrayList<String[]> answers;
    
    /**
     * Constructs an answered question object
     * @param q The question to base this object on
     * @param cAns The index of the correct answer
     * @param ansList The list of answers. Index 0 is the answer itself, index 1 is how many students picked the answer, and index 2 is a comma separated list of student's names who picked that answer
     */
    public AnsweredQuestion(Question q, String cAns, ArrayList<String[]> ansList){
       qTag=q.getTag();
       qStyle=q.getType();
       qNum=q.getQuestionNumber();
       qImg=q.getImage();
       qText=q.getText();
       correctAnswer=cAns;
       answers = new ArrayList<>(ansList); 
    }
    
    /**
     * Creates an answered question object without a Question object available.
     * @param text The question's text
     * @param img The question's image.
     * @param tag The question's tag
     * @param num The question's number
     * @param correctAns The correct answer to the question
     * @param type The type of question
     * @param ans An array list containing answers. (index 0 is the answer itself, index 1 is how many students picked the answer, and index 2 is a comma separated list of student's names who picked that answer)
     */
    public AnsweredQuestion(String text, String img, String tag, int num, String correctAns, String type, ArrayList<String[]> ans){
    qTag=tag;
    qStyle=type;
    qNum=num;
    qImg=img;
    qText=text;
    correctAnswer=correctAns;
    answers = new ArrayList<>(ans); 
    }
    
    /**
     * Returns the tag of this question
     * @return The tag of this question
     */
    public String getTag(){
    return qTag;
    }
    
    /**
     * Returns the type of question
     * @return the type of question
     */
    public String getType(){
    return qStyle;
    }
    
    /**
     * Returns the question number
     * @return the question number
     */
    public int getQuestionNumber(){
    return qNum;
    }
    
    /**
     * Returns the correct answer
     * @return the correct answer
     */
    public String getCorrectAnswer(){
    return correctAnswer;
    }
    
    /**
     * Returns the image associated with this question
     * @return the image associated with this question
     */
    public String getImage(){
    return qImg;
    }
    
    /**
     * Returns the text of this question
     * @return the text of this question
     */
    public String getText(){
    return qText;
    }
    
    /**
     * Returns the arraylist of answers
     * @return the arraylist of answers
     */
    public ArrayList<String[]> getAnswers(){
    return answers;
    }
    
    /**
     * Returns the specified answer
     * @param i The index of the answer
     * @return the specified answer
     */
    public String getAnswer(int i){
    return answers.get(i)[0];
    }
    
    /**
     * Returns the names of the students who chose the specified answer
     * @param i The index of the answer
     * @return the names of the students who chose the specified answer
     */
    public String getStudentsWhoAnswered(int i){
    return answers.get(i)[2];
    }
    
    /**
     * Returns the number of students who chose the specified answer
     * @param i The index of the answer
     * @return the number of students who chose the specified answer
     */
    public int getNumOfStudentsWhoAnswered(int i){
    return Integer.parseInt(answers.get(i)[1]);
    }
    
    /**
     * Returns the amount of students who chose the correct answer 
     * NOTE: This method is only used for the consolidation process. To get question graph posses from a CTMR file, use CTMRParser.getQuestionGraphPos
     * @return the amount of students who chose the correct answer
     * @see Ciencias.Parsers.CTMRParser#getQuestionGraphPos(int) getQuestionGraphPos
     */
    public int getQuestionGraphPosition(){
    if(this.getType().equals("FREERESPONSE")){return -1;}
    if(this.getType().equals("WORDBANK") || QArray.getQuestion(qNum-1).getMaxAnswers() > 1){
    for(int i = 0; i < this.answers.size(); i++){
    if(getAnswer(i).equals(correctAnswer)){return getNumOfStudentsWhoAnswered(i); }
    else{return 0;}
    }}
    if(this.getType().equals("MULTIPLECHOICE")){
    return getNumOfStudentsWhoAnswered(Integer.parseInt(getCorrectAnswer()));}
    return 0;
    }
    
    @Override
    public String toString(){
    return "Question " + qNum;
    }
    
    }

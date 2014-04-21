package Ciencias.Core;

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
     * @return the amount of students who chose the correct answer
     */
    public int getQuestionGraphPosition(){
    if(this.getType().equals("FREERESPONSE")){return -1;}
    if(this.getType().equals("WORDBANK") || this.getType().equals("MULTIPLECHOICE")){
    for(int i = 0; i < this.answers.size(); i++){
    if(answers.get(i)[0].equals(correctAnswer)){return Integer.parseInt(answers.get(i)[1]); }
    }
    }
       return 0; 
    }
    
    }

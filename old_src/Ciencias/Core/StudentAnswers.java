

package Ciencias.Core;

import java.util.ArrayList;

/**
 * Objective representation of student's answers
 * @author Paul Alves
 */


public class StudentAnswers {
    private String userName;
    private ArrayList<String[]> answers;
    private int periodNum;
    
    /**
     * Constructs a StudentAnswers object
     * @param user The user name of this student
     * @param ans The answers this student has chosen as an ArrayList of String arrays, with the first index being the answer,and the second being the question type.
     * @param pd The period of this student
     */
    public StudentAnswers( String user, ArrayList<String[]> ans, int pd){
    answers = new ArrayList<>(ans);
    userName = user;
    periodNum = pd;
    }
    
    /**
     * Constructs a StudentAnswers object to be used as the answer key.
     * @param a The correct answers
     */
    public StudentAnswers(ArrayList<String[]> a){
    answers = new ArrayList<>(a);
    periodNum = -1;
    userName = "Key";
    }
    
    /**
     * Returns the user name of the student
     * @return The user name of the student
     */
    public String getName(){
    return userName;
    }
    
    /**
     * Returns the type of question at the specified index
     * @param i The index of the question
     * @return The type of question
     */
    public String getQuestionTypeAtIndex(int i){
    return answers.get(i)[1];
    }
    
    /**
     * Returns the answer to the specified question (question number - 1)
     * @param i The index of the question
     * @return The answer to the question
     */
    public String getAnswerAtIndex(int i){
    return answers.get(i)[0];
    }
    
    /**
     * Returns the size of the student answer array
     * @return The size of the student answer array
     */
    public int getSize(){
    return answers.size();
    }
    
    /**
     * Returns the period number of the student
     * @return The period number of the student
     */
    public int getPeriod(){
    return periodNum;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ciencias.Managers;
import Ciencias.Core.Question;
import java.util.ArrayList;

/**
 * Manages user answer choices.
 * @author Paul Alves
 */
public class UserAnswerManager {
    private static ArrayList<String> ansArray;
    
    /**
     * Initializes the Answer Array
     */
    public static void init()
    {
        ansArray = new ArrayList<>(QArray.getIndexes());
        for( int i = 0; i < QArray.getIndexes()+1; i++){
        ansArray.add(null);//who likes hacks i like hacks
        }
    }
    
    /**
     * Adds the answer to the array
     * @param q The answer to give this array
     * @param index the index to add the answer to
     */
    public static void add(String q,int index)
    {
        ansArray.set(index,q);
    }
    
    /**
     * Returns the entire array of answers
     * @return the whole array of answers as an arraylist
     */
    public static ArrayList<String> getList()
    {
        return ansArray;
    }
    
    /**
     * Returns the full answer to a question (what would be written to a CTMA file)
     * @param index the index of the answer
     * @return the answer given
     */
    public static String getFullAnswer(int index)
    {
        
        if(ansArray.get(index) == null){return "Null";}
        return ansArray.get(index);
        
    }
/**
 * Sorts the array list of answers in order of the question number.
 * @return A newly sorted arraylist of the answers 
 */
public static ArrayList<String> sortAnswers(){
ArrayList<String> ansArray2 = new ArrayList<>();
for(int i = 0; i<ansArray.size(); i++){
ansArray2.add(null);
}
for(int i = 0; i<ansArray2.size(); i++){
String c = ansArray.get(i);
String[] split = c.split("=");
int index = Integer.parseInt(split[0])-1;
ansArray2.set(index,c);
}
return ansArray2;
}

    /**
     * Returns the index of the specified answer (what is after the = sign in a CTMA file)
     * @param index Index of the answer to get
     * @return A String containing the selected answer index
     */
 public static String getAnswerIndex(int index){
    if(getFullAnswer(index) == null || !getFullAnswer(index).contains("=")){return "Null";}
    String[] ans =  getFullAnswer(index).split("=");
    return ans[1];
}

    /**
     * Checks if there are any null values in the answer arraylist (if not all questions have been answered)
     * @return True is there is a null value, false if there isn't.
     */
public static boolean hasNull(){
for(int i = 0; i<ansArray.size(); i++ ){
if(ansArray.get(i) == null || ansArray.get(i).endsWith("=Null")){return true;}
} 
return false;
}

    /**
     * Returns an array list of the unanswered questions.
     * @return An array list of questions that have no answer
     */
public static ArrayList<Question> getUnansweredQuestions(){
ArrayList<Question> unansweredQuestions = new ArrayList<>();

for (int i = 0; i< ansArray.size(); i++){
if(ansArray.get(i) == null || ansArray.get(i).endsWith("=Null")){
unansweredQuestions.add(QArray.getQuestion(i));
}
}
return unansweredQuestions;
}

}

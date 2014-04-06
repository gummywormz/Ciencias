package Ciencias.Managers;

import Ciencias.Core.Question;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Manages the array of questions
 * 
 * @author Paul Alves
 * @version 2/21/14
 */
public class QArray
{
    private static ArrayList<Question> qArray;
    
    /**
     * Initializes the QArray
     */
    public static void init()
    {
        qArray = new ArrayList<>();
    }
    
    /**
     * Adds the question object to the array
     * @param q The question object to give this array
     */
    public static void add(Question q)
    {
        qArray.add(q);
    }
    
    /**
     * Returns the entire array of questions
     * @return the whole array of questions as an arraylist
     */
    public static ArrayList<Question> getList()
    {
        return qArray;
    }
    
    /**
     * Returns the specified question
     * @param index the index of the question (always question number - 1)
     * @return the question at the index
     */
    public static Question getQuestion(int index)
    {
        return qArray.get(index);
    }
    
     /**
     * Returns the amount of indexes in the QArray
     * @return the number of indexes of the QArray
     */
    public static int getIndexes()
    {
        return qArray.size()-1;
    }
    
    /**
     *Randomizes the QArray.
     */
    public static void randomize()
    {
       //long seed = System.nanoTime();
       Collections.shuffle(qArray/*,new Random(seed)*/); 
    }
    
    /**
     * Returns the index in the QArray of the specified question object
     * @param q The question to find
     * @return The index in the QArray of the question. -1 otherwise
     */
    public static int getQuestionIndex(Question q){
        for(int i = 0; i<qArray.size(); i++){
        if(q.getQuestionNumber() == qArray.get(i).getQuestionNumber()){
        return i;
        }
        }
        
        return -1;
    }
    
    }
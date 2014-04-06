/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ciencias.Managers;

import java.util.ArrayList;

/**
 * Manages the word bank. 
 * @author Paul Alves
 * @version 3/15/14
 */
public class WordBankMan {
    
    private static ArrayList <String []> usedAnswers;
    private static ArrayList <String> wordBank;
    private static ArrayList <String> pristineWordBank;
    
    /**
     * Initializes the word banks.
     */
    public static void init()
    {
        usedAnswers=new ArrayList<>();
        wordBank=new ArrayList<>();
        pristineWordBank = new ArrayList<>();
    }
    
    /**
     * Adds a word to the word bank.
     * @param word the word to add
     */
    public static void add(String word)
    {
        wordBank.add(word);
        pristineWordBank.add(word);
    }
    
    /**
     * Uses a word specified in the word bank.
     * @param word the word to use
     * @param qNum The number of the question in which this word was used. 
     */
    public static void useWord(String word, int qNum)
    {
        for (int i = 0; i< wordBank.size(); i++){
        String wordCompare = wordBank.get(i);
        if(wordCompare.equals(word)){wordBank.remove(i);
        String [] fullWord = new String[2];
        fullWord[0] = wordCompare;
        fullWord[1] = "" + qNum;
        usedAnswers.add(fullWord);
        break;
        }
        }
    }
    
    /**
     * Returns the question number a word was used in., - 1 if it was not used
     * @param word Word to search for.
     * @return The question number the word was used in.
     */
    public static int findQuestionNumberOfWord(String word){
        for(int i = 0; i<usedAnswers.size(); i++){
            String [] findQ = usedAnswers.get(i);
        if(findQ[0].equals(word) ){return Integer.parseInt(findQ[1]);}
        }
        
    return -1;
    }
    
    /**
     * Unuses a word 
     * @param qNum The question number of the word to unuse.
     */
    public static void unUseWord(int qNum){
        for(int i = 0; i<usedAnswers.size(); i++){
            String [] findQ = usedAnswers.get(i);
            int actualInt = Integer.parseInt(findQ[1]);
        if(actualInt == qNum ){
        wordBank.add(findQ[0]);
        usedAnswers.remove(i);
        break;
        }
        }
    }
    
    /**
     * Returns the current word bank.
     * @return The word bank
     */
    public static ArrayList<String> getWordBank()
    {
    return wordBank;
    }

    public static ArrayList<String> getPristineWordBank() {
        return pristineWordBank;
    }
        
    }
/* Copyright (c) 2014 Paul Alves. All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

Redistributions in any form must be accompanied by information on how to obtain complete source code for this software and any accompanying software that uses this software. The source code must either be included in the distribution or be available for no more than the cost of distribution plus a nominal fee, and must be freely redistributable under reasonable conditions. For an executable file, complete source code means the source code for all modules it contains. It does not include source code for modules or files that typically accompany the major components of the operating system on which the executable file runs.

If any files are modified, you must cause the modified files to carry prominent notices stating that you changed the files and the date of any change.

THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT, ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OF THIS SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package Ciencias.Managers;

import Ciencias.Core.Question;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the test editor.
 * @author Paul Alves
 */


public class TestEditorManager {
    private static int randomize = 0;
    private static int appendPd = 0;
    private static int enableUserNames = 0;
    private static String testName;
    private static String driveFolderRoot;
    private static String driveSubFolder;
    private static String wordBank;
    private static ArrayList<Question> testQArray;
    
    /**
     * Sets the value of ENABLERANDOMIZATION
     * @param val The value to set
     */
    public static void setRandomize(int val){
    randomize = val;
    }
    
    public static void initQArray()
    {
     testQArray=new ArrayList<>();   
    }
    
    /**
     * Sets the value of ENABLERANDOMIZATION
     * @param val The value to set
     */
    public static void setPeriodAppend(int val){
    appendPd = val;
    }
    
    /**
     * Sets the value of ENABLEUSERNAMES
     * @param val The value to set
     */
    public static void setUserNames(int val){
    enableUserNames = val;
    }
    
    /**
     * Sets the value of TESTNAME
     * @param val The value to set
     */
    public static void setTestName(String val){
    testName = val;
    }
    
    /**
     * Sets the value of DRIVEFOLDERROOT
     * @param val The value to set
     */
    public static void setDriveFolderRoot(String val){
    driveFolderRoot = val;
    }
    
    /**
     * Sets the value of DRIVESUBFOLDER
     * @param val The value to set
     */
    public static void setDriveSubFolder(String val){
    driveSubFolder = val;
    }
    
    /**
     * Sets the value of WORDBANK
     * @param bank The value to set
     */
    public static void setWordBank(String bank)
    {
        wordBank = bank;
    }
    
    /**
     * Adds a question to the array.
     * @param q The question to add
     */
    public static void addQuestion(Question q){
    testQArray.add(q);
    }
    
    /**
     * Removes the question at the index.
     * @param index The index to remove.
     */
    public static void removeQuestion(int index){
    testQArray.remove(index);
    }
    
    /**
     * Fixes the question numbers.
     */
    public static void fixQNums(){
    for(int i = 0; i < testQArray.size(); i++){
    Question q = testQArray.get(i);
    q.setQuestionNumber(i+1);
    }
    }
    
    /**
     * Returns a question at the given index.
     * @param index
     * @return The question at the given index.
     */
    public static Question getQuestion(int index){
    return testQArray.get(index);
    }
    
    /**
     * Swaps 2 question in the array.
     * @param i An index to swap
     * @param j An index to swap
     */
    public static void swapQuestions(int i, int j){
    Collections.swap(testQArray, i, j);
    }
    
    /**
     * Returns the QArray
     * @return The QArray
     */
    public static ArrayList<Question> getQArray(){
    return testQArray;
    }
    
    /**
     * Returns the value of ENABLERANDOMIZATION
     * @return the value of ENABLERANDOMIZATION
     */
    public static int getRandomization()
    {
        return randomize;
    }
    
     /**
     * Returns the value of APPENDPERIOD
     * @return the value of APPENDPERIOD
     */
    public static int getAppendPeriod()
    {
        return appendPd;
    }
    
     /**
     * Returns the value of ENABLEUSERNAMES
     * @return the value of ENABLEUSERNAMES
     */
    public static int getEnableUserNames()
    {
        return enableUserNames;
    }
    
     /**
     * Returns the value of DRIVEFOLDERROOT
     * @return the value of DRIVEFOLDERROOT
     */
    public static String getDriveFolderRoot(){
    return driveFolderRoot;
    }
    
     /**
     * Returns the value of DRIVESUBFOLDER
     * @return the value of DRIVESUBFOLDER
     */
    public static String getDriveSubFolder(){
    return driveSubFolder;
    }
    
     /**
     * Returns the value of TESTNAME
     * @return the value of TESTNAME
     */
    public static String getTestName(){
    return testName;
    }
    
     /**
     * Returns the value of WORDBANK
     * @return the value of WORDBANK
     */
    public static String getWordBank(){
    return wordBank;
    }
    
    }


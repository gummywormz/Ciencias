
package Ciencias.Parsers;

import Ciencias.Core.StudentAnswers;
import Ciencias.GUI.MainWindow;
import Ciencias.Managers.QArray;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
        

/**
 * Parses a collection of CTMA files
 * @author Paul Alves
 */


public class CTMAParser {
    private static int tempPd;
    private static ArrayList<String[]> tempAns;
    private static String name;
    private static ArrayList<StudentAnswers> studentArray;
    private static StudentAnswers key;
    
    private static void resetItems(){
    tempPd = 0;
    tempAns = new ArrayList<>();
    studentArray = new ArrayList<>();
    name = "None";
    }
    
    /**
     * Returns the answer key
     * @return The answer key
     */
    public static StudentAnswers getKey(){
    return key;
    }
    
    /**
     * Generates scores for the overview section
     * NOTE: CTMTParser and CTMAParser MUST have data for this to work
     * @return An array of the scores: average, highest, then lowest
     */
    public static int[] generateScores(){
        double avg = 0;
        int high = -1;
        int low = Integer.MAX_VALUE;
        int tempScore = 0;
        int index = 0;
        int[] scores = new int[studentArray.size()];
        for(StudentAnswers s : studentArray){
        for(int i = 0; i < key.getSize(); i++){
        if(!QArray.getQuestion(i).getType().equals("FREERESPONSE")){
            if(QArray.getQuestion(i).getMaxAnswers() > 1){
            String[] keyAns = key.getAnswerAtIndex(i).split(",");
            String[] studentAns = s.getAnswerAtIndex(i).split(",");
                if(studentAns.length == keyAns.length){
                Arrays.sort(keyAns);
                Arrays.sort(studentAns);
                if(Arrays.equals(keyAns, studentAns)){
                tempScore++;
                }
            }
        }
            else{
              if(key.getAnswerAtIndex(i).equals(s.getAnswerAtIndex(i))){
              tempScore++;
              }
             }
            
        }
        }
        scores[index] = tempScore;
        index++;
        tempScore = 0;
        }
        
        for(int s : scores){
        avg += s;
        if(s > high){ high = s; }
        if (s < low){low = s;}
        }
        avg/=scores.length;
        return new int[]{(int)Math.round(avg), high, low};
    }
    
    /**
     * Returns the array of StudentAnswer objects
     * @return The array of StudentAnswer objects
     */
    public static ArrayList<StudentAnswers> getAnswerArray(){
    return studentArray;
    }
    
    /**
     * Starts parsing the directory of CTMA files
     * @param dir Directory of the ctma files to parse
     */
    public static void startParsing(File dir){
    resetItems();
    File files[] = dir.listFiles();
    
    for(File f : files){
    if(f.isFile() && (f.getName().endsWith(".ctma") || f.getName().endsWith(".CTMA"))){
        try {
            parseFile(f);
        } catch (IOException ex) {
            MainWindow.throwError("Unable to access a ctma file. Make sure it is not in use and you have proper permissions to access this file. Error on file: " + f.getName());
        }
    }
    }
    
    }
    
    private static void parseFile(File f) throws IOException{
        try {
           BufferedReader in = new BufferedReader(new FileReader(f));
           String ln1 = in.readLine();
           
           if(!ln1.startsWith("[")){
           MainWindow.throwError("Invalid CTMA File found. Error on file: " + f.getName());
           }
           
           String[] head = ln1.split("PERIOD ");
           head[0] = head[0].replace("[","");
           head[1] = head[1].replace("]","");
           name = head[0];
           tempPd = Integer.parseInt(head[1]);
           
         while((ln1 = in.readLine())!=null){
           String[] ans = ln1.split("=");
           String realAns = ans[1];
           int index = Integer.parseInt(ans[0]);
           String style = QArray.getQuestion(index-1).getType();
           tempAns.add(new String[]{realAns,style});
           
           }
         studentArray.add(new StudentAnswers(name,tempAns,tempPd));
         tempAns.clear();
           
        } catch (FileNotFoundException ex) {
        }
    }
    
    /**
     * Parses a single CTMA file as the answer key
     * @param k The CTMA key to parse
     */
    public static void parseKey(File k){
    ArrayList<String[]> tempKey = new ArrayList<>();
    try
    {
    BufferedReader in = new BufferedReader(new FileReader(k));
    String ln1;
    ln1 = in.readLine();
      while(!ln1.startsWith("1")){
      ln1 = in.readLine();
      if(ln1 == null){
        MainWindow.throwError("Invalid answer key file.");
        return;
      }
    
      }
    do {
    String[] realAns = ln1.split("=");
    String ans = realAns[1];
    int index = Integer.parseInt(realAns[0]);
    String style = QArray.getQuestion(index-1).getType();
    tempKey.add(new String[] {ans,style});
    
    }while ((ln1 = in.readLine())!=null);
    key = new StudentAnswers(tempKey);
    
    
    }catch (FileNotFoundException ex) {
        MainWindow.throwError("Unable to find the answer key file.");
        } catch (IOException ex) {
        MainWindow.throwError("Unable to access the answer key file. Make sure you have appropriate permissions and the file is not open in any application.");
        }
    }
    
}

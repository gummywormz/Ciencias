
package Ciencias.Misc;

import Ciencias.Core.AnsweredQuestion;
import Ciencias.Core.Question;
import Ciencias.Core.StudentAnswers;
import Ciencias.Managers.QArray;
import Ciencias.Managers.WordBankMan;
import Ciencias.Parsers.CTMAParser;
import Ciencias.Parsers.CTMTParser;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that generates a CTMR file. 
 * IMPROTANT NOTE: CTMTParser and CTMAParser MUST have data for this to work! (You must parse the answer key, user answer files, and the test before calling consolidate)
 * @author Paul Alves
 */


public class CTMRConsolidator {
    
private static ArrayList<String> head;
private static ArrayList<AnsweredQuestion> questions;


    /**
     * Starts consolidating the CTMR file.
     */
    public static void consolidate(){
        
    for(int i = 0; i <QArray.getIndexes()+1; i++){
    questions.add(new AnsweredQuestion(QArray.getQuestion(i),CTMAParser.getKey().getAnswerAtIndex(i),validateAnswer(QArray.getQuestion(i)))); 
    }
    head.add("TESTNAME=" + CTMTParser.getTestName());
    head.add("ENABLEUSERNAMES=" + CTMTParser.getEnableUserNamesSetting());
    int[] scores = CTMAParser.generateScores();
    head.add("AVERAGESCORE=" + scores[0]);
    head.add("HIGHESTSCORE=" + scores[1]);
    head.add("LOWESTSCORE=" + scores[2]);
    int min = Integer.MAX_VALUE;
    int missed = 0;
    for(int j = 0; j < questions.size(); j++){
    AnsweredQuestion a = questions.get(j);
    if(a.getQuestionGraphPosition() < min){
    missed = a.getQuestionNumber();
    }
    head.add("QUESTIONGRAPHPOS=" + a.getQuestionGraphPosition());
    }
    head.add("MOSTMISSEDQUESTION=" + missed);
}
    
    /**
     * Returns the head portion of the CTMR file.
     * @return The completed overview portion of the CTMR file
     */
    public static ArrayList<String> getHead(){
    return head;
    }
    
    /**
     * Returns the questions section of the CTMR file
     * @return the questions section of the CTMR file
     */
    public static ArrayList<AnsweredQuestion> getQuestions(){
    return questions;
    }
    
    private static ArrayList<String[]> validateAnswer(Question q)
    {
    ArrayList<StudentAnswers> a = CTMAParser.getAnswerArray();
    ArrayList<String[]> ans = new ArrayList<>();
    int picked = 0;
    String names = "";
    String type = q.getType();
    switch (type){
        case "MULTIPLECHOICE":
        if(q.getMaxAnswers() > 1){type = "MAXANSWERS"; break;}
        for(int i = 0; i < q.getAnswers().size(); i++){
        for(StudentAnswers s : a){
        if(Integer.parseInt(s.getAnswerAtIndex(q.getQuestionNumber()+1)) == i ){
        picked++;
        names = names + s.getName() + ", ";
        }
        }
        ans.add(new String[]{q.getAnswers().get(i),""+picked,names});
        picked = 0;
        names = "";
        }
        return ans;
        
        case "FREERESPONSE":  
            for(StudentAnswers sa : a){
            ans.add(new String[]{sa.getAnswerAtIndex(q.getQuestionNumber()-1),"0",sa.getName()});
            }
            return ans;
            
        case "MAXANSWERS":
        for(int i = 0; i < q.getAnswers().size(); i++){
        for(StudentAnswers s : a){
        String[] split = s.getAnswerAtIndex(q.getQuestionNumber()+1).split(",");
        
        if(Arrays.asList(split).contains(""+i)){
        picked++;
        names = names + s.getName() + ", ";
        }
        }
        ans.add(new String[]{q.getAnswers().get(i),""+picked,names});
        picked = 0;
        names = "";
        }
        return ans;
            
        case "WORDBANK":
        for(String w : WordBankMan.getPristineWordBank()){
        for(StudentAnswers s: a){
        if(s.getAnswerAtIndex(q.getQuestionNumber()-1).equals(w)){
        picked++;
        names = names + s.getName() + ", ";
        }
        }
        ans.add(new String[]{w,""+picked,names});
        picked = 0;
        names = "";
        
        }
        return ans;
            
    }
        
        
    return null;
    }
    
}

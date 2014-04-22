
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
private static ArrayList<String[]> tempValidated;


    /**
     * Starts consolidating the CTMR file.
     */
    public static void consolidate(){
    head = new ArrayList<>();
    questions = new ArrayList<>();
    tempValidated = new ArrayList<>();
    for(int i = 0; i <QArray.getIndexes()+1; i++){
    Question q = QArray.getQuestion(i);
    String ans = CTMAParser.getKey().getAnswerAtIndex(i);
    validateAnswer(q);
    
    questions.add(new AnsweredQuestion(q,ans,tempValidated)); 
    tempValidated.clear();
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
    
    private static void validateAnswer(Question q)
    {
    ArrayList<StudentAnswers> a = new ArrayList(CTMAParser.getAnswerArray());
    //ArrayList<String[]> ans = new ArrayList<>();
    int picked = 0;
    String names = "";
    String type = q.getType();
    switch (type){
        case "MULTIPLECHOICE":
        if(q.getMaxAnswers() > 1){type = "MAXANSWERS"; break;}
        for(int i = 0; i < q.getAnswers().size(); i++){
        for(StudentAnswers s : a){
        if(Integer.parseInt(s.getAnswerAtIndex(q.getQuestionNumber()-1)) == i ){
        picked++;
        names = names + s.getName() + ", ";
        }
        }
        tempValidated.add(new String[]{q.getAnswers().get(i),""+picked,names});
        picked = 0;
        names = "";
        }
        return;
        
        case "FREERESPONSE":  
            for(StudentAnswers sa : a){
            tempValidated.add(new String[]{sa.getAnswerAtIndex(q.getQuestionNumber()-1),"0",sa.getName()});
            }
            return;
            
        case "MAXANSWERS":
        for(int i = 0; i < q.getAnswers().size(); i++){
        for(StudentAnswers s : a){
        String[] split = s.getAnswerAtIndex(q.getQuestionNumber()-1).split(",");
        
        if(Arrays.asList(split).contains(""+i)){
        picked++;
        names = names + s.getName() + ", ";
        }
        }
        tempValidated.add(new String[]{q.getAnswers().get(i),""+picked,names});
        picked = 0;
        names = "";
        }
        return;
            
        case "WORDBANK":
        for(String w : WordBankMan.getPristineWordBank()){
        for(StudentAnswers s: a){
        if(s.getAnswerAtIndex(q.getQuestionNumber()-1).equals(w)){
        picked++;
        names = names + s.getName() + ", ";
        }
        }
        tempValidated.add(new String[]{w,""+picked,names});
        picked = 0;
        names = "";
        } 
    }
     for(int i = 0; i < q.getAnswers().size(); i++){
        for(StudentAnswers s : a){
        String[] split = s.getAnswerAtIndex(q.getQuestionNumber()-1).split(",");
        
        if(Arrays.asList(split).contains(""+i)){
        picked++;
        names = names + s.getName() + ", ";
        }
        }
        tempValidated.add(new String[]{q.getAnswers().get(i),""+picked,names});
        picked = 0;
        names = "";
        }
    }
    
}

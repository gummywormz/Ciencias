package Ciencias.Core;

import java.util.ArrayList;
/**
 * An objective representation of a question.
 * 
 * @author Paul Alves 
 * @version 2/21/2014
 */
public class Question{

    private int qNum;
    private String qText;
    private String qType;   
    private String qTag;
    private ArrayList<String> qAnswers;
    private int maxAnswers;
    private String imgPath;

    /**
     * Constructs a Question
     * @param pQNum The Question number
     * @param pQText The Question text
     * @param pQType The question type
     * @param pQTag The Question tag (pass "None" for the default tag)
     * @param pQAnswers The Question answers as an arraylist
     * @param pMaxAnswers The maximum amount of answers a question can have
     * @param img path to an image to display in the viewer. (pass "None" for no image)
     */
    public Question(int pQNum, String pQText, String pQType, String pQTag, ArrayList<String> pQAnswers, int pMaxAnswers, String img)
    {
        qNum = pQNum;
        qText = pQText;
        qType = pQType;
        qTag = pQTag;
        qAnswers = new ArrayList<>(pQAnswers);
        maxAnswers = pMaxAnswers;
        imgPath = img;
    }

    /**
     * Returns the question number
     * @return the question number
     */
    public int getQuestionNumber()
    {
        return qNum;
    }

    /**
     * Returns the text of the question
     * @return the text of the question
     */
    public String getText()
    {
        return qText;
    }

    /**
     * Returns the question's tag
     * @return the question's tag
     */
    public String getTag()
    {
        return qTag;
    }

    /**
     * Returns the question type
     * @return the question type
     */
    public String getType()
    {
        return qType;
    }

    /**
     * Returns the image path for a question
     * @return the image path
     */
    public String getImage()
    {
        if(imgPath == null){return "None";}
        return imgPath;
    }
    
    /**
     * Returns the image path for a question
     * @return the image path
     */
    public int getMaxAnswers()
    {
        return maxAnswers;
    }

    /**
     * Returns the possible answers for the question
     * @return the answers as an ArrayList
     */
    public ArrayList<String> getAnswers()
    {
        return qAnswers;
    }
    
    @Override
    public String toString(){
    return "Question " + this.getQuestionNumber();
    }
}	
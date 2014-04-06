package Ciencias.Managers;

import java.util.ArrayList;
/**
 * Manages tags.
 * 
 * @author Paul Alves
 * @version 3/16/14
 */
public class TagMan
{
    private static ArrayList<String[]> tagList = new ArrayList<>();
    /**
     * Adds a tagged question to the array
     * @param qNum Number of the question
     * @param tag The question's tag
     */
    public static void addTag(int qNum, String tag)
    {
        String qNumVar = Integer.toString(qNum);
        String[] magic = new String[2];
        magic[0] = qNumVar;
        magic[1] = tag;
        tagList.add(magic);
    }
    
    /**
     * Returns the entire array of tags
     * @return the whole array of yags as an arraylist
     */
    public static ArrayList<String[]> getList()
    {
        return tagList;
    }
    
    /**
     * Returns the specified question number and its tsg
     * @param index the index of the question (always question number - 1)
     * @return the question and tag at the index
     */
    public static String[] getTaggedQuestion(int index)
    {
        return tagList.get(index);
    }
    }

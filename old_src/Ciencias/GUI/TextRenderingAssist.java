/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ciencias.GUI;

/**
 * Provides static methods for Strings to ensure they display and otherwise behave properly.
 * @author Paul Alves
 * @version 3/16/2014
 */
public class TextRenderingAssist {
    
    /**
     * Prepares text for the renderer by separating the text with line breaks.
     * @param pText The text to prepare
     * @param breakPoint How many characters in to create breakpoints.
     * @return The prepared text
     */
    public static String prepareText(String pText, int breakPoint){
        if(breakPoint > pText.length()){return pText;}
        String fullText = "<HTML><BODY>" + pText;
        while(fullText.charAt(breakPoint) != ' '){breakPoint--;if(breakPoint == -1){break;}}
        while(!fullText.endsWith("<BR>")){
            if(breakPoint == -1){break;}
        try{fullText = new StringBuilder(fullText).insert(breakPoint, "<BR>").toString();}catch(java.lang.StringIndexOutOfBoundsException e){break;}
        breakPoint *=2;
        
        }
    return fullText + "</BODY></HTML>";
    }

    /**
     * Tests if a string is unsuitable for a file name 
     * @param text The string to test
     * @return 1 If the string is invalid for a file name. 0 otherwise
     */
    public static int testForBadCharacters(String text) {
        if(text.contains("?") || text.contains("*") || text.contains("\"") || text.contains("<") || text.contains(">") || text.contains(":") || text.contains("\\") || text.contains("/") || text.contains("|")
                || text.startsWith(".") || text.contains("^") || text.length() >= 256 || text.contains("\\0") || text.length() == 0){return 1;}
        
        return 0;
    }
    
}

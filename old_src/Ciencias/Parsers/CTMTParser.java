package Ciencias.Parsers;

import Ciencias.Core.Question;
import Ciencias.GUI.MainWindow;
import Ciencias.Managers.QArray;
import Ciencias.Managers.TagMan;
import Ciencias.Managers.WordBankMan;
import java.io.*;
import java.util.ArrayList;

/**
 * Parses CTMT files, which contain data related to test settings and questions.
 * 
 * @author Paul Alves 
 * @version 3/16/2014
 */
public class CTMTParser
{
    private static int enableRandomization = 0;
    private static int linenum;
    private static int numberOfQuestions=0;
    private static int enableUserNames=0;
    private static int forceFullScreen=0;
    private static int appendPeriod=0;
    private static double testTimeLimit=0.0;
    private static String testName="Test Name";
    private static String driveFolderRoot="Error";
    private static String driveSubFolder="Error";
    private static String CTMAName="Error";
    //this is probably a sloppy way to make the questions but i'm lazy :/
    private static int qNum = -1;
    private static String qText = "Error";
    private static String qType = "Error"; 
    private static String qTag = "None";
    private static ArrayList<String> qAnswers;
    private static int maxAnswers = 1;
    private static String imgPath = "None";
    private static String errors = "<HTML><BODY BGCOLOR=#D6D9DF>";
    private static int numOfWBQ = 0;
    /**
     * Attempts to parse the specified CTMT file.
     * @param filePath the path of the CTMT file to parse
     * @throws java.io.IOException
     */
    public static void startParsing(File filePath) throws java.io.IOException
    {
        resetItems();
        try{
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String ln1 = in.readLine();
            linenum++;
            while(!ln1.equals("[START_HEAD]")){
                ln1 = in.readLine();
                linenum++;
                if((ln1.startsWith("@")) || (ln1.length() == 0)){}
                else if(ln1.equals("[START_HEAD]")){break;}
                else{MainWindow.throwError("No Head Found!" + ln1);return;}
            }
            //qWordBank=new ArrayList<>();
            while((ln1 = in.readLine()) != null && !ln1.equals("[END_HEAD]")){linenum++;parseHead(ln1);}
            ln1 = in.readLine();
            linenum++;
            while(!ln1.equals("[START_QUESTION]")){
                ln1 = in.readLine();linenum++;
                if((ln1.startsWith("@")) || (ln1.length() == 0)){}
                else if(ln1.equals("[START_QUESTION]")){break;}
                else{MainWindow.throwError("No Questions Found!" + ln1);return;}
            }
            if(driveFolderRoot.equals("Error")){errors = errors + "<BR>No DRIVEFOLDERROOT specified!";}
            if(CTMAName.equals("Error")){errors = errors + "<BR>No CTMANAME specified!";}
            if(driveSubFolder.equals("Error")){errors = errors + "<BR>No DRIVESUNFOLDER specified!";}
            QArray.init();
            qAnswers=new ArrayList<>();
            while((ln1 = in.readLine()) != null){linenum++;parseQuestion(ln1);}
            in.close();
        }
        catch(java.io.FileNotFoundException e){MainWindow.throwError("File not found!");}
    }

    private static void resetItems(){
        enableRandomization = 0;
        appendPeriod = 0;
        numberOfQuestions=0;
        enableUserNames=0;
        testName="Test Name";
        driveFolderRoot="Error";
        CTMAName="Error";
        driveSubFolder="Error";
        forceFullScreen=0;
        testTimeLimit=0.0;
        numOfWBQ = 0;
        errors = "<HTML><BODY BGCOLOR=#D6D9DF>";
    }

    private static void parseHead(String settingToParse)
    {
        String[] setting = settingToParse.split("=");
        String settingName = setting[0];
        if(setting[0].startsWith("@") || setting[0].length() == 0){return;}
        String settingValue = setting[1];
        settingName = settingName.toUpperCase();
        if(settingName.startsWith("ENABLERANDOMIZATION")){
            int realInt = Integer.parseInt(settingValue);
            enableRandomization = realInt;
        }else if(settingName.startsWith("NUMBEROFQUESTIONS")){
            int realInt = Integer.parseInt(settingValue);
            numberOfQuestions = realInt;
        }else if(settingName.startsWith("APPENDPERIOD")){
            int realInt = Integer.parseInt(settingValue);
            appendPeriod = realInt;
        }else if(settingName.startsWith("ENABLEUSERNAMES")){
            int realInt = Integer.parseInt(settingValue);
            enableUserNames = realInt;
        }else if(settingName.startsWith("FORCEFULLSCREEN")){
            int realInt = Integer.parseInt(settingValue);
            forceFullScreen = realInt;
        }else if(settingName.startsWith("TESTNAME")){
            testName = settingValue;
        }else if(settingName.startsWith("DRIVEFOLDERROOT")){
            driveFolderRoot = settingValue;
        }else if(settingName.startsWith("CTMANAME")){
            CTMAName = settingValue;
        }else if(settingName.startsWith("TESTTIMELIMIT")){
            double realDouble = Double.parseDouble(settingValue);
            testTimeLimit = realDouble;
        }else if(settingName.startsWith("DRIVESUBFOLDER")){
            driveSubFolder = settingValue;}
        else if(settingName.startsWith("WORDBANK")){
            String[] wordz = settingValue.split(",");
            WordBankMan.init();
            for(String n : wordz){
                WordBankMan.add(n);
            }
        }

        else{errors = errors + "<BR>Unknown header setting found: " + settingName + " @ line " + linenum;}
    }

    private static void parseQuestion(String settingToParse)
    {
        String[] setting = settingToParse.split("=");
        String settingName = setting[0];
        if(setting[0].startsWith("@") || setting[0].length() == 0 || setting[0].startsWith("[START_QUESTION]")){return;}
        if(setting[0].startsWith("[END_QUESTION]")){buildQuestion();return;}
        String settingValue = setting[1];
        settingName = settingName.toUpperCase();
        if(settingName.startsWith("QUESTIONNUMBER")){
            int realInt = Integer.parseInt(settingValue);
            qNum = realInt;
        }else if(settingName.startsWith("MAXANSWERS")){
            int realInt = Integer.parseInt(settingValue);
            maxAnswers = realInt;
        }else if(settingName.startsWith("QUESTIONSTYLE")){
            qType = settingValue;
        }else if(settingName.startsWith("IMAGE")){
            imgPath = settingValue;
        }else if(settingName.startsWith("USEIMAGE")){
            imgPath = settingValue;
        }else if(settingName.startsWith("QUESTIONTITLE")){
            qText = settingValue;
        }else if(settingName.startsWith("QUESTIONTAG")){
            qTag = settingValue;
        }else if(settingName.startsWith("ANSWERCHOICE")){
            qAnswers.add(settingValue);
        }else{errors = errors + "<BR>Unknown question setting found: " + settingName + " @ line " + linenum;}
    }

    private static void buildQuestion(){
        Question q1 = new Question(qNum, qText, qType, qTag, qAnswers, maxAnswers,imgPath);
        QArray.add(q1);
        TagMan.addTag(qNum,qTag);
        if(qNum <= 0){errors = errors + "<BR>Invalid Question Number found around line " + linenum;}
        if(qText.equals("Error")){errors = errors + "<BR>No Question Text found around line " + linenum;}
        if(!qType.equals("MULTIPLECHOICE") && !qType.equals("FREERESPONSE") && !qType.equals("WORDBANK")){errors = errors + "<BR>No Question Type found around line " + linenum;}
        if(qAnswers.isEmpty() && qType.equals("MULTIPLECHOICE")){errors = errors + "<BR>No ANSWERCHOICE(s) for multiple choice question found around line " + linenum;}
        if(qType.equals("WORDBANK")){numOfWBQ++;
        if(numOfWBQ > WordBankMan.getPristineWordBank().size()){
        errors = errors + "<BR>The number of words in the word bank is less than the amount of questions using the word bank.";
        }
        }
        qNum = -1;
        qText = "Error";
        qType = "Error";    
        qTag = "None";
        imgPath = "None";
        qAnswers.clear();
        maxAnswers = 1;
    }

    /**
     * Returns the value of ENABLERANDOMIZATION
     * @return the value of the setting (0 is false, 1 is true)
     */
    public static int getRandomizationSetting(){
        return enableRandomization;
    }

    /**
     * Returns the value of NUMBEROFQUESTIONS
     * @return the value of the setting (the number of questions defined)
     */
    public static int getNumberOfQuestionsSetting(){
        return numberOfQuestions;
    }

    /**
     * Returns the value of ENABLEUSERNAMES
     * @return the value of the setting (0 is false, 1 is true)
     */
    public static int getEnableUserNamesSetting(){
        return enableUserNames;
    }

    /**
     * Returns the value of FORCEFULLSCREEN
     * @return the value of the setting (0 is false, 1 is true)
     */
    public static int getFullScreenSetting(){
        return forceFullScreen;
    }

    /**
     * Returns the value of APPENDPERIOD
     * @return the value of the setting (0 is false, 1 is true)
     */
    public static int getAppendPeriodSetting(){
        return appendPeriod;
    }

    /**
     * Returns the value of TESTTIMELIMIT
     * @return the value of the setting (the whole number part is minutes, the fractional part is seconds)
     */
    public static double getTestTimeSetting(){
        return testTimeLimit;
    }

    /**
     * Returns the name of the test
     * @return the name of the test
     */
    public static String getTestName(){
        return testName;
    }

    /**
     * Returns the name of the CTMA Answer Key associated with this test
     * @return the name of the CTMA file
     */
    public static String getCTMAName(){
        return CTMAName;
    }

    /**
     * Returns the name of the base drive folder
     * @return the name of the base drive folder
     */
    public static String getDriveFolderRoot(){
        return driveFolderRoot;
    }

    /**
     * Returns the name of the sub drive folder where the tests will be uploaded to
     * @return the name of the sub drive folder
     */
    public static String getDriveSubFolder(){
        return driveSubFolder;
    }

    /**
     * Returns any errors found during parsing to be displayed in the error message box
     * @return Errors found during parsing.
     */
    public static String getErrors(){
if(errors.equals("<HTML><BODY BGCOLOR=#D6D9DF>")){return "";}
return errors + "</BODY></HTML>";
}
}

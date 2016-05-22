/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textparser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tyler
 */
public class TextParser {
    
    private String depFilename;
    private String srcFilename;
    private File depFile;
    private File srcFile;
    private FileInputStream fisDep;
    private FileInputStream fisSrc;
    private BufferedInputStream bisDep;
    private BufferedInputStream bisSrc;
    private DataInputStream disDep;
    private DataInputStream disSrc;
    private List<Paragraph> parList;
    private int parCount = 0;
    
    
    public TextParser(){
        parList = new ArrayList<>();
        parCount = 1;
        
        depFilename = "C:\\Users/Tyler/Dropbox/Soche/TestData/TestData4.txt";
        srcFilename = "C:\\Users/Tyler/Dropbox/Soche/TestData/TestData4.txt";
        
        depFile = new File(depFilename);
        srcFile = new File(srcFilename);
        try{
            fisDep = new FileInputStream(depFile);
            bisDep = new BufferedInputStream(fisDep);
            disDep = new DataInputStream(bisDep);
            
            fisSrc = new FileInputStream(srcFile);
            bisSrc = new BufferedInputStream(fisSrc);
            disSrc = new DataInputStream(bisSrc);
            
            
        }catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        
    }
    
    public void closeConnections(){
        try {
            fisDep.close();
            bisDep.close();
            disDep.close();
            bisSrc.close();
            fisSrc.close();
            disSrc.close();
        } catch (IOException ex) {
            Logger.getLogger(TextParser.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    /*
    public String readSrcSentence(int sentenceCount){
        int index = 0;
        char readChar;
        for(int i = 0; i < sentenceCount; i++){
            do{
                readChar = 
            }while()
        }
        
        return "null";
    } 
   */
    
    
    public String readLineFromDep() throws IOException{
        return disDep.readLine();
    }
    
    public Paragraph parseParagraph(){
        Paragraph par = new Paragraph(parCount);
        boolean verbFound = false;
        String line;
        String[] words;
        int lastVerb = 0;
        int thisVerb = 0;
        try {
            
            
            while(disDep.available() != 0){
                line = disDep.readLine();
                thisVerb++;
                words = line.split("\t");
                if(words[3].equals("VERB") ){
                    if(verbFound == false){ //start of a  new sentence
                        //System.out.println("Sentence " + sentenceCount);
                        lastVerb = 0;
                        thisVerb = 0;
                        par.addSentence();
                        verbFound = true;
                        par.addVerbToSentence(false, words[1], thisVerb);
                    } else{                        
                        par.addVerbToSentence(false, words[1], (thisVerb-lastVerb));
                        lastVerb = thisVerb;
                    }
                }else if(words[1].equals("not") || 
                        words[1].equals("Not") ||
                        words[1].equals("no")||
                        words[1].equals("No")){
                    par.addNegationToSentence(words[1]);
                    
                }else if(words[3].equals("SENT")){
                    verbFound = false;
                    //System.out.println("End Sentence");
                    //System.out.println();
                }
            }
            par.printParagraph();
            
        } catch (IOException ex) {
            Logger.getLogger(TextParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        parCount++;
        return par;
    }
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TextParser parser = new TextParser();
        
        parser.parseParagraph();
        
        parser.closeConnections();
        
    }
    
}

//
//        
//        // TODO code application logic here
//        File file = new File("C:\\Users/Tyler/Dropbox/Soche/TestData/TestData3.txt");
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        DataInputStream dis = null;
//        String Line;
//        String[] Words;
//        boolean verbFound = false;
//        int sentenceCount = 1;
//        
//        
//        try {
//            fis = new FileInputStream(file);
//
//            // Here BufferedInputStream is added for fast reading.
//            bis = new BufferedInputStream(fis);
//            dis = new DataInputStream(bis);
//
//            // dis.available() returns 0 if the file does not have more lines.
//            while (dis.available() != 0) {
//                Line = dis.readLine();
//                Words = Line.split("\t");
//                if(Words[3].equals("VERB") ){
//                    if(verbFound == false){
//                        System.out.println("Sentence " + sentenceCount);
//                        sentenceCount++;
//                        verbFound = true;
//                    }
//                    System.out.println("Verb:\t" + Words[1]);
//                }else if(Words[3].equals("SENT")){
//                    verbFound = false;
//                    System.out.println("End Sentence");
//                    System.out.println();
//                }
//                
//            }
//
//            // dispose all the resources after using them.
//            fis.close();
//            bis.close();
//            dis.close();
//
//        } catch (FileNotFoundException e) {
//          e.printStackTrace();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }

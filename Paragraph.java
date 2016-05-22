/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Tyler
 */
public class Paragraph {
    private List<Sentence> sentenceList;
    private int sentenceCount = 0;
    private int paragraphNum = 0;
    
    public Paragraph(int paragraphNum_in){
        sentenceList = new ArrayList<>();
        sentenceCount = 0;
        paragraphNum = paragraphNum_in;
    }
    
    public void addSentence(){
        Sentence sent = new Sentence(sentenceCount);
        sentenceList.add(sent);
        sentenceCount++;
    }
    
    public Sentence getSentence(int index){
        return sentenceList.get(index);
    }
    
    public int getSentenceCount(){
        return sentenceCount;
    }
   
    public void addVerbToSentence(boolean mv, String word, int prox){
        sentenceList.get(sentenceCount-1).addVerb(mv, word, prox);
    }
    
    public void addNegationToSentence(String word){
        sentenceList.get(sentenceCount-1).addNegation(word);
    }
    
    public void printParagraph(){
        System.out.println("Paragraph #" + paragraphNum);
        for(int i = 0; i < sentenceCount; i++){
            sentenceList.get(i).printSentence();
        }
        
    }
    
}

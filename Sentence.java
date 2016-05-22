/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tyler
 * 
 */
public class Sentence {
    private List<ParseWord> wordList;
    private int wordCount = 0;
    private int sentenceNum = 0;
    
    public Sentence(int sentenceNum_in){
        wordList = new ArrayList<>();
        wordCount = 0;
        sentenceNum = sentenceNum_in;
    }
    
    public void addNegation(String word_in){
        Negation negative = new Negation(word_in);
        wordList.add(negative);
        wordCount++;
    }
    
    public void addVerb(boolean mainVerb_in, String verbWord_in, int prox){
        Verb verb = new Verb(mainVerb_in, verbWord_in, prox);    
        wordList.add(verb);
        wordCount++;
    }
    
    public ParseWord getWord(int index){
        return wordList.get(index);
    }
    
    public int getWordCount(){
        return wordCount;
    }
    
    public void printSentence(){
        System.out.println("    Sentence #" + (sentenceNum+1));
        for(int i = 0; i < wordCount; i++){
            System.out.print("\t");
            if(wordList.get(i) instanceof Verb){
                ((Verb)wordList.get(i)).printVerb();  
            }else{
                ((Negation)wordList.get(i)).printWord(); 
            }
                   
        }
        System.out.println("    End Sentence\n");
    }
    
    public void clearSentence(){
        for(int i = 0; i < wordCount; i++){
            wordList.remove(0);
        }
        wordCount = 0;
        sentenceNum = 0;
    }
    
}

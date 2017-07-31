package com.wondersgroup.sh.search.summariser;

public interface IStopWordProvider {
    /**
     * Check if a word is a stop word
     * 
     * @param word The word to check
     * @return true if the word is a stop word, false otherwise
     */
    public boolean isStopWord(String word);
}

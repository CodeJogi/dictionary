package com.example.demo1;

import java.util.HashMap;

public class DictionaryUsingHashmap {

    private HashMap<String,String> hm;

    DictionaryUsingHashmap()
    {
        hm=new HashMap<>();
        addWord("Jogendra","epic of shiv");
        addWord("Mango","fruits");
        addWord("Puppy","baby of dog");
        addWord("cat","animal");
        addWord("abc","first letters of alphabets");

    }
    public boolean addWord(String s1,String s2)
    {
        hm.put(s1,s2);
        return true;
    }
    public String getMeaning(String s1)
    {
        if(hm.containsKey(s1))
        {
            return hm.get(s1);
        }
        else
        {
            return "Word not found";
        }
    }



}

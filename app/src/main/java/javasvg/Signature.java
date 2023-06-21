package javasvg;

import java.util.ArrayList;
import java.util.stream.Stream;

class Signature {
    private ArrayList<String> wordList;
    
    Signature(String source, Index index) {

      wordList = new ArrayList<String>();

      String word = "";
      while (index.get() < source.length()) {
        char c = source.charAt(index.get());

        if (source.substring(index.get()).startsWith("/*")) {
          if(word.length() > 0){
            wordList.add(word);
            word = "";
          }
          word += "/*";
          index.add(2);
          while (index.get() < source.length()) {
            if (source.substring(index.get()).startsWith("*/")) {
              break;
            }            
            c = source.charAt(index.get());
            word += c;
            index.increment();
          }
          word += "*/";
          wordList.add(word);
          word = "";
          index.increment();
        }
        else if(c == '"'){
          word += c;
          index.increment();
          while (index.get() < source.length()) {
            c = source.charAt(index.get());
            word += c;
            if (c == '"') {
              break;
            }
            index.increment();
          }
          wordList.add(word);
          word = "";
        }
        else if(c == '\''){
          word += c;
          index.increment();
          while (index.get() < source.length()) {
            c = source.charAt(index.get());
            word += c;
            if (c == '\'') {
              break;
            }
            index.increment();
          }
          wordList.add(word);
          word = "";
        }        
        else if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
          if (word.length() > 0) {
            wordList.add(word);
            word = "";
          }
        } else if (c == '(' || c == ')' || c == '{' || c == '}' || c == ';') {
          
          if (word.length() > 0) {
            wordList.add(word);
            word = "";
          }
          else if(word.length() == 0 && wordList.size() == 0){
            wordList.add(String.valueOf(c));
            word = "";
            index.increment();
          }
          
          break;
        } else {
          word += c;
        }
        index.increment();
      }

      if(word.length() > 0){
        wordList.add(word);
      }
    }

    public boolean contains(String word) {
      return wordList.contains(word);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < wordList.size(); i++) {
        sb.append(wordList.get(i));
        if (i < wordList.size() - 1) {
          sb.append(" ");
        }
      }
      return sb.toString();
    }

    public String get(int i) {
      return wordList.get(i);
    }

    public int size() {
      return wordList.size();
    }

    public Stream<String> stream() {
      return wordList.stream();
    }
  }
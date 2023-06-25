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

        // 単語の切れ目だったら区切る
        if(source.substring(index.get()).startsWith("/*")
            || c == '(' || c == ')' || c == '{' || c == '}' || c == ';'
            || c == ' ' || c == '\n' || c == '\t' || c == '\r'
            || c == ','){
          if(word.length() > 0){
            wordList.add(word);
            word = "";
          }
        }


        if (source.substring(index.get()).startsWith("/*")) {
          int end = source.substring(index.get()).indexOf("*/", 2);
          wordList.add(source.substring(index.get(), index.get() + end + 2));
          index.add(end + 2);          
        }
        else if(c == '"'){
          word += c;
          index.increment();
          while (index.get() < source.length()) {
            c = source.charAt(index.get());
            word += c;
            if (c == '\\') {
              index.increment();
              c = source.charAt(index.get());
              word += c;
            }
            else if (c == '"') {
              break;
            }
            index.increment();
          }
          wordList.add(word);
          word = "";
          index.increment();
        }
        else if(c == '\''){
          word += c;
          index.increment();
          while (index.get() < source.length()) {
            c = source.charAt(index.get());
            word += c;
            if (c == '\\') {
              index.increment();
              c = source.charAt(index.get());
              word += c;
            }
            else if (c == '\'') {
              break;
            }
            index.increment();
          }
          wordList.add(word);
          word = "";
          index.increment();
        }   
        else if(c == ','){
          wordList.add(String.valueOf(c));
          index.increment();
        }   
        else if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
          index.increment();
        } else if (c == '(' || c == ')' || c == '{' || c == '}' || c == ';') {
          
          if(wordList.size() == 0){
            wordList.add(String.valueOf(c));
            word = "";
            index.increment();
          }
          
          break;
        } else {
          word += c;
          index.increment();
        }
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
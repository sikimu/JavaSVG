package javasvg.signature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javasvg.Index;

public class Signature {
    final private List<String> wordList;
    
    Signature(String source, Index index) {

      ArrayList<String> list = new ArrayList<String>();

      // からの文節が作成されました
      if(index.get() >= source.length()){
        throw new RuntimeException("からの文節が作成されました");
      }

      // 1文字で文節とするもの
      char firstChar = source.charAt(index.get());
      if (firstChar == '(' || firstChar == ')' || firstChar == '{' || firstChar == '}' || firstChar == ';') {
        list.add(String.valueOf(firstChar));
        index.increment();

        wordList = Collections.unmodifiableList(list);
        return;
      }

      String word = "";
      while (index.get() < source.length()) {
        char c = source.charAt(index.get());

        // 単語の切れ目だったら区切る
        if(source.substring(index.get()).startsWith("/*")
            || c == '(' || c == ')' || c == '{' || c == '}' || c == ';'
            || c == ' ' || c == '\n' || c == '\t' || c == '\r'
            || c == ','){
          if(word.length() > 0){
            list.add(word);
            word = "";
          }
        }


        if (source.substring(index.get()).startsWith("/*")) {
          int end = source.substring(index.get()).indexOf("*/", 2);
          list.add(source.substring(index.get(), index.get() + end + 2));
          index.add(end + 2);          
        }
        else if(c == '"' || c == '\''){
          char quote = c;
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
            else if (c == quote) {
              break;
            }
            index.increment();
          }
          list.add(word);
          word = "";
          index.increment();
        } 
        else if(c == ','){
          list.add(String.valueOf(c));
          index.increment();
        }   
        else if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
          index.increment();
        } 
        else if (c == '(' || c == ')' || c == '{' || c == '}' || c == ';') {
          break;
        } else {
          word += c;
          index.increment();
        }
      }

      if(word.length() > 0){
        list.add(word);
      }

      wordList = Collections.unmodifiableList(list);
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
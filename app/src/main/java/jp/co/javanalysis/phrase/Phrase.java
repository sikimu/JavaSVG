package jp.co.javanalysis.phrase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javasvg.Index;

public class Phrase {
    final private List<String> stringList;
    final private List<String> commentList;
    
    Phrase(String source, Index index) {

      ArrayList<String> list = new ArrayList<String>();
      ArrayList<String> tempCommentList = new ArrayList<String>();

      // からの文節が作成されました
      if(index.get() >= source.length()){
        throw new RuntimeException("からの文節が作成されました");
      }

      // 1文字で文節とするもの
      char firstChar = source.charAt(index.get());
      if (firstChar == '(' || firstChar == ')' || firstChar == '{' || firstChar == '}' || firstChar == ';') {
        list.add(String.valueOf(firstChar));
        index.increment();

        stringList = Collections.unmodifiableList(list);
        commentList = Collections.unmodifiableList(tempCommentList);
        return;
      }

      String word = "";
      while (index.get() < source.length()) {
        char c = source.charAt(index.get());

        // 単語の切れ目だったら区切る
        if(source.substring(index.get()).startsWith("/*")
            || source.substring(index.get()).startsWith("//")
            || c == '(' || c == ')' || c == '{' || c == '}'
            || c == ' ' || c == '\n' || c == '\t' || c == '\r'
            || c == ',' || c == '='){
          if(word.length() > 0){
            list.add(word);
            word = "";
          }
        }


        if (source.substring(index.get()).startsWith("/*")) {
          int end = source.substring(index.get()).indexOf("*/", 2);
          tempCommentList.add(source.substring(index.get(), index.get() + end + 2));
          index.add(end + 2);          
        }
        else if(source.substring(index.get()).startsWith("//")){
          while (index.get() < source.length()) {
            c = source.charAt(index.get());
            if (c == '\n' || c == '\r') {
              break;
            }
            word += c;
            index.increment();
          }
          tempCommentList.add(word);
          word = "";
          index.increment();          
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
        else if(source.substring(index.get()).startsWith("==")){
          list.add(source.substring(index.get()).substring(0, 2));
          index.add(2);
        }
        else if(c == ',' || c == '='|| c == '(' || c == ')'){
          list.add(String.valueOf(c));
          index.increment();
        }   
        else if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
          index.increment();
        } 
        else if (c == '{' || c == '}' || c == ';') {
          break;
        } else {
          word += c;
          index.increment();
        }
      }

      if(word.length() > 0){
        list.add(word);
      }

      stringList = Collections.unmodifiableList(list);
      commentList = Collections.unmodifiableList(tempCommentList);
    }

    private Phrase(List<String> stringList, List<String> commentList) {
      this.stringList = Collections.unmodifiableList(stringList);
      this.commentList = Collections.unmodifiableList(commentList);
    }

    public boolean contains(String word) {
      return stringList.contains(word);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < stringList.size(); i++) {
        sb.append(stringList.get(i));
        if (i < stringList.size() - 1) {
          sb.append(" ");
        }
      }
      return sb.toString();
    }

    public String get(int i) {
      return stringList.get(i);
    }

    public int size() {
      return stringList.size();
    }

    public Stream<String> stream() {
      return stringList.stream();
    }

    public Object getComment(int i) {
      return commentList.get(i);
    }

    public List<Phrase> split(String string) {
      List<Phrase> list = new ArrayList<>();
      List<String> tempStringList = new ArrayList<>();
      for (int i = 0; i < stringList.size(); i++) {
        String word = stringList.get(i);
        if (word.equals(string)) {
          if (tempStringList.size() > 0) {
            list.add(new Phrase(tempStringList, new ArrayList<>()));
            tempStringList = new ArrayList<>();
          }
        } else {
          tempStringList.add(word);
        }
      }
      if (tempStringList.size() > 0) {
        list.add(new Phrase(tempStringList, new ArrayList<>()));
      }
      return list;
    }

    public Phrase subPhrase(int start, int end) {
      return new Phrase(stringList.subList(start, end), new ArrayList<>());
    }

    public int indexOf(String string) {
      return stringList.indexOf(string);
    }
  }
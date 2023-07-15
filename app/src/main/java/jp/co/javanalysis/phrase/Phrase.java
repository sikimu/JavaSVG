package jp.co.javanalysis.phrase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javasvg.Index;
import jp.co.javanalysis.StartEndPoint;

public class Phrase {

  /** 1文字で単語としてみなす文字列 */
  final static List<String> WORDS1 = Arrays.asList(
    "\\+", "-", "\\*", "/", "%", 
    "<", ">", 
    ",", 
    "=", "\\(", "\\)", 
    "&", "\\|", "\\^", "~", "!", "\\?", ":");

  /** 2文字で単語としてみなす文字列 */
  final static List<String> WORDS2 = Arrays.asList("==", "!=", "<=", ">=", "&&", "\\|\\|", "\\+=", "-=", "\\*=", "/=", "%=", "\\+\\+", "--", "->", "<<",">>", "\\|=", "&=", "\\^=");

  /** 3文字で単語としてみなす文字列 */
  final static List<String> WORDS3 = Arrays.asList(">>>", "<<=", ">>=");

  /** 4文字で単語としてみなす文字列 */
  final static List<String> WORDS4 = Arrays.asList(">>>=");

  final private List<String> stringList;
  final private List<String> commentList;

  Phrase(String source, Index index) {

    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> tempCommentList = new ArrayList<String>();

    // からの文節が作成されました
    if (index.get() >= source.length()) {
      throw new RuntimeException("からの文節が作成されました");
    }

    // 1文字で文節とするもの
    char c = source.charAt(index.get());
    if (c == '{' || c == '}') {
      list.add(String.valueOf(c));
      index.increment();

      stringList = Collections.unmodifiableList(list);
      commentList = Collections.unmodifiableList(tempCommentList);
      return;
    }

    String word = "";
    while (index.get() < source.length()) {
      // 1文字で文節とするもの
      c = source.charAt(index.get());

      if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
        index.increment();
        continue;
      } 

      if (c == '{' || c == '}' || c == ';') {
        break;
      }      

      StartEndPoint sep = searchWordArea(source, index.get());
      String readWord = source.substring(sep.start, sep.end);

      if (readWord.startsWith("/*") || readWord.startsWith("//")) {
        tempCommentList.add(readWord);
        index.set(sep.end);
      } 
      else{
        list.add(readWord);
        index.set(sep.end);
      }
    }

    if (word.length() > 0) {
      list.add(word);
    }

    stringList = Collections.unmodifiableList(list);
    commentList = Collections.unmodifiableList(tempCommentList);
  }

  private StartEndPoint searchWordArea(String source, final int i) {

    if (source.substring(i).startsWith("/*")) {
      int end = source.substring(i).indexOf("*/", 2);
      return new StartEndPoint(i, i + end + 2);
    }
    else if (source.substring(i).startsWith("//")) {
      Pattern pattern = Pattern.compile("\\r|\\n");
      Matcher matcher = pattern.matcher(source.substring(i));
      if (matcher.find()) {
          int end = matcher.start();
          return new StartEndPoint(i, i + end);
      } else {
          return new StartEndPoint(i, source.length());
      }      
    }   
    else if (source.substring(i).startsWith("\"") || source.substring(i).startsWith("'")) {
      char quote = source.substring(i).charAt(0);
      int end = i + 1;
      while (end < source.length()) {
        char c = source.charAt(end);
        if (c == '\\') {
          end++;
        } else if (c == quote) {
          end++;
          break;
        }
        end++;
      }
      return new StartEndPoint(i, end);
    }  
    else if (source.substring(i).matches("^(" + String.join("|", WORDS4) + ").*")){
      return new StartEndPoint(i, i + 4);
    }      
    else if (source.substring(i).matches("^(" + String.join("|", WORDS3) + ").*")){
      return new StartEndPoint(i, i + 3);
    }       
    else if (source.substring(i).matches("^(" + String.join("|", WORDS2) + ").*")){
      return new StartEndPoint(i, i + 2);
    }
    else if (source.substring(i).matches("^(" + String.join("|", WORDS1) + ").*")){
      return new StartEndPoint(i, i + 1);
    }    
    else {
      int end = i + 1;
      while (end < source.length()) {
        char c = source.charAt(end);
        // 単語の切れ目だったら区切る
        if (source.substring(end).startsWith("/*")
            || source.substring(end).startsWith("//")
            || source.substring(end).matches("^(" + String.join("|", WORDS1) + ").*")
            || source.substring(end).matches("^(" + String.join("|", WORDS2) + ").*")
            || source.substring(end).matches("^(" + String.join("|", WORDS3) + ").*")
            || source.substring(end).matches("^(" + String.join("|", WORDS4) + ").*")
            || c == '{' || c == '}'
            || c == ' ' || c == '\n' || c == '\t' || c == '\r' | c == ';') {
          break;
        }
        end++;
      }
      return new StartEndPoint(i, end);
    }
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
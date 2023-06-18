package javasvg;

public class Index{

    private int index;
    
    public Index(int index){
        this.index = index;
    }

    public int get(){
        return index;
    }

    public void increment(){
        index++;
    }

    public void add(int i) {
        index += i;
    }
}
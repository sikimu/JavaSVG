package javasvg;

import java.util.ArrayList;

public class AnalysisResultPackage {

    private final ArrayList<AnalysisResultSource> list;

    public AnalysisResultPackage(ArrayList<AnalysisResultSource> list) {

        this.list = list;
    }

    public ArrayList<String> getAllClasses() {
        
        ArrayList<String> classes = new ArrayList<>();
        for (AnalysisResultSource source : list) {
            for(int i = 0; i < source.size(); i++) {
                if(source.get(i) instanceof AnalysisResultClass) {
                    AnalysisResultClass clazz = (AnalysisResultClass) source.get(i);
                    classes.add(clazz.getName());
                }
            }
        }
        return classes;
    }
}

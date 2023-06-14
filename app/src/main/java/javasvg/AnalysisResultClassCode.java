package javasvg;

public class AnalysisResultClassCode extends AnalysisResult {

    private String name;

    public AnalysisResultClassCode(Signature signature) {
        //classの後にある単語がクラス名
        for (int i = 0; i < signature.size(); i++) {
            if (signature.get(i).equals("class")) {
                this.name = signature.get(i + 1);
                break;
            }
        }
    }

    public String getName() {
        return name;
    }
}
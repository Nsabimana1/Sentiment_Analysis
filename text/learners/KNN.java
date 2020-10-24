package text.learners;

import search.core.Duple;
import search.core.Histogram;
import text.core.Sentence;
import text.core.TextLearner;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class KNN implements TextLearner {
    ArrayList<Duple<Histogram, String>> arrayData = new ArrayList<>();
    int k = 0;
    public KNN(int k){
        this.k = k;
    }
    @Override
    public void train(Sentence words, String lbl) {
        Histogram tempHist = words.wordCounts();
        arrayData.add(new Duple<>(tempHist, lbl));

//        if(!labelMap.containsKey(lbl)){
//            labelMap.put(lbl, words.wordCounts());
//        }else {
//            for (String v : words) {
//                labelMap.get(lbl).bump(v);
//            }
//        }
    }

    @Override
    public String classify(Sentence words) {
        ArrayList<Duple<String, Double>> arrayMat = new ArrayList<>();
        Histogram<String> a = words.wordCounts();

        for(int i = 0; i < arrayData.size(); i++){
            Histogram<String> b = arrayData.get(i).getFirst();
            double total = 0.0;

            for(String s: a){
                Double countIna = (a.getCountFor(s) + 0.0) /a.getTotalCounts();
                Double countInb = (b.getCountFor(s) + 0.0) /b.getTotalCounts();
                Double tempD = Math.pow((countIna  - countInb), 2);
                total += tempD;
            }

            for(String v: b){
                if(a.getCountFor(v) == 0){
                    total += Math.pow((b.getCountFor(v)/b.getTotalCounts()), 2);
                }
            }
            arrayMat.add(new Duple<>(arrayData.get(i).getSecond(), Math.sqrt(total)));
        }

        Collections.sort(arrayMat, Comparator.comparing(Duple::getSecond));
        return this.getClassification(arrayMat);
    }

    public String getClassification(ArrayList<Duple<String, Double>> arrayMat){
        Histogram<String> result = new Histogram<>();
        for(int i = 0; i < k; i++){
            result.bump(arrayMat.get(i).getFirst());
        }
        return result.getPluralityWinner();
    }
}

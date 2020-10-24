package text.learners;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import search.core.Duple;
import search.core.Histogram;
import sun.security.pkcs11.Secmod;
import text.core.LabeledWords;
import text.core.Sentence;
import text.core.TextLearner;
import text.test.DataSetReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class NaiveBayes implements TextLearner {
    HashMap<String, Histogram<String>> labelMap = new HashMap<>();
    HashSet<String> distinctW = new HashSet<>();

	@Override
	public void train(Sentence words, String lbl) {
        if(!labelMap.containsKey(lbl)){
            labelMap.put(lbl, words.wordCounts());
        }else {
            for (String v : words) {
                labelMap.get(lbl).bump(v);
            }
        }
        for(String w: words){
            distinctW.add(w);
        }
	}

	public double getWordProByLabel(String w, String label){
        Histogram histLabel = this.labelMap.get(label);
        int wCount = histLabel.getCountFor(w);
        int totalCount = histLabel.getTotalCounts();
        int totalDistinct = this.distinctW.size();
        return this.getProbability(wCount, totalCount, totalDistinct, 1.0);
    }

    public String getClassification(HashMap<String, Double> classifiedMap){
        double min = Double.MIN_VALUE;
        String classification = "";
        for(String l: classifiedMap.keySet()){
            if(classifiedMap.get(l) > min){
                classification = l;
                min = classifiedMap.get(l);
            }
        }

        return classification;
    }

    public  static double getProbability(double wCountInCategory, double totalCountPerCategory, double totalDistinctW, double alpha){
        return (wCountInCategory + alpha)/(totalCountPerCategory + totalDistinctW);
    }

	@Override
	public String classify(Sentence words) {
        // Use the counted values for classification.
        HashMap<String, Double> classMap = new HashMap<>();
        for (String label : labelMap.keySet()) {
            double prob = 1;
             for(String w: words.wordCounts()){
                 prob *= this.getWordProByLabel(w, label);
             }
             classMap.put(label, prob);
            }

//        System.out.println(this.getClassification(classMap));
		return this.getClassification(classMap);
	}

}

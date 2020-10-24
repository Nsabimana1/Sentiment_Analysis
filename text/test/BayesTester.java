package text.test;

import java.io.FileNotFoundException;

import text.learners.KNN;
import text.learners.NaiveBayes;

public class BayesTester {
	public static void test(String[] filenames, DataSetReader dataReader) throws FileNotFoundException {
        SimpleTester.test(filenames, dataReader, (train, test) -> SimpleTester.conductTest("Naive Bayes", new NaiveBayes(), train, test));
//      SimpleTester.test(filenames, dataReader, (train, test) -> SimpleTester.conductTest("KNN", new KNN(11), train, test));
	}
}

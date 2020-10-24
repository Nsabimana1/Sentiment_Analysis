package text.test;

import text.learners.KNN;

import java.io.FileNotFoundException;

public class KNNTester {
    public static void test(String[] filenames, DataSetReader dataReader) throws FileNotFoundException {
        SimpleTester.test(filenames, dataReader, (train, test) -> SimpleTester.conductTest("KNN", new KNN(5), train, test));
    }
}

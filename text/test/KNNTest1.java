package text.test;

import text.core.LabeledWords;

import java.io.File;
import java.io.FileNotFoundException;

public class KNNTest1 {
    public static void main(String[] args) throws FileNotFoundException {
//        KNNTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "imdb_labelled.txt"}, LabeledWords::fromSentiment);
//        KNNTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "amazon_cells_labelled.txt"}, LabeledWords::fromSentiment);
//        KNNTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "yelp_labelled.txt"}, LabeledWords::fromSentiment);
        KNNTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "smsSpamCollection.txt"}, LabeledWords::fromSentimentOutSideFile);
    }
}

package text.test;

import java.io.File;
import java.io.FileNotFoundException;

import text.core.LabeledWords;

public class BayesTest1 {
	public static void main(String[] args) throws FileNotFoundException {
//		BayesTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "imdb_labelled.txt"}, LabeledWords::fromSentiment);
//        BayesTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "smsSpamCollection.txt"}, LabeledWords::fromSentiment);

//        BayesTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "imdb_labelled.txt"}, LabeledWords::fromSentiment);
//        BayesTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "amazon_cells_labelled.txt"}, LabeledWords::fromSentiment);
//        BayesTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "yelp_labelled.txt"}, LabeledWords::fromSentiment);
        BayesTester.test(new String[]{"sentiment_labelled_sentences" + File.separator + "smsSpamCollection.txt"}, LabeledWords::fromSentimentOutSideFile);

    }
}

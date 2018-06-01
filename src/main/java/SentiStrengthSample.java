import uk.ac.wlv.sentistrength.SentiStrength;

public class SentiStrengthSample {
    public static void testSentiStrength() {
        SentiStrength sentiStrengthClassifier1 = new SentiStrength();
        SentiStrength sentiStrengthClassifier2 = new SentiStrength();

        String argument1[] = {"sentidata", "/Users/nick/Documents/学习资料/2018.S1/RM/Research/SentiStrength/SentiStrength_DataEnglishFeb2017/", "explain"};
        String argument2[] = {"sentidata", "/Users/nick/Documents/学习资料/2018.S1/RM/Research/SentiStrength/SentiStrength_DataEnglishFeb2017/"};

        sentiStrengthClassifier1.initialise(argument1);
        sentiStrengthClassifier2.initialise(argument2);

        String result_from_classifier1 = sentiStrengthClassifier1.computeSentimentScores("im thinking of a may to do better world generation    hint:[https://github.com/googlemaps/google-maps-services-java](url)");
        String result_from_classifier2 = sentiStrengthClassifier2.computeSentimentScores("Very good!");

        System.out.println(result_from_classifier1 + "\n" + result_from_classifier2);
    }
}

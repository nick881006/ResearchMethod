import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uk.ac.wlv.sentistrength.SentiStrength;

import java.io.*;

public class AnalyzeCommits {
    public static void main(String... args) throws IOException, ParseException, InterruptedException {
        final String BASE_PATH = "/Users/nick/Documents/学习资料/2018.S1/RM/Research/Data/Bigquery/";

        // initialize sentiStrength
        SentiStrength sentiStrengthClassifier = new SentiStrength();
        String argument[] = {"sentidata", "/Users/nick/Documents/学习资料/2018.S1/RM/Research/SentiStrength/SentiStrength_DataEnglishFeb2017/"};
        sentiStrengthClassifier.initialise(argument);

        // loop through all languages
        for (int i = 0; i < 15; i++) {
            String language = languages.values[i].name();

            System.err.println("Processing: " + language);

            // initialize input file
            File file = new File(BASE_PATH + language + ".json");
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader(file));

            JSONParser parser = new JSONParser();

            int count = 0;
            String currentLine;
            String comment;

            // initialize output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(language + ".txt", true));
            while ((currentLine = bufferedReader.readLine()) != null) {
                JSONObject jsonObject = (JSONObject)parser.parse(currentLine);
                comment = (String)jsonObject.get("c_message");

                String result = sentiStrengthClassifier.computeSentimentScores(comment);

                int positive = Integer.parseInt(result.split("\\s+")[0]);
                int negative = Integer.parseInt(result.split("\\s+")[1]);

                int score = positive + negative;

                writer.append(String.valueOf(i + 1));
                writer.append(",");
                writer.append(String.valueOf(score));
                writer.append("\n");

                count++;

                if (count >= 10000 && count % 10000 == 0) {
                    System.out.println(count + " Records processed");
                }
            }
            writer.close();

            Thread.sleep(2000);
        }
    }

    private enum languages {
        C,
        Cpp,
        Csharp,
        Go,
        Java,
        JavaScript,
        Matlab,
        ObjectiveC,
        Perl,
        PHP,
        Python,
        R,
        Ruby,
        Scala,
        Swift;

        public static final languages values[] = values();
    }
}

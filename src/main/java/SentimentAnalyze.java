import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uk.ac.wlv.sentistrength.SentiStrength;

import java.io.*;

public class SentimentAnalyze {
    public static void analysis() {
        try {
            SentiStrength sentiStrengthClassifier = new SentiStrength();

            String argument[] = {"sentidata", "/Users/nick/Documents/学习资料/2018.S1/RM/Research/SentiStrength/SentiStrength_DataEnglishFeb2017/", "explain"};
            sentiStrengthClassifier.initialise(argument);

            File file = new File("/Users/nick/Documents/学习资料/2018.S1/RM/Research/Data/Bigquery/Perl.json");

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader(file));

            JSONParser parser = new JSONParser();

            int count = 0;
            String currentLine;
            String comment;
            while ((currentLine = bufferedReader.readLine()) != null && count < 10) {
                JSONObject jsonObject = (JSONObject)parser.parse(currentLine);

                comment = (String)jsonObject.get("c_message");

                String result = sentiStrengthClassifier.computeSentimentScores(comment);
                count++;

                System.out.println(comment + "     " + result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

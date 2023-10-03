import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderExample {
    public static void main(String[] args) {
        String csvFile = "teste.csv"; // Replace with the path to your CSV file

        try (FileReader fileReader = new FileReader(csvFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            int lineCount = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (lineCount == 0) {
                    // Skip the first line
                    lineCount++;
                    continue;
                }

                // Split the line into values based on commas
                String[] values = line.split(",");

                // Process each value
                for (String value : values) {
                    System.out.print(value + " ");
                }
                System.out.println(); // Move to the next line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

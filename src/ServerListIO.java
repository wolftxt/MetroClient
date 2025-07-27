
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerListIO {

    private static final String SERVERLISTPATH = "src/resources/serverList.txt";

    public static String[] readServerList() {
        String[] linesArray = new String[countLines()];

        try (BufferedReader br = new BufferedReader(new FileReader(SERVERLISTPATH))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                linesArray[index++] = line;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the serverList.txt file");
        }
        return linesArray;
    }

    public static int countLines() {
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(SERVERLISTPATH))) {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the serverList.txt file");
        }
        return lineCount;
    }

    public static void addServer(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SERVERLISTPATH, true))) {
            writer.write(line + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error while adding a Server to the serverList.txt file");
        }
    }

    public static void removeLineFromFile(int lineNumber) {
        Path path = Paths.get(SERVERLISTPATH);
        File tempFile = new File("tempFile.txt");

        try (BufferedReader reader = Files.newBufferedReader(path); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            int currentLineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLineNumber != lineNumber) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                currentLineNumber++;
            }

            // Delete the original file
            Files.delete(path);

            // Rename the temporary file to the original file
            Files.move(tempFile.toPath(), path);
        } catch (IOException ex) {
            System.err.println("Error while removing a line from the serverList.txt file");;
        }
    }
}

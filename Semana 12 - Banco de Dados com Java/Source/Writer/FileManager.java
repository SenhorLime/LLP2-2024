package Source.Writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<String> loadFile(String path) {
        List<String> fileLines = new ArrayList<String>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (Exception e) {
            System.err.println("Could not open file: " + e.getMessage());
        }

        fileLines.remove(0);

        return fileLines;
    }

    public static void writeLineInFile(String path, String line, boolean addLine) {
        try (BufferedWriter writer = new BufferedWriter(addLine ? new FileWriter(path, true) : new FileWriter(path))) {
            writer.write(line + System.lineSeparator());
        } catch (Exception e) {
            System.err.println("Could not open file: " + e.getMessage());
        }
    }

    public static void writeVectorInFile(String path, List<String> lines, boolean addLines) {
        try (BufferedWriter writer = new BufferedWriter(addLines ? new FileWriter(path, true) : new FileWriter(path))) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (Exception e) {
            System.err.println("Could not open file: " + e.getMessage());
        }
    }
}
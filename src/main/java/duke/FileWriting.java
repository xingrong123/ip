package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriting {
    private static final String DIR_PATH = "." + File.separator + "data";
    private static final String FILE_PATH = DIR_PATH + File.separator + "duke.txt";

    public static void saveTaskList(String data) throws DukeException {
        try {
            createDirectory();
            writeToFile(data);
        } catch (IOException e) {
            throw new DukeException(DukeExceptionType.WRITE_FILE_ERROR, e.getMessage());
        }
    }

    private static void createDirectory() throws IOException {
        Path path = Paths.get(DIR_PATH);
        Files.createDirectories(path);
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }
}

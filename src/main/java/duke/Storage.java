package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath.replace('/', File.separatorChar);
    }

    public ArrayList<String> load() throws DukeException {
        File f = new File(filePath);
        ArrayList<String> tasks;
        try {
             tasks = getTasks(f);
        } catch (FileNotFoundException e) {
            throw new DukeException(DukeExceptionType.ERROR_LOADING_FILE);
        }
        return tasks;
    }

    private ArrayList<String> getTasks(File f) throws FileNotFoundException {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            tasks.add(s.nextLine());
        }
        return tasks;
    }


    public void save(String data) throws DukeException {
        try {
            createDirectory();
            writeToFile(data);
        } catch (IOException e) {
            throw new DukeException(DukeExceptionType.WRITE_FILE_ERROR);
        }
    }

    private void createDirectory() throws IOException {
        String dirPath = getDirectory(filePath);
        Path path = Paths.get(dirPath);
        Files.createDirectories(path);
    }

    private String getDirectory(String path) {
        String dirPath;
        if (path.contains(File.separator)) {
            dirPath = path.substring(0, path.lastIndexOf(File.separator));
        } else {
            dirPath = path;
        }
        return dirPath;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}

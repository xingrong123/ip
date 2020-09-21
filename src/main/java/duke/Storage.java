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
import java.util.Scanner;

public class Storage {
    private static final String TASK_SEPARATOR = "~~";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath.replace('/', File.separatorChar);
    }

    public String[] load() throws DukeException {
        File f = new File(filePath);
        String[] tasks;
        try {
             tasks = getTasks(f);
        } catch (FileNotFoundException e) {
            throw new DukeException(DukeExceptionType.ERROR_LOADING_FILE);
        }
        return tasks;
    }

    private String[] getTasks(File f) throws FileNotFoundException {
        StringBuilder data = new StringBuilder();
        int taskCount;
        taskCount = readTasksAndGetTaskCount(data, f);
        String[] tasks = data.toString().split(TASK_SEPARATOR, taskCount);
        return tasks;
    }

    private int readTasksAndGetTaskCount(StringBuilder data, File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        int taskCount = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            data.append(line);
            taskCount++;
            if (s.hasNextLine()) {
                data.append(TASK_SEPARATOR);
            }
        }
        return taskCount;
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

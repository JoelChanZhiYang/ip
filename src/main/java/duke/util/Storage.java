package duke.util;

import duke.exception.InvalidInputException;
import duke.exception.SaveFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String saveFilePath;

    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public ArrayList<? extends Task> load() throws InvalidInputException, SaveFileException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File saveFile = getSaveFile();
            Scanner scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                String taskInSaveFormat = scanner.nextLine();
                String[] currTask = taskInSaveFormat.split("\\|\\|");
                Task newTask;
                switch (currTask[0]) {
                    case "[T]":
                        newTask = new Todo(currTask[2]);
                        if (currTask[1].equals("true")) {
                            newTask.complete();
                        }
                        tasks.add(newTask);
                        break;
                    case "[E]":
                        LocalDateTime eventTime = Parser.parseDate(currTask[3]);
                        newTask = new Event(currTask[2], eventTime);
                        if (currTask[1].equals("true")) {
                            newTask.complete();
                        }
                        tasks.add(newTask);
                        break;
                    case "[D]":
                        LocalDateTime deadline = Parser.parseDate(currTask[3]);
                        newTask = new Deadline(currTask[2], deadline);
                        if (currTask[1].equals("true")) {
                            newTask.complete();
                        }
                        tasks.add(newTask);
                        break;
                }
            }
            System.out.println("Save file successfully read.");
            return tasks;
        } catch (IOException e) {
            throw new SaveFileException("Unable to load save file.");
        }
    }

    public void save(TaskList list) throws SaveFileException {
        try {
            File saveFile = this.getSaveFile();
            FileWriter writer = new FileWriter(saveFile);
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toSaveFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new SaveFileException("Unable to write to save file.");
        }
    }

    private File getSaveFile() throws IOException {
        File saveFile = new File(saveFilePath);
        saveFile.getParentFile().mkdirs(); // if folder already exist, nothing is done
        saveFile.createNewFile(); // if file already exists, nothing is done
        return saveFile;
    }
}
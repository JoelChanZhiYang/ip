package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke class to encapsulate the high level logic of Duke
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructor of Duke
     *
     * @param filePath path of the save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Function that causes Duke to start listening for user input
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main function that instatiates a Duke and runs it.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("./data/saveFile.txt").run();
    }
}

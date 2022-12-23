package application.controllers;

import application.data_handler.FileHandler;

public class FileController {
    FileHandler fileHandler = new FileHandler();

    // Getter ----------------------------------

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public void adminWriteToFiles() {
        fileHandler.getFileWriter().writeToEmployeeList();
        fileHandler.getFileWriter().writeToLoginCredentials();
    }
}

package application.data_handler;

import application.filehandler.FileReader;
import application.filehandler.FileWriter;

public class FileHandler {
    FileWriter fileWriter = new FileWriter();
    FileReader fileReader = new FileReader();

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public FileReader getFileReader() {
        return fileReader;
    }
}

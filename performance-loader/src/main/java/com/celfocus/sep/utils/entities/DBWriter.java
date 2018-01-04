package com.celfocus.sep.utils.entities;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.celfocus.sep.utils.Constants;
import com.celfocus.sep.utils.exceptions.WriterInUseException;

public class DBWriter {

    private Map<TABLE, BufferedWriter> writers = new HashMap<>();
    private String directoryPath;

    public DBWriter(final String directoryPath) throws Exception {
        if (!Files.isDirectory(Paths.get(directoryPath), LinkOption.NOFOLLOW_LINKS)) {
            throw new Exception("Directory does not exist");
        }
        this.directoryPath = directoryPath;
    }

    public void addWriter(final TABLE table, final String fileName, final String pathSeparator) throws IOException, WriterInUseException {
        if (writers.containsKey(table)) {
            throw new WriterInUseException();
        }
        writers.put(table, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directoryPath + pathSeparator + fileName), "utf-8")));

    }

    public void write(final TABLE table, final String content) throws IOException {
        writers.get(table).write(content);
    }

    public void writeLine(final TABLE table, final String content) throws IOException {
        writers.get(table).write(content + Constants.EOL);
    }

    private void closeWriter(final TABLE table) throws IOException {
        writers.get(table).close();
    }

    public void destroy() {
        for (TABLE table : writers.keySet()) {
            try {
                closeWriter(table);
            } catch (IOException e) {
                System.out.println("[ERROR]," + e);
            }
        }
    }
}

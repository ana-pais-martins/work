package com.celfocus.sep.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.celfocus.sep.utils.entities.Company;
import com.celfocus.sep.utils.entities.DB;
import com.celfocus.sep.utils.entities.DBWriter;
import com.celfocus.sep.utils.entities.Group;
import com.celfocus.sep.utils.entities.TABLE;
import com.celfocus.sep.utils.entities.TableElement;
import com.celfocus.sep.utils.entities.Terminal;
import com.celfocus.sep.utils.entities.User;

public class Loader {

    private static final Map<DB, DBWriter> writers = new HashMap<>();

    private static void createWriters() throws Exception {
        for (DB db : DB.values()) {
            DBWriter dbWriter = new DBWriter(db.getPerformanceDirectory());
            for (TABLE table : TABLE.values()) {
                dbWriter.addWriter(table, db.getFileName(table), Constants.PATH_SEPARATOR);
            }
            writers.put(db, dbWriter);
        }
    }

    private static void cleanupWriters() {
        for (DB db : writers.keySet()) {
            writers.get(db).destroy();
        }
    }

    public static void main(String... args) throws Exception {
        try {
            createWriters();
            insertData();
        } finally {
            cleanupWriters();
        }
    }

    private static void writeData(final TableElement tableElement) throws IOException {
        for (DB db : writers.keySet()) {
            writers.get(db).writeLine(tableElement.getElementTable(), tableElement.getInsertRow(db));
        }
    }

    private static void insertData() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        Company currentCompany = new Company();
        Group currentGroup = new Group();
        User currentUser = new User();
        Terminal currentTerminal = new Terminal();
        long currentGroupId = 1;
        System.out.println("Insert Company Data");
        for (long companyId = 1; companyId <= Constants.NUMBER_COMPANIES; companyId++) {
            currentCompany.setCompanyId(companyId);
            writeData(currentCompany);
            currentGroup.setCompanyId(companyId);
            for (int groupsInCompany = 0; groupsInCompany < Constants.NUMBER_GROUPS_COMPANY; groupsInCompany++) {
                currentGroup.setGroupId(currentGroupId);
                writeData(currentGroup);
                currentGroupId++;
            }
        }
        long companyId = 1, groupId = 1;
        for (long userId = 1; userId <= Constants.NUMBER_TERMINALS;) {
            currentUser.setGroupId(groupId);
            currentTerminal.setCompanyId(companyId);
            long numberTerminals = userId == 1 ? Constants.MAX_TERMINALS_COMPANY : 1;
            for (int counter = 0; counter < numberTerminals; counter++) {
                currentUser.setUserId(userId);
                writeData(currentUser);
                currentTerminal.setUserId(userId);
                writeData(currentTerminal);
                userId++;
            }
            companyId = companyId % Constants.NUMBER_COMPANIES + 1;
            groupId += Constants.NUMBER_GROUPS_COMPANY;
            if (groupId > Constants.NUMBER_GROUPS) {
                groupId = (userId - Constants.MAX_TERMINALS_COMPANY) / Constants.NUMBER_COMPANIES % Constants.NUMBER_GROUPS_COMPANY + 1;
            }
        }
    }
}

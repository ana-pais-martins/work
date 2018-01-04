package com.celfocus.sep.utils.entities;

import java.io.IOException;

import com.celfocus.sep.utils.Constants;

public class Company implements TableElement {
    private long companyId;
    private String companyName;
    private static final String NAME_FORMAT = "COMPANY_PERFORMANCE_%d";

    public Company() {
        super();
    }

    public Company(final long companyId) {
        super();
        setCompanyId(companyId);
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final long companyId) {
        this.companyId = companyId;
        this.companyName = String.format(NAME_FORMAT, companyId);
    }

    @Override
    public String getInsertRow(final DB dialect) throws IOException {
        String insertRow = "";
        switch (dialect) {
        case ORACLE:
            insertRow = String.format("%d,%s", companyId, companyName);
            break;
        case TT:
            insertRow = String.format(
                                      "%d,%s,\"EN\",NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,NULL,NULL,0,NULL,1,0,0,0,0,2,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,0,0,0,NULL,NULL,0,0,0,0,0,0,NULL,%s",
                                      companyId, companyName, Constants.INSERT_DATA);
            break;
        }
        return insertRow;
    }

    @Override
    public TABLE getElementTable() {
        return TABLE.COMPANY;
    }
}

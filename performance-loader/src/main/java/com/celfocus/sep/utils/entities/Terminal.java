package com.celfocus.sep.utils.entities;

import java.io.IOException;

import com.celfocus.sep.utils.Constants;

public class Terminal implements TableElement {

    private String terminalId;
    private long companyId;
    private long userId;
    private static final String ID_FORMAT = "4410%07d";
    
    public Terminal(){
        super();
    }
    
    public Terminal(final long userId, final long companyId){
        super();
        setUserId(userId);
        setCompanyId(companyId);
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
        this.terminalId = String.format(ID_FORMAT, userId);
    }

    @Override
    public String getInsertRow(DB dialect) throws IOException {
        String insertRow = "";
        switch (dialect) {
        case ORACLE:
            insertRow = String.format("%s,%d,%d", terminalId, userId, companyId);
            break;
        case TT:
            insertRow = String.format(
                                      "%s,NULL,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,\"PreActive\",NULL,20,NULL,NULL,0,\"0\",0,NULL,NULL,\"\",NULL,%d,%d,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL,NULL,1,0,0,NULL,NULL,0,0,0,0,1,0,NULL,0,0,0,0,0,0,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,0,0,NULL,NULL,NULL,NULL,%s",
                                      terminalId, companyId, userId, Constants.INSERT_DATA);
            break;
        }
        return insertRow;
    }

    @Override
    public TABLE getElementTable() {
        return TABLE.TERMINAL;
    }
}

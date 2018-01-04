package com.celfocus.sep.utils.entities;

import java.io.IOException;

import com.celfocus.sep.utils.Constants;

public class User implements TableElement {

    private long userId;
    private long groupId;
    private String userName;
    private static final String NAME_FORMAT = "USER_PERFORMANCE_%d";

    public User() {
        super();
    }

    public User(final long userId, final long groupId) {
        super();
        setUserId(userId);
        setGroupId(groupId);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
        this.userName = String.format(NAME_FORMAT, userId);
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getInsertRow(DB dialect) throws IOException {
        String insertRow = "";
        switch (dialect) {
        case ORACLE:
            insertRow = String.format("%d,%s,%d", userId, userName, groupId);
            break;
        case TT:
            insertRow = String.format(
                                      "%d,%d,%s,NULL,0,1,1,0,0,0,NULL,0,NULL,NULL,\"PT\",0,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,0,1,NULL,0,NULL,NULL,NULL,\"0000000000000000000\",NULL,NULL,NULL,1,2,NULL,NULL,\"123\",NULL,NULL,NULL,NULL,0,0,0,0,\"0\",NULL,%s",
                                      userId, groupId, userName, Constants.INSERT_DATA);
            break;
        }
        return insertRow;
    }

    @Override
    public TABLE getElementTable() {
        return TABLE.USER;
    }
}

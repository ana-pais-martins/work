package com.celfocus.sep.utils.entities;

import java.io.IOException;

import com.celfocus.sep.utils.Constants;

public class Group implements TableElement {
    private long companyId;
    private long groupId;
    private String groupName;
    private static final String NAME_FORMAT = "GROUP_PERFORMANCE_%d";

    public Group() {
        super();
    }

    public Group(final long groupId, final long companyId) {
        super();
        setCompanyId(companyId);
        setGroupId(groupId);
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
        this.groupName = String.format(NAME_FORMAT, groupId);
    }

    @Override
    public String getInsertRow(final DB dialect) throws IOException {
        String insertRow = "";
        switch (dialect) {
        case ORACLE:
            insertRow = String.format("%d,%s,%d", groupId, groupName, companyId);
            break;
        case TT:
            insertRow = String.format("%d,%d,%s,1,NULL,0,0,0,%s", groupId, companyId, groupName, Constants.INSERT_DATA);
            break;
        }
        return insertRow;
    }

    @Override
    public TABLE getElementTable() {
        return TABLE.GROUP;
    }
}

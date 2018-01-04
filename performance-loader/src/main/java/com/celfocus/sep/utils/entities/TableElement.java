package com.celfocus.sep.utils.entities;

import java.io.IOException;

public interface TableElement {

    String getInsertRow(final DB dialect) throws IOException;
    
    TABLE getElementTable();
}

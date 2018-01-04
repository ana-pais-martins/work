package com.celfocus.sep.utils;

import java.util.Date;

public final class Constants {
    public static final String INSERT_USER = "db-performance";
    public static final String EOL = "\n";
    public static final String PATH_SEPARATOR = "\\";
    public static final long NUMBER_COMPANIES = 25000;
    public static final long INSERT_TIMESTAMP = new Date().getTime() / 1000;
    public static final long NUMBER_GROUPS_COMPANY = 3;
    public static final long NUMBER_GROUPS = NUMBER_COMPANIES * NUMBER_GROUPS_COMPANY;
    public static final long NUMBER_TERMINALS = 1800000;
    public static final long MAX_TERMINALS_COMPANY = 100000;
    public static final String INSERT_DATA = String.format("%d,%s,%d,%s", INSERT_TIMESTAMP, INSERT_USER, INSERT_TIMESTAMP, INSERT_USER);
    
}

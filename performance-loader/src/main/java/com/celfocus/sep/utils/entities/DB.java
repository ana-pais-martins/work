package com.celfocus.sep.utils.entities;

public enum DB {
    TT("D:\\projects\\SEP\\onenet\\docker-images\\timesten-sep\\sql_scripts\\performance", "insert_%s.sql"),
    ORACLE("D:\\projects\\SEP\\onenet\\docker-images\\database\\sql\\post_install\\performance", "%s_data.txt");

    private String performanceDirectory;
    private String fileNameFormat;

    DB(final String loadDirectory, final String fileNameFormat) {
        this.performanceDirectory = loadDirectory;
        this.fileNameFormat = fileNameFormat;
    }

    public String getFileName(final TABLE table) {
        return String.format(fileNameFormat, table.name().toLowerCase());
    }

    public String getPerformanceDirectory() {
        return this.performanceDirectory;
    }
}

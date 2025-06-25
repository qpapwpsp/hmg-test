package com.hmg.as.test.hmg_test.vo;

public class ColumnInfo {
    private final String name;
    private final String dbType;
    private final int size;
    private final boolean notNull;

    public ColumnInfo(String name, String dbType, int size, boolean notNull) {
        this.name = name;
        this.dbType = dbType;
        this.size = size;
        this.notNull = notNull;
    }

    public String getName() { return name; }
    public String getDbType() { return dbType; }
    public int getSize() { return size; }
    public boolean isNotNull() { return notNull; }
}

package com.hy.kelihua;

class LogEntry {
    private String level;
    private String message;
    private String date;

    public LogEntry(String level, String message, String date) {
        this.level = level;
        this.message = message;
        this.date = date;
    }

    public String getLevel() { return level; }
    public String getMessage() { return message; }
    public String getDate() { return date; }
}
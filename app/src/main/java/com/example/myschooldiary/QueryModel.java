package com.example.myschooldiary;

public class QueryModel {
    String ClassCode, Name, Query, Subject;

    public QueryModel(){
        
    }
    public QueryModel(String classCode, String Name, String query, String subject) {
        this.ClassCode = classCode;
        this.Name = Name;
        this.Query = query;
        this.Subject = subject;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String classCode) {
        this.ClassCode = classCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String studentName) {
        this.Name = studentName;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        this.Query = query;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        this.Subject = subject;
    }
}

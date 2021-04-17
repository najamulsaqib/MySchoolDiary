package com.example.myschooldiary;

public class QueryModel {
    String ClassCode, Name, Query, Subject, Id;

    public QueryModel(){
        
    }
    public QueryModel(String classCode, String Name, String query, String subject, String Id) {
        this.ClassCode = classCode;
        this.Name = Name;
        this.Query = query;
        this.Subject = subject;
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

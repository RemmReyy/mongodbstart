package org.cntu.model;

import org.bson.Document;

public class SocialPerformanceRecord {
    private int goalId;
    private String goalDescription;
    private double valueSupervisor;
    private double valuePeerGroup;
    private int year;

    public SocialPerformanceRecord(int goalId, String goalDescription, double valueSupervisor, double valuePeerGroup, int year) {
        this.goalId = goalId;
        this.goalDescription = goalDescription;
        this.valueSupervisor = valueSupervisor;
        this.valuePeerGroup = valuePeerGroup;
        this.year = year;
    }

    public int getGoalId() { return goalId; }

    public void setGoalId(int goalId) { this.goalId = goalId; }

    public String getGoalDescription() { return goalDescription; }

    public void setGoalDescription(String goalDescription) { this.goalDescription = goalDescription; }

    public double getValueSupervisor() { return valueSupervisor; }

    public void setValueSupervisor(double valueSupervisor) { this.valueSupervisor = valueSupervisor; }

    public double getValuePeerGroup() { return valuePeerGroup; }

    public void setValuePeerGroup(double valuePeerGroup) { this.valuePeerGroup = valuePeerGroup; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("goal-id" , this.goalId);
        document.append("goalDescription" , this.goalDescription);
        document.append("valueSupervisor" , this.valueSupervisor);
        document.append("valuePeerGroup" , this.valuePeerGroup);
        document.append("year" , this.year);
        return document;
    }
}


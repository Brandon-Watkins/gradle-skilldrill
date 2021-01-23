package edu.isu.cs.cs2263;

public class Course {
    private int number;
    private String subject;
    private String title;

    Course() {}
    Course(String sub, int num, String title) { subject = sub; number = num; this.title = title; }

    public void setNumber(int num) { number = num; }
    public int getNumber() { return number; }
    public void setSubject(String subj) { subject = subj; }
    public String getSubject() { return subject; }
    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }
    @Override
    public String toString() { return subject + " " + number + " " + title; }
}

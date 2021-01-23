package edu.isu.cs.cs2263;

import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private List<Course> courses;

    Student() {}
    Student(String first, String last) { firstName = first; lastName = last; }
    Student(String first, String last, List<Course> courses) { firstName = first; lastName = last; this.courses = courses; }

    public void setFirstName(String name) { firstName = name ; }
    public String getFirstName() { return firstName; }
    public void setLastName(String name) { lastName = name ; }
    public String getLastName() { return lastName; }
    @Override
    public String toString() { return firstName + " " + lastName; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
    public boolean addCourse(Course course) { return courses.add(course); }
    public boolean removeCourse(Course course) { return courses.remove(course); }
}

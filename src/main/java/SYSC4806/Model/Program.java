package SYSC4806.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Program is a model class that represent a group of courses.
 */
@Entity
public class Program {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "programs")
    private List<Course> courses = new ArrayList<>();

    public Program() {
    }

    public Program(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> listCourse) {
        this.courses = listCourse;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
}

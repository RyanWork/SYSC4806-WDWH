package SYSC4806.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 *  LearningOutcome is a model class that represents skills a student should acquire after completing a course.
 *  A learning outcome belongs to many categories and a student acquire's certain learning outcomes by taking courses.
 */
@Entity
public class LearningOutcome {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Course> courses = new ArrayList<>();

    public LearningOutcome() {
    }

    public LearningOutcome(String name, Category category) {
        this.name = name;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
}

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToMany
    private List<Course> courses;

    public LearningOutcome() {
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
}

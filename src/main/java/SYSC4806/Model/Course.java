package SYSC4806.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Course is a model class that represents a course a student can take.
 * A course belongs to many programs and a student should acquire certain learning outcomes by taking this course.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int year;
    private String name;
    private String code;

    @ManyToMany
    private List<LearningOutcome> learningOutcomes;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "program_course",
            joinColumns = @JoinColumn(name = "program_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",
                    referencedColumnName = "id"))
    private List<Program> programs;

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<LearningOutcome> getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(List<LearningOutcome> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}

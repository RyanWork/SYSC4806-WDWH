package SYSC4806;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int year;
    private String name;
    private String code;
    private List<LearningOutcome> learningOutcomes;
    private List<Program> programs;

    public Course() {
    }

}

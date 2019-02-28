package SYSC4806.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Category is a model class that represents a group of learning outcomes.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<LearningOutcome> learningOutcomes;

    public Category() {
    }

    public Category(String name, List<LearningOutcome> learningOutcomes) {
        this.name = name;
        this.learningOutcomes = learningOutcomes;
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

    public List<LearningOutcome> getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(List<LearningOutcome> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
}

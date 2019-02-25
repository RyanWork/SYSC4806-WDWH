package SYSC4806.Model;

import javax.persistence.*;
import java.util.ArrayList;


/**
 * Category is a model class that represents a group of learning outcomes.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany
    private ArrayList<LearningOutcome> learningOutcomes;

    public Category() {
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

    public ArrayList<LearningOutcome> getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(ArrayList<LearningOutcome> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
}

package SYSC4806.Model;

import javax.persistence.*;
import java.util.ArrayList;


/**
 * Program is a model class that represent a group of courses.
 */
@Entity
public class Program {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "programs")
    private ArrayList<Course> listCourse;

    public Program() {
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

    public ArrayList<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(ArrayList<Course> listCourse) {
        this.listCourse = listCourse;
    }
}

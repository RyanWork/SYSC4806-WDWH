package SYSC4806;

import SYSC4806.Model.Category;
import SYSC4806.Model.Course;
import SYSC4806.Model.LearningOutcome;
import SYSC4806.Model.Program;
import SYSC4806.Repository.CategoryRepository;
import SYSC4806.Repository.CourseRepository;
import SYSC4806.Repository.LearningOutcomeRepository;
import SYSC4806.Repository.ProgramRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CategoryRepository categoryRepository, CourseRepository courseRepository, LearningOutcomeRepository learningOutcomeRepository, ProgramRepository programRepository) {
        return (args) -> {

            Category category = new Category("Programming");
            Category category2 = new Category("Software Architecture");
            Category category3 = new Category("Logic Algorithm");

            LearningOutcome learningOutcome = new LearningOutcome("Web Application Coding", category);
            LearningOutcome learningOutcome2 = new LearningOutcome("Design Software System", category2);
            LearningOutcome learningOutcome3 = new LearningOutcome("Logic Gates", category3);

            Course course = new Course(4, "Software Engineering Lab", "4806");
            Course course2 = new Course(3, "Real-Time Concurrent System", "3303");
            Course course3 = new Course(3, "Operating Systems", "4001");
            Course course4 = new Course(2, "Circuits and Signals", "2501");
            Course course5 = new Course(2, "Switching Circuits", "2607");
            Course course6 = new Course(1, "Introduction to Software", "1005");
            Course course7 = new Course(1, "Introduction to Engineering", "1010");

            Program program = new Program("Software Engineering");
            Program program2 = new Program("Civil Engineering");
            Program program3 = new Program("Computer System Engineering");
            Program program4 = new Program("Electrical Engineering");

            course.addLO(learningOutcome);
            course2.addLO(learningOutcome);
            course3.addLO(learningOutcome);
            course4.addLO(learningOutcome3);
            course5.addLO(learningOutcome3);
            course6.addLO(learningOutcome);
            course7.addLO(learningOutcome2);

            program.addCourse(course);
            program.addCourse(course2);
            program.addCourse(course3);
            program.addCourse(course4);
            program.addCourse(course5);
            program.addCourse(course6);
            program2.addCourse(course7);
            program3.addCourse(course4);
            program3.addCourse(course5);
            program3.addCourse(course6);
            program4.addCourse(course4);
            program4.addCourse(course5);

            programRepository.save(program);
            programRepository.save(program2);
            programRepository.save(program3);
            programRepository.save(program4);
        };
    }
}
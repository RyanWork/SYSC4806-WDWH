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

            // Testing purposes to connect DB to Front
            // Can delete this setup once DB is up
            // Hard Input of each object.
            Category category = new Category("Programming");
            Category category2 = new Category("Software Architecture");
            Category category3 = new Category("Logic Algorithm");
            LearningOutcome learningOutcome = new LearningOutcome("Web Application Coding", category);
            Course course = new Course(4, "Software Engineering Lab", "4806");
            Course course2 = new Course(3, "Real-Time Concurrent System", "3303");
            Course course3 = new Course(3, "Operating Systems", "4001");
            Program program = new Program("Software Engineering");
            Program program2 = new Program("Civil Engineering");
            Program program3 = new Program("Computer System Engineering");

            category.addLO(learningOutcome);
            learningOutcome.addCourse(course);

            course.addLO(learningOutcome);
            course.addProgram(program);

            program.addCourse(course);

            categoryRepository.save(category);
            courseRepository.save(course);
            learningOutcomeRepository.save(learningOutcome);
            programRepository.save(program);

            categoryRepository.save(category2);
            categoryRepository.save(category3);
            courseRepository.save(course2);
            courseRepository.save(course3);
            programRepository.save(program2);
            programRepository.save(program3);
        };
    }
}
package SYSC4806.Controller;

import SYSC4806.Model.Course;
import SYSC4806.Repository.CategoryRepository;
import SYSC4806.Repository.CourseRepository;
import SYSC4806.Repository.LearningOutcomeRepository;
import SYSC4806.Repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LearningOutcomeRepository learningOutcomeRepository;

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("learningoutcomes", learningOutcomeRepository.findAll());
        model.addAttribute("programs", programRepository.findAll());
        return "index";
    }

    /*
     * Selects courses based on chosen program and year and returns them to view
     * to refresh results table
     */
    @GetMapping("/results/{program}/{year}")
    public String showResults(Model model, @PathVariable("program") String p, @PathVariable String year) {
        long p_id = programRepository.findByName(p).getId();
        List<String> courseNames = courseRepository.findCourseByProgramAndYear(p_id, Integer.parseInt(year));
        ArrayList<Course> courses = new ArrayList<>();
        for (String cName : courseNames) {
            Course c = courseRepository.findByName(cName);
            courses.add(c);
        }
        model.addAttribute("courses", courses);
        return "fragments/results :: resultsTable";
    }

    /*
     * Exports to CSV
     */
    @GetMapping("/export/{csvoption}")
    public String export(@PathVariable("csvoption") String csvoption) {
        if (!csvoption.isEmpty()) {
            try (PrintWriter writer = new PrintWriter(new File("Learning Outcomes.csv"))) {

                String csvDataNL = csvoption.replace(", $,", "\r\n");

                StringBuilder sb = new StringBuilder();
                sb.append(csvDataNL);

                writer.write(sb.toString());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return "fragments/results :: exportCsv";
    }
}

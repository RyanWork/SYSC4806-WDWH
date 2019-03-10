package SYSC4806.Controller;
import SYSC4806.Model.Course;
import SYSC4806.Model.Program;
import SYSC4806.Repository.CategoryRepository;
import SYSC4806.Repository.CourseRepository;
import SYSC4806.Repository.LearningOutcomeRepository;
import SYSC4806.Repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @GetMapping("/results/{program}/{year}")
    public String showResults(Model model, @PathVariable("program") String p, @PathVariable String year)
    {
        long p_id = programRepository.findByName(p).getId();
//        model.addAttribute("courses", program.getCourses());
        List<String> courseNames = courseRepository.findCourseByProgramAndYear(p_id, Integer.parseInt(year));
        ArrayList<Course> courses = new ArrayList<>();
        for(String cName : courseNames) {
            Course c = courseRepository.findByName(cName);
            courses.add(c);
        }
        model.addAttribute("courses", courses);
        return "fragments/results :: resultsTable";
    }
}

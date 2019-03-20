package SYSC4806.Controller;

import SYSC4806.Model.Category;
import SYSC4806.Model.Course;
import SYSC4806.Model.LearningOutcome;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    private final LearningOutcomeRepository learningOutcomeRepository;

    private final ProgramRepository programRepository;

    @Autowired
    public HomeController(CategoryRepository categoryRepository, CourseRepository courseRepository, LearningOutcomeRepository learningOutcomeRepository, ProgramRepository programRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.learningOutcomeRepository = learningOutcomeRepository;
        this.programRepository = programRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("learningoutcomes", learningOutcomeRepository.findAll());
        model.addAttribute("programs", programRepository.findAll());
        return "index";
    }

    /*
     * Selects courses based on chosen program, year, courses and catagories and returns them to view
     * to refresh results table
     */
    @GetMapping("/results/{program}/{year}")
    public String showResults(Model model, @PathVariable("program") String p, @PathVariable("year") String year,
                              @RequestParam(value = "courses", required = false) String co,
                              @RequestParam(value = "categories", required = false) String ca) {
        long p_id = programRepository.findByName(p).getId();
        List<String> courseNames = courseRepository.findCourseByProgramAndYear(p_id, Integer.parseInt(year));

        if (co != null && !(co.isEmpty())) {
            List<String> courseNamesFilter = Arrays.asList(co.split(","));
            courseNames.retainAll(courseNamesFilter);
        }

        if (ca != null && !(ca.isEmpty())) {
            List<String> catNamesFilter = Arrays.asList(ca.split(","));

            //As per the category, finding the list of learning outcomes
            ArrayList<Long> loListId = new ArrayList<>();
            for (String cName : catNamesFilter) {
                List<BigInteger> loIdList = learningOutcomeRepository.findByCategoryName(cName);
                for (BigInteger loID : loIdList) {
                    loListId.add(loID.longValue());
                }
            }

            //As per the list of learning outcomes, find the list of course id's
            HashSet<BigInteger> courseIdsList = new HashSet<>();
            for (Long lo : loListId) {
                List<BigInteger> courseIds = learningOutcomeRepository.findByLearningOutcomeId(lo);
                courseIdsList.addAll(courseIds);
            }

            //As per course ids, find the course names
            ArrayList<String> coursesCatFilter = new ArrayList<>();
            for (BigInteger courseId : courseIdsList) {
                Course c = courseRepository.findById(courseId.longValue()).orElse(null);
                coursesCatFilter.add(c.getName());
            }

            courseNames.retainAll(coursesCatFilter);
        }

        ArrayList<Course> courses = new ArrayList<>();
        for (String cName : courseNames) {
            Course c = courseRepository.findByName(cName);
            courses.add(c);
        }
        model.addAttribute("courses", courses);
        return "fragments/results :: resultsTable";
    }


    @GetMapping("/add/{name}/{code}/{year}/{category}/{learningO}/{program}")
    public String add(Model model, @PathVariable("name") String n, @PathVariable("code") String co, @PathVariable("year")
            String y, @PathVariable("category") String ca, @PathVariable("learningO") String lo,
                      @PathVariable("program") String p)
    {
        Category cat = new Category(ca);
        categoryRepository.save(cat);

        Course course =  new Course(Integer.parseInt(y), n, co);
        courseRepository.save(course);

        LearningOutcome outcome = new LearningOutcome(lo, cat);
        outcome.addCourse(course);
        learningOutcomeRepository.save(outcome);

        Program pro = new Program(p);
        pro.addCourse(course);
        programRepository.save(pro);

        this.home(model);
        return "admin";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model)
    {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("learningoutcomes", learningOutcomeRepository.findAll());
        model.addAttribute("programs", programRepository.findAll());
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}

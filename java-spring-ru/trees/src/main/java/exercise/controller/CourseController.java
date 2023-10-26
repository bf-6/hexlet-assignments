package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private static final String DOT_STRING = "\\.";
    private static final String EMPTY_STRING = "";
    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN

    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPrevious(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        String path = course.getPath();

        if (path != null) {
            List<Long> previousCoursesIDs = Arrays.stream(path.split(DOT_STRING))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return courseRepository.findAllById(previousCoursesIDs);
        }
        return null;
    }
    // END

}

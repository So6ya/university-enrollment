package university.repository;

import university.model.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCourseRepository implements CourseRepository {
    private final Map<Long, Course> courses = new HashMap<>();

    @Override
    public void save(Course course) {
        courses.put(course.getId(), course);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }
}
package university.repository;

import university.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    void save(Course course);

    Optional<Course> findById(Long id);

    List<Course> findAll();
}
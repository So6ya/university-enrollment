package university.repository;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;

import java.util.List;

public interface EnrollmentRepository {

    void save(Enrollment enrollment);

    boolean existsByStudentAndCourse(Student student, Course course);

    List<Enrollment> findByStudent(Student student);

    List<Enrollment> findByCourse(Course course);
}
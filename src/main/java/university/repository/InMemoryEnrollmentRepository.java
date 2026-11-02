package university.repository;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryEnrollmentRepository implements EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public boolean existsByStudentAndCourse(Student student, Course course) {
        return enrollments.stream().anyMatch(e -> e.getStudent().getId().equals(student.getId()) && e.getCourse().getId().equals(course.getId())
        );
    }

    @Override
    public List<Enrollment> findByStudent(Student student) {
        return enrollments.stream().filter(e -> e.getStudent().getId().equals(student.getId())).collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> findByCourse(Course course) {
        return enrollments.stream().filter(e -> e.getCourse().getId().equals(course.getId())).collect(Collectors.toList());
    }
}
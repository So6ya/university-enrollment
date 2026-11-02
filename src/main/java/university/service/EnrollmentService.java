package university.service;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;
import university.repository.CourseRepository;
import university.repository.EnrollmentRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class EnrollmentService {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enroll(Student student, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course not found: " + courseId));

        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new IllegalStateException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> getStudentEnrollments(Student student) {
        return enrollmentRepository.findByStudent(student);
    }

    public List<Enrollment> getCourseEnrollments(Course course) {
        return enrollmentRepository.findByCourse(course);
    }
}
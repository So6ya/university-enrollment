package university.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;
import university.repository.CourseRepository;
import university.repository.EnrollmentRepository;
import university.repository.InMemoryCourseRepository;
import university.repository.InMemoryEnrollmentRepository;

class EnrollmentServiceTest {

    private EnrollmentRepository enrollmentRepository;
    private EnrollmentService service;
    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {
        CourseRepository courseRepository = new InMemoryCourseRepository();
        enrollmentRepository = new InMemoryEnrollmentRepository();
        service = new EnrollmentService(courseRepository, enrollmentRepository);

        student = new Student(1L, "Rodya");
        course = new Course(10L, "Rat");

        courseRepository.save(course);
    }

    @Test
    void shouldEnrollStudentInCourse() {
        Enrollment enrollment = service.enroll(student, course.getId());

        assertNotNull(enrollment);
        assertEquals(student, enrollment.getStudent());
        assertEquals(course, enrollment.getCourse());
        assertTrue(enrollmentRepository.existsByStudentAndCourse(student, course));
        assertEquals(1, service.getStudentEnrollments(student).size());
    }

    @Test
    void shouldNotEnrollSameStudentTwice() {
        service.enroll(student, course.getId());

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.enroll(student, course.getId())
        );

        assertEquals("Student already enrolled in this course", exception.getMessage());
        assertEquals(1, service.getStudentEnrollments(student).size());
    }

    @Test
    void shouldAllowDifferentStudentsToEnrollSameCourse() {
        Student second = new Student(2L, "Filya");

        service.enroll(student, course.getId());
        service.enroll(second, course.getId());

        assertEquals(1, service.getCourseEnrollments(course).size());
    }
}
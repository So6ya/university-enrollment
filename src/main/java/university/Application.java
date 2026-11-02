package university;

import university.model.Course;
import university.model.Student;
import university.repository.CourseRepository;
import university.repository.EnrollmentRepository;
import university.repository.InMemoryCourseRepository;
import university.repository.InMemoryEnrollmentRepository;
import university.service.EnrollmentService;

public class Application {
    public static void main(String[] args) {
        CourseRepository courseRepository = new InMemoryCourseRepository();
        EnrollmentRepository enrollmentRepository = new InMemoryEnrollmentRepository();

        EnrollmentService enrollmentService = new EnrollmentService(courseRepository, enrollmentRepository);

        Course course = new Course(1L, "Java");
        courseRepository.save(course);

        Student rodya = new Student(1L, "Rodya");
        Student filya = new Student(2L, "Filya");

        enrollmentService.enroll(rodya, course.getId());
        System.out.println("Rodya enrolled in Course");

        try {
            enrollmentService.enroll(rodya, course.getId());
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        enrollmentService.enroll(filya, course.getId());
        System.out.println("Filya enrolled in Course");

        System.out.println("Students on course: " + enrollmentService.getCourseEnrollments(course).size());
    }
}
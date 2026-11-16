package university.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import university.model.Course;
import university.model.Enrollment;
import university.model.Student;
import university.repository.CourseRepository;
import university.repository.EnrollmentRepository;

public class EnrollmentService {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentService.class);

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(CourseRepository courseRepository,
                             EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enroll(Student student, Long courseId) {
        log.info("Enrollment request received: student={}, courseId={}",
                student == null ? null : student.getName(), courseId);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Enrollment failed: course not found, courseId={}", courseId);
                    return new NoSuchElementException("Course not found: " + courseId);
                });

        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            log.warn("Student {} is already enrolled in course id={}",
                    student.getName(), course.getId());
            throw new IllegalStateException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollmentRepository.save(enrollment);
        log.info("Student {} successfully enrolled in course id={}",
                student.getName(), course.getId());

        return enrollment;
    }

    public List<Enrollment> getStudentEnrollments(Student student) {
        return enrollmentRepository.findByStudent(student);
    }

    public List<Enrollment> getCourseEnrollments(Course course) {
        return enrollmentRepository.findByCourse(course);
    }
}
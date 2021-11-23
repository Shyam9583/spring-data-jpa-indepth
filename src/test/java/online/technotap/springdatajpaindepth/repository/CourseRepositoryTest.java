package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.BaseTest;
import online.technotap.springdatajpaindepth.enitity.Course;
import online.technotap.springdatajpaindepth.enitity.CourseMaterial;
import online.technotap.springdatajpaindepth.enitity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest extends BaseTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CourseRepository courseRepository;

    private Course course;
    private CourseMaterial courseMaterial;
    private Teacher teacher;

    @BeforeEach
    public void setup() {
        courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .build();

        teacher = Teacher.builder()
                .firstName("Mark")
                .lastName("Brown")
                .build();

        course = Course.builder()
                .courseTitle("DSA")
                .credit(6)
                .courseMaterial(courseMaterial)
                .build();
    }

    @Test
    @DisplayName("This should return a list of all courses")
    public void test1() {
        entityManager.persist(course);
        List<Course> courses = courseRepository.findAll();

        assertThat(courses).contains(course);
        assertThat(course.getCourseMaterial()).isEqualTo(courseMaterial);
    }

    @Test
    @DisplayName("This should get a teacher associated with the course")
    public void test2() {
        course.setTeacher(teacher);
        teacher.setCourses(List.of(course));
        entityManager.persist(course);

        Course resultCourse = courseRepository.getById(course.getCourseId());
        assertThat(resultCourse.getTeacher()).isNotNull();

        Teacher resultTeacher = resultCourse.getTeacher();
        assertThat(resultTeacher.getCourses()).contains(course);
    }
}
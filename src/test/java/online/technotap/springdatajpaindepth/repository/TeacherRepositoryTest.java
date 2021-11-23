package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.BaseTest;
import online.technotap.springdatajpaindepth.enitity.Course;
import online.technotap.springdatajpaindepth.enitity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeacherRepositoryTest extends BaseTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TeacherRepository teacherRepository;

    private Teacher teacher;
    private List<Course> courseList;

    @BeforeEach
    private void setup() {
        teacher = Teacher.builder()
                .firstName("Vipin")
                .lastName("Chopra")
                .build();
        teacher = entityManager.persist(teacher);
        courseList = List.of(
                Course.builder()
                        .courseTitle("DSA")
                        .credit(6)
                        .teacher(teacher)
                        .build(),
                Course.builder()
                        .courseTitle("System Design")
                        .credit(4)
                        .teacher(teacher)
                        .build()
        );
        for (var course : courseList) entityManager.persist(course);
    }

    @Test
    @DisplayName("Given a courseTitle, it should return a teacher whose any courses match that courseTitle")
    public void test1() {
        Long desiredId = teacher.getTeacherId();
        String courseTitle = courseList.get(0).getCourseTitle();
        Teacher result = teacherRepository.findByCourseTitle(courseTitle);
        assertThat(result.getTeacherId()).isEqualTo(desiredId);
    }
}
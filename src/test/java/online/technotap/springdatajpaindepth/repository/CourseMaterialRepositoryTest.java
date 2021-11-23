package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.BaseTest;
import online.technotap.springdatajpaindepth.enitity.Course;
import online.technotap.springdatajpaindepth.enitity.CourseMaterial;
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
class CourseMaterialRepositoryTest extends BaseTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    private Course course;
    private CourseMaterial courseMaterial;

    @BeforeEach
    public void setup() {
        course = Course.builder()
                .courseTitle("DSA")
                .credit(6)
                .build();

        courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        courseMaterial = entityManager.persistAndFlush(courseMaterial);
    }

    @Test
    @DisplayName("This should return the list of courseMaterials")
    public void test1() {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();

        assertThat(courseMaterials).contains(courseMaterial);
        assertThat(courseMaterial.getCourse().getCredit()).isEqualTo(course.getCredit());
    }

    @Test
    @DisplayName("This should return the courseMaterial only")
    public void test2() {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        assertThat(courseMaterials).contains(courseMaterial);
        assertThat(courseMaterial.getCourse()).isNotNull();
    }
}
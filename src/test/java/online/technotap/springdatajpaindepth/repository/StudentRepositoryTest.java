package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.BaseTest;
import online.technotap.springdatajpaindepth.enitity.Guardian;
import online.technotap.springdatajpaindepth.enitity.Student;
import online.technotap.springdatajpaindepth.util.SkipBeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StudentRepositoryTest extends BaseTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup(TestInfo info) {
        var guardian = Guardian.builder()
                .name("Dulari Sahoo")
                .mobile("8658155518")
                .build();

        student = Student.builder()
                .firstName("Shyam")
                .emailAddress("strikerallin1@gmail.com")
                .guardian(guardian)
                .build();

        if (checkToSkip(info)) return;
        student = entityManager.persist(student);
    }

    private boolean checkToSkip(TestInfo info) {
        if (info.getTestMethod().isEmpty()) return true;
        var annotations = info.getTestMethod().get().getDeclaredAnnotations();
        for (var annotation : annotations) {
            if (annotation.annotationType() == SkipBeforeEach.class) return true;
        }
        return false;
    }

    @Test
    @DisplayName("Given a student entity, it should save the student in the database")
    @SkipBeforeEach
    public void test1() {
        Student result = studentRepository.save(student);
        Student savedStudent = entityManager.find(Student.class, result.getStudentId());

        assertThat(result).isEqualTo(savedStudent);
    }

    @Test
    @DisplayName("This should return list of all student entities present")
    public void test2() {
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("This should return list of students whose firstName is equal to input")
    public void test3() {
        String firstName = "Shyam";

        List<Student> studentList = studentRepository.findAllByFirstName(firstName);
        assertThat(student).isIn(studentList);
    }

    @Test
    @DisplayName("This should return list of students whose firstName contains the input text")
    public void test4() {
        String text = "am";

        List<Student> studentList = studentRepository.findAllByFirstNameContaining(text);
        assertThat(student).isIn(studentList);
    }

    @Test
    @DisplayName("This should return empty list if every student's last name is null")
    public void test5() {
        List<Student> studentList = studentRepository.findAllByLastNameNotNull();
        assertThat(studentList.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("This should return list of students whose guardian's name is given")
    void test6() {
        String guardianName = "Dulari Sahoo";
        List<Student> studentList = studentRepository.findAllByGuardianName(guardianName);
        assertThat(studentList.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("This should update the firstName of the student, given update and emailAddress")
    void test7() {
        String newFirstName = "Sam", emailAddress = "strikerallin1@gmail.com";

        int changeCount = studentRepository.updateStudentNameByEmailAddress(newFirstName, emailAddress);
        Student updatedStudent = entityManager.find(Student.class, student.getStudentId());

        assertThat(changeCount).isEqualTo(1);
        assertThat(updatedStudent.getFirstName()).isEqualTo(newFirstName);
    }
}

package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.enitity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.firstName = ?1")
    List<Student> findAllByFirstName(String firstName);

    @Query("select s from Student s where s.firstName like %?1%")
    List<Student> findAllByFirstNameContaining(String name);

    @Query("select s from Student s where s.lastName is not null")
    List<Student> findAllByLastNameNotNull();

    @Query("select s from Student s where s.guardian.name = ?1")
    List<Student> findAllByGuardianName(String guardianName);

    @Modifying(clearAutomatically = true)
    @Query("update Student s set s.firstName = ?1 where s.emailAddress = ?2")
    int updateStudentNameByEmailAddress(String firstName, String emailAddress);
}

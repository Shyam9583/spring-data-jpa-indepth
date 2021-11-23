package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.enitity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    //    @Query(
//            value = "select distinct t.* from teacher t inner join course c on t.teacher_id = c.teacher_id where c.course_title = ?1",
//            nativeQuery = true
//    )
    @Query("select t from Teacher t join t.courses tc where tc.courseTitle = ?1")
    Teacher findByCourseTitle(String courseTitle);
}

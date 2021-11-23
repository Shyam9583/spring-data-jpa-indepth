package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.enitity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

package online.technotap.springdatajpaindepth.repository;

import online.technotap.springdatajpaindepth.enitity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}

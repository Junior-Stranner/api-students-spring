package br.com.jujubaprojects.studensapi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jujubaprojects.studensapi.Model.Student;

public interface StudentRepository extends JpaRepository<Student , Integer>{

    int countById(Long id);

    boolean existsByRegistration(String registration);
    
   
    @Query("SELECT AVG((s.note1 + s.note2) / 2) FROM Student s WHERE s.id = ?1")
    Double findAverageNoteByStudentId(Long studentId);
    

    Optional<Student> findById(Long id);
}

package br.com.jujubaprojects.studensapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.studensapi.Model.Student;

public interface StudentRepository extends JpaRepository<Student , Integer>{

    int countByKey(int key);

    
    boolean existsByRegistration(int registration);

}

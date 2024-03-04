package br.com.jujubaprojects.studensapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.studensapi.Model.StudentList;


public interface StudentListRepository extends JpaRepository<StudentList,Long>{

    StudentList findByName(String name);

}

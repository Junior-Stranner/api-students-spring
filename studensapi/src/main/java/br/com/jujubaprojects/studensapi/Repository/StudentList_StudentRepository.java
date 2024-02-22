package br.com.jujubaprojects.studensapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jujubaprojects.studensapi.Model.StudentList_Student;
import br.com.jujubaprojects.studensapi.Model.pk_StudentList_Student;

@Repository
public interface StudentList_StudentRepository extends JpaRepository<pk_StudentList_Student, Void> {

    void createAssociation();

    void save(StudentList_Student association);


}


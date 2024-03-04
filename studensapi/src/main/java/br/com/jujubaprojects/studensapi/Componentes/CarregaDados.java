/*package br.com.jujubaprojects.studensapi.Componentes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import br.com.jujubaprojects.studensapi.Model.StudentList;
import br.com.jujubaprojects.studensapi.Repository.StudentListRepository;

public class CarregaDados implements CommandLineRunner{

      
    @Autowired
    StudentListRepository studentListRepository;

    @Override
    public void run(String... args) throws Exception {
        String[] studentLists = {"APPROVED", "DISAPPROVED", "RECOVERY"};

        for (String studentListString : studentLists) {
            StudentList studentList = studentListRepository.findByNome(studentListString);
            if (studentList == null) {
                studentList = new StudentList( studentListString);
                this.studentListRepository.save(studentList);
            }
        }
    }
}*/

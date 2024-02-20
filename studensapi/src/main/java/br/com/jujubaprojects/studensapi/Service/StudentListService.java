package br.com.jujubaprojects.studensapi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.studensapi.DTO.StudentListDTO;
import br.com.jujubaprojects.studensapi.Model.StudentList;
import br.com.jujubaprojects.studensapi.Repository.StudentListRepository;

@Service
public class StudentListService {

    @Autowired
    StudentListRepository studentListRepository;
    

    public List<StudentListDTO> findAllList(){
      List<StudentList>  studentLists = this.studentListRepository.findAll();
        return  studentLists.stream().map(x -> new StudentListDTO(x)).toList();
    }

    public StudentListDTO createSTudentList(StudentListDTO studentListDTO){
    List<StudentList> studentListDTOs = this.studentListRepository.findAll();
    boolean existingStudentList = studentListDTOs.stream().anyMatch(list -> list.getName().equals(studentListDTO.getName()));   

     if(existingStudentList){
        return  ResponseEntity().badRequest("Student list already exist !!");
     }
     return null;
    }
}

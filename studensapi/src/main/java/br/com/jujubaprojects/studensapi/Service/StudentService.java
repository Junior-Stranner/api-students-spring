package br.com.jujubaprojects.studensapi.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.studensapi.DTO.StudentDTO;
import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.Repository.StudentRepository;
//import br.com.jujubaprojects.studensapi.mapper.DozerMapper;

@Service
public class StudentService {
    
    @Autowired 
    StudentRepository studentRepository;


  public List<StudentDTO> allStudents() {
      List<Student> entities = this.studentRepository.findAll();
    //  List<StudentDTO> studentDTOs = DozerMapper.parseObject(entities, StudentDTO.class);
      return studentRepository.findAll().stream().map(StudentDTO::new).collect(Collectors.toList());
      //return entities.stream().map(x -> new StudentDTO(x)).toList;


 //   return studentDTOs;
}
    
   public ResponseEntity<?> create(Student student){
        List<Student> students = this.studentRepository.findAll();
        final boolean existingStudent = students.stream().anyMatch(studentExist -> studentExist.getRegistration().equals(student.getRegistration()));
        return ;

   }
}

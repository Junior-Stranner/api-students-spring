package br.com.jujubaprojects.studensapi.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.jujubaprojects.studensapi.DTO.StudentDTO;
import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.Repository.StudentRepository;
//import br.com.jujubaprojects.studensapi.mapper.DozerMapper;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
    
    @Autowired 
    StudentRepository studentRepository;

    public List<StudentDTO> allStudents() {
       this.studentRepository.findAll();
    //  List<StudentDTO> studentDTOs = DozerMapper.parseObject(entities, StudentDTO.class);
      return studentRepository.findAll().stream().map(StudentDTO::new).collect(Collectors.toList());
      //return entities.stream().map(x -> new StudentDTO(x)).toList;
 }
    

   public Student create(Student student) {
  //   List<Student> students = this.studentRepository.findAll();
    /*  boolean existingStudent = students.stream().anyMatch(existingStudent -> existingStudent.getRegistration().equals(student.getRegistration()));

    if(existingStudent) {
        throw new IllegalArgumentException("Student with the same Registration already exist !");
    } else {
        return this.studentRepository.save(student);
      }*/
      if (studentRepository.existsByRegistration(student.getRegistration())) {
        throw new RuntimeException("Já existe um aluno cadastrado com a mesma matrícula.");
    }else{

        //calcula a média do aluno através das duas notas no banco de dados
        double avargeNote = this.studentRepository.findAverageNote();

      // Salvar o aluno se a matrícula for única
      Student savedStudent = this.studentRepository.save(student);

    return  savedStudent;
    }
   }

   public int quantityRegsitration(Long id){
    if(this.studentRepository.countById(id) != 0)
    return  this.studentRepository.countById(id);

     else{
        throw new NullPointerException("no records registered !");

     }
   }

   public StudentDTO findByIdStudent(Long id){

    if (this.studentRepository.countById(id) != 0){
          
        Student student = this.studentRepository.findById(id).get();
        return new StudentDTO(student);
    }else{ 

    throw new NullPointerException() ;
    }
   }

   public StudentDTO upateStudent(StudentDTO studentDTO){

    Optional<Student> optionalStudent = this.studentRepository.findById(studentDTO.getKey());

      if(optionalStudent.isPresent()){
        Student existingStudent = optionalStudent.get();

        existingStudent.setFirstname(studentDTO.getFirstname());
        existingStudent.setNote1(studentDTO.getNote1());
        existingStudent.setNote2(studentDTO.getNote2());

     Student student = this.studentRepository.save(existingStudent);
        return new StudentDTO(student);
    } else{
        throw new EntityNotFoundException("student not found !");
    }
   
   }
   public void deleteStudent( Long id){
    Student deleteStudent = this.studentRepository.findById(id).get();
    this.studentRepository.delete(deleteStudent);
   }

}

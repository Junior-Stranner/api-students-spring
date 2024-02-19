package br.com.jujubaprojects.studensapi.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.studensapi.Controller.StudentController;
import br.com.jujubaprojects.studensapi.DTO.StudentDTO;
import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.Repository.StudentRepository;
//import br.com.jujubaprojects.studensapi.mapper.DozerMapper;
import br.com.jujubaprojects.studensapi.exeptions.ResourceNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
    
    @Autowired 
    StudentRepository studentRepository;

    @SuppressWarnings("null")
    public List<StudentDTO> allStudents() {
        // Obter todos os estudantes do repositório
        List<Student> students = this.studentRepository.findAll();
        
        // Calcular a média das notas de todos os estudantes
        for (Student student : students) {
            double average = (student.getNote1() + student.getNote2()) / 2;
            student.setAverage(average);
            student.setStatus(student.resultStudent());
        }
        // Mapear os estudantes para DTOs e adicionar a média a cada DTO
        List<StudentDTO> studentDTOs = students.stream()
                                               .map(student -> {
                                                   StudentDTO dto = new StudentDTO(student);
                                                   dto.setAverage(student.getAverage());
                                                   return dto;
                                               })
                                               .collect(Collectors.toList());
                                          
		// Adiciona links de auto-relacionamento usando HATEOAS
		studentDTOs.forEach(s -> s.add(linkTo(methodOn(StudentController.class).findById(s.getId())).withSelfRel()));
	
        return studentDTOs;
    }
    

   @SuppressWarnings("null")
public ResponseEntity<Student> create(Student student) {
  //   List<Student> students = this.studentRepository.findAll();
    /*  boolean existingStudent = students.stream().anyMatch(existingStudent -> existingStudent.getRegistration().equals(student.getRegistration()));

    if(existingStudent) {
        throw new IllegalArgumentException("Student with the same Registration already exist !");
    } else {
        return this.studentRepository.save(student);
      }*/
      if (studentRepository.existsByRegistration(student.getRegistration())) {
        return ResponseEntity.badRequest().build();
    }else{
           student.setBirthDay(LocalDate.of(2000, 1, 1)); // Exemplo de data de nascimento
           System.out.println(student.getBirthDay()); // Isso imprimirá "null"

      // Salvar o aluno se a matrícula for única
      Student savedStudent = this.studentRepository.save(student);

     //calcula a média e joga para a varriável avarge
     double average = this.studentRepository.findAverageNote();

     //adiciono a média para o aluno salvo 
     savedStudent.setAverage(average);
     savedStudent.setStatus(savedStudent.resultStudent());

     student.add(linkTo(methodOn(StudentController.class).findById(student.getId())).withSelfRel());


     //retorno o aluno salvo com a média
    return  ResponseEntity.ok(savedStudent);
    }
   }

   public int quantityRegsitration(Long id){
    if(this.studentRepository.countById(id) != 0)
    return  this.studentRepository.countById(id);
     else{
        throw new NullPointerException("no records registered !");

     }
   }

   @SuppressWarnings("null")
public Student findByIdStudent(Long id) {
    Student existingStudent = this.studentRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("No person found with ID: " + id));

   // if (optionalStudent.isPresent()) {
    //    Student student = optionalStudent.get();
        
        // Obter a média das notas do aluno
        double average = this.studentRepository.findAverageNote();
        
        // Criar uma entity para o aluno com a média das notas atribuída
        existingStudent.setAverage(average);

        existingStudent.add(linkTo(methodOn(StudentController.class).findById(id)).withSelfRel());

        return existingStudent;
  /*   } else {
        throw new EntityNotFoundException("Student not found with id: " + existingStudent);
    }*/
}


   @SuppressWarnings("null")
public StudentDTO upateStudent(StudentDTO studentDTO){

    Optional<Student> optionalStudent = this.studentRepository.findById(studentDTO.getId());

      if(optionalStudent.isPresent()){
        Student existingStudent = optionalStudent.get();

        existingStudent.setFirstname(studentDTO.getFirstname());
        existingStudent.setNote1(studentDTO.getNote1());
        existingStudent.setNote2(studentDTO.getNote2());
        existingStudent.setAverage(studentDTO.getAverage());
        existingStudent.setStatus(studentDTO.getStatus());

     Student student = this.studentRepository.save(existingStudent);

     student.add(linkTo(methodOn(StudentController.class).findById(student.getId())).withSelfRel());

        return new StudentDTO(student);
    } else{
        throw new EntityNotFoundException("student not found !");
    }
   
   }
   @SuppressWarnings("null")
public void deleteStudent(Long id){
    Student deleteStudent = this.studentRepository.findById(id).get();
    this.studentRepository.delete(deleteStudent);
   }

}

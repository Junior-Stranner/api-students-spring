package br.com.jujubaprojects.studensapi.Service;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.studensapi.Controller.StudentController;
import br.com.jujubaprojects.studensapi.Controller.StudentListController;
import br.com.jujubaprojects.studensapi.DTO.StudentListDTO;
import br.com.jujubaprojects.studensapi.Model.StudentList;
import br.com.jujubaprojects.studensapi.Repository.StudentListRepository;
import br.com.jujubaprojects.studensapi.Repository.StudentRepository;
import br.com.jujubaprojects.studensapi.exeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class StudentListService {

    @Autowired
    StudentListRepository studentListRepository;

    @Autowired
    StudentRepository studentRepository;

    @SuppressWarnings("null")
    public List<StudentListDTO> findAllList(){
      List<StudentList>  studentLists = this.studentListRepository.findAll();

       // Mapear os objetos StudentList para StudentListDTO
     List<StudentListDTO> studentListDTOs = studentLists.stream()
            .map(StudentListDTO::new)
            .collect(Collectors.toList());

      studentListDTOs.forEach(s -> s.add(linkTo(methodOn(StudentListController.class).findById(s.getId())).withSelfRel()));

        return studentListDTOs;
    }

    public ResponseEntity<?> createStudentList(StudentListDTO studentListDTO) {
        List<StudentList> studentLists = this.studentListRepository.findAll();
        boolean existingStudentList = studentLists.stream()
            .anyMatch(list -> list.getName().equals(studentListDTO.getName()));   
    
        if (existingStudentList) {
            return ResponseEntity.badRequest().body("Student list with the same name already exists!");
        } else {
            // Criando uma nova instância de StudentList a partir do DTO
            StudentList newStudentList = new StudentList();
            newStudentList.setName(studentListDTO.getName());
            // Você pode definir outros atributos aqui conforme necessário
            // Salvando a nova lista de estudantes
            StudentList createdStudentList = this.studentListRepository.save(newStudentList);
    
            new StudentListDTO(createdStudentList);
            // Retornando uma resposta de sucesso
            return ResponseEntity.ok().body("Student list created successfully!");
        }
    }

   /* public StudentListDTO addStudentToList(long studentId, long studentListId) {
        // Encontre o estudante e a lista de estudantes pelos seus IDs
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<StudentList> studentListOptional = studentListRepository.findById(studentListId);
    
        if (studentOptional.isPresent() && studentListOptional.isPresent()) {
            Student student = studentOptional.get();
            StudentList studentList = studentListOptional.get();
    
            // Adicione o estudante à lista de estudantes
            studentList.getStudents().add(student);
            studentListRepository.save(studentList);
    
            return new StudentListDTO(studentList); // ou qualquer coisa que você queira retornar
        } else {
            throw new EntityNotFoundException("Student or StudentList not found");
        }
    }    */

      @SuppressWarnings("null")
   public StudentListDTO updateStudent(StudentList studentList){
    Optional<StudentList> optinalList = this.studentListRepository.findById(studentList.getId());

    if(optinalList.isPresent()){
        StudentList existingStudentList = optinalList.get();

        existingStudentList.setName(studentList.getName());
        existingStudentList.setStudents(studentList.getStudents());

        StudentList updatedStudentList = this.studentListRepository.save(existingStudentList); // Salvando as alterações no banco de dados

        updatedStudentList.add(linkTo(methodOn(StudentController.class).findById(updatedStudentList.getId())).withSelfRel());

        return new StudentListDTO(updatedStudentList); // Retornando o estudante atualizado
    } else {
        throw new EntityNotFoundException("StudentList not found with ID: " + studentList.getId());

    }
   }
  /*   public StudentListDTO addStudentToList(long studentId, long studentListId) {
        Set<Student> studentSet = null;
        StudentList studentList = studentListRepository.findById(studentListId).get();
        Student student = studentRepository.findById(studentId).get();

        studentSet =  studentList.getStudents();
        studentSet.add(student);
        studentList.setStudents(studentSet);
        StudentList addStudentToList = studentListRepository.save(studentList);
        return new StudentListDTO(addStudentToList);
    }*/


    @SuppressWarnings("null")
    public StudentListDTO findByIdStudentList(Long id) {
        // Buscar a lista de estudantes pelo ID
        StudentList existingStudentList = this.studentListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Student list found with ID: " + id));
        // Converter a entidade para DTO
        StudentListDTO studentListDTO = new StudentListDTO(existingStudentList);

        studentListDTO.add(linkTo(methodOn(StudentController.class).findById(id)).withSelfRel());

        // Retornando o DTO
        return studentListDTO;
    }


@SuppressWarnings("null")
public void deleteStudentList(Long id){
    StudentList deleteStudentList = this.studentListRepository.findById(id).get();
    this.studentListRepository.delete(deleteStudentList);
   }

}
    

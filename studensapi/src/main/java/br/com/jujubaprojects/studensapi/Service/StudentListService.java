package br.com.jujubaprojects.studensapi.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.studensapi.Controller.StudentController;
import br.com.jujubaprojects.studensapi.DTO.StudentListDTO;
import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.Model.StudentList;
import br.com.jujubaprojects.studensapi.Repository.StudentListRepository;
import br.com.jujubaprojects.studensapi.exeptions.ResourceNotFoundException;

@Service
public class StudentListService {

    @Autowired
    StudentListRepository studentListRepository;
    

    public List<StudentListDTO> findAllList(){
      List<StudentList>  studentLists = this.studentListRepository.findAll();
        return  studentLists.stream().map(x -> new StudentListDTO(x)).toList();
    }

    public ResponseEntity<?> createStudentList(StudentListDTO studentListDTO) {
        List<StudentList> studentLists = this.studentListRepository.findAll();
        boolean existingStudentList = studentLists.stream()
            .anyMatch(list -> list.getName().equals(studentListDTO.getName()));   
    
        if (existingStudentList) {
            return ResponseEntity.badRequest().body("Student list already exists!");
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

    public StudentListDTO findByIdStudentList(Long id) {
        // Buscar a lista de estudantes pelo ID
        StudentList existingStudentList = this.studentListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Student list found with ID: " + id));
        // Converter a entidade para DTO
        StudentListDTO studentListDTO = new StudentListDTO(existingStudentList);
        // Retornando o DTO
        return studentListDTO;
    }
    

}
    

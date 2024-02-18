package br.com.jujubaprojects.studensapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.studensapi.DTO.StudentDTO;
import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.Service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    
    @GetMapping
    public List<StudentDTO> findAll(){
        return this.studentService.allStudents();
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid Student student){
        return this.studentService.create(student);

    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id){
        return this.studentService.findByIdStudent(id);
    }

    @PutMapping
    public StudentDTO updateStudent(StudentDTO studentDTO){
        return this.studentService.upateStudent(studentDTO);
    }

    @GetMapping("/count")
    public int countRegister(Long id){
        return this.studentService.quantityRegsitration(id);

    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") long id){
        this.studentService.deleteStudent(id);
    }

}

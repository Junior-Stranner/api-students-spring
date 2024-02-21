package br.com.jujubaprojects.studensapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.studensapi.DTO.StudentListDTO;
import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.Service.StudentListService;
import br.com.jujubaprojects.studensapi.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student/list")
@Tag(name = "StudentList", description = "Endpoints for Managing Student list")
public class StudentListController {

    @Autowired
    StudentListService studentListService;

    @Autowired
    StudentService studentService;
    
     @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Finds all Student lists", description = "Finds all Student lists",
		tags = {"StudentList"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = {
					@Content(
						mediaType = "application/json",
						array = @ArraySchema(schema = @Schema(implementation = StudentListDTO.class))
					)
				}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	  }
	)
    public List<StudentListDTO> findAll(){
        return this.studentListService.findAllList();
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Adds a new Student list",
		description = "Adds a new Student by passing in a JSON, XML or YML representation of the Student list!",
		tags = {"StudentList"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = Student.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
    public ResponseEntity<?> create(@RequestBody @Valid StudentListDTO studentListDTO){
        return this.studentListService.createStudentList(studentListDTO);

    }

    @PostMapping("/addStudents/{studentListId}")
    public ResponseEntity<StudentListDTO> addStudentsToList(@PathVariable Long studentListId, @RequestBody List<Student> student) {
        StudentListDTO studentListDTO = studentListService.addStudentsToList(student, studentListId);
        return new ResponseEntity<>(studentListDTO, HttpStatus.OK);
    }
    

    
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds a Student list", description = "Finds a Student list",
    tags = {"StudentList"},
    responses = {
          @ApiResponse(description = "Success", responseCode = "200",
              content = @Content(schema = @Schema(implementation = Student.class))
          ),
          @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
      }
  )
   public StudentListDTO findById(@PathVariable("id") Long id){
        return this.studentListService.findByIdStudentList(id);
    }

    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Student List",
		description = "Deletes a Student by passing in a JSON, XML or YML representation of the List!",
		tags = {"Student"},
		responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
    public void deleteStudent(@PathVariable("id") long id){
        this.studentListService.deleteStudentList(id);
    }
}

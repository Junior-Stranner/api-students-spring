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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student", description = "Endpoints for Managing Student")
public class StudentController {

    @Autowired
    StudentService studentService;

  /*   @Operation(summary = "Finds all Students",
           tags = {"Student"},
           responses = {
               @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                              array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class)))),
               @ApiResponse(description = "Bad Request", responseCode = "400"),
               @ApiResponse(description = "Unauthorized", responseCode = "401"),
               @ApiResponse(description = "Not Found", responseCode = "404"),
               @ApiResponse(description = "Internal Error", responseCode = "500")
           }
)*/
    
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Finds all Students", description = "Finds all Students",
		tags = {"Student"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = {
					@Content(
						mediaType = "application/json",
						array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class))
					)
				}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	  }
	)
    public List<StudentDTO> findAll(){
        return this.studentService.allStudents();
    }


    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Adds a new Student",
		description = "Adds a new Student by passing in a JSON, XML or YML representation of the Student!",
		tags = {"Student"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = Student.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
    public ResponseEntity<Student> create(@RequestBody @Valid Student student){
        return this.studentService.create(student);

    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds a Student", description = "Finds a Student",
    tags = {"Student"},
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
    public Student findById(@PathVariable("id") Long id){
        return this.studentService.findByIdStudent(id);
    }

    @PutMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates a Student",
		description = "Updates a Student by passing in a JSON, XML or YML representation of the person!",
		tags = {"Student"},
		responses = {
			@ApiResponse(description = "Updated", responseCode = "200",
				content = @Content(schema = @Schema(implementation = StudentDTO.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO){
        return this.studentService.updateStudent(studentDTO);
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adds a new Student",
		description = "Adds a new Student by passing in a JSON, XML or YML representation of the person!",
		tags = {"Student"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200",
				content = @Content(schema = @Schema(implementation = Student.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		}
	)
    public int countRegister(Long id){
        return this.studentService.quantityRegsitration(id);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Student",
		description = "Deletes a Student by passing in a JSON, XML or YML representation of the person!",
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
        this.studentService.deleteStudent(id);
    }

}

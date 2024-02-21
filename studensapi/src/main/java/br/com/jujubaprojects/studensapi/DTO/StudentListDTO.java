package br.com.jujubaprojects.studensapi.DTO;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import br.com.jujubaprojects.studensapi.Model.StudentList;

public class StudentListDTO extends RepresentationModel<StudentListDTO> {
    
    private long id;
    private String name;
    private List<Long> studentIds;


    
    public StudentListDTO() {
       
    }

    public StudentListDTO(StudentList entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setstudentIds(List<Long> studentIds) {
        studentIds = studentIds;
    }

    
}

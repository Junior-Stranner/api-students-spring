package br.com.jujubaprojects.studensapi.DTO;

import org.springframework.hateoas.RepresentationModel;

import br.com.jujubaprojects.studensapi.Model.StudentList;

public class StudentListDTO extends RepresentationModel<StudentListDTO> {
    
    private long id;
    private String name;

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
  

    
}

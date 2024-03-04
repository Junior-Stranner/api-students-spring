package br.com.jujubaprojects.studensapi.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Student_list")
public class StudentList extends RepresentationModel<StudentList>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "studentList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students ;

    
    public StudentList() {
    }


    public StudentList( String name) {
        this.name = name;
        this.students = new ArrayList<>();
     }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentList other = (StudentList) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }




    public List<Student> getStudents() {
        return students;
    }




    public void setStudents(List<Student> students) {
        this.students = students;
    }


    @Override
    public String toString() {
        return "StudentList [id=" + id + ", name=" + name + ", students=" + students + "]";
    }


    public void addStudent(Student student) {
       this.students.add(student);
   }

}

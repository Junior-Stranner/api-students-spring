package br.com.jujubaprojects.studensapi.Model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Student_list")
public class StudentList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

   // private List<Long> studentIds;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "student_list_student",
           joinColumns = @JoinColumn(name = "student_list_id"),
           inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonIgnoreProperties("studentLists")
    private Set<Student> students = new HashSet<>();


    
    public StudentList() {
    }


    public StudentList( String name) {
        this.name = name;
     //   this.studentIds = studentIds;
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




    public Set<Student> getStudents() {
        return students;
    }




    public void setStudents(Set<Student> students) {
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

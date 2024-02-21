package br.com.jujubaprojects.studensapi.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aux_list_student")
public class StudentList_Student {
    

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student ;

    @ManyToOne()
    @JoinColumn(name = "StudentList_id")
    private StudentList studentList;

    

    public StudentList_Student() {
     
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }
 

    
}

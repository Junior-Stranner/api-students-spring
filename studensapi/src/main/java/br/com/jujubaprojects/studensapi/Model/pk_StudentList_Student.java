package br.com.jujubaprojects.studensapi.Model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class pk_StudentList_Student {
    
    

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student ;

    @ManyToOne()
    @JoinColumn(name = "StudentList_id")
    private StudentList studentList;

    public pk_StudentList_Student(Student student, StudentList studentList) {
      this.student = student;
      this.studentList = studentList;
    }

    public pk_StudentList_Student() {
     
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

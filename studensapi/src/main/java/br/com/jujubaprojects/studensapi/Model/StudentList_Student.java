package br.com.jujubaprojects.studensapi.Model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "aux_list_student")
public class StudentList_Student {
    

      @EmbeddedId
      private pk_StudentList_Student id = new pk_StudentList_Student();


      

    public StudentList_Student() {
    }


    public StudentList_Student(Student student, StudentList studentList) {
        this.id.setStudent(student);
        this.id.setStudentList(studentList);
    }

    public pk_StudentList_Student getId() {
        return id;
    }

    public void setStudent(Student student) {
		id.setStudent(student);
	}

	public Student getStudent() {
		return id.getStudent();
	}

	public void setStudentList(StudentList studentList) {
		id.setStudentList(studentList);
	}

	public StudentList getStudentList() {
		return id.getStudentList();
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
        StudentList_Student other = (StudentList_Student) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

      
}

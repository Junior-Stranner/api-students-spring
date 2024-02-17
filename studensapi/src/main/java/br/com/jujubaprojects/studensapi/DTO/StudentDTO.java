package br.com.jujubaprojects.studensapi.DTO;


import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.enums.StudentStatus;


@JsonPropertyOrder({"id", "firstName", "status", "note1","note2", "average"})
public class StudentDTO implements Serializable{

    private static final long SerializableUID = 1L;
    
    @JsonProperty("id")
    @Mapping("id") // Mapping apensa funciona com DozerMApper
    private Long Key;
    private String firstname;
    private StudentStatus status;
    private double note1 , note2;
    private double average;


    public StudentDTO(Long key, String firstname, StudentStatus status, double note1, double note2, double average) {
        Key = key;
        this.firstname = firstname;
        this.status = status;
        this.note1 = note1;
        this.note2 = note2;
    }

    public StudentDTO(Student student) {
        BeanUtils.copyProperties(student, this);

    }


    public Long getKey() {
        return Key;
    }
    public void setKey(Long key) {
        Key = key;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public StudentStatus getStatus() {
        return status;
    }
    public void setStatus(StudentStatus status) {
        this.status = status;
    }
    public double getNote1() {
        return note1;
    }
    public void setNote1(double note1) {
        this.note1 = note1;
    }
    public double getNote2() {
        return note2;
    }
    public void setNote2(double note2) {
        this.note2 = note2;
    }
    public double getAverage() {
        return average;
    }
    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((Key == null) ? 0 : Key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentDTO other = (StudentDTO) obj;
        if (Key == null) {
            if (other.Key != null)
                return false;
        } else if (!Key.equals(other.Key))
            return false;
        return true;
    }




    
}

package br.com.jujubaprojects.studensapi.Model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.jujubaprojects.studensapi.enums.StudentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_student")
@JsonPropertyOrder({"id", "firstName", "lastname", "registration", "year", "gender", "birthday", "note1", "note2", "average", "status"})
public class Student {

   // private static final long SerializableUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Enter the student's first name")
    private String firstname;

    @NotBlank(message = "Enter the student's last name")
    private String lastname;

    @NotBlank(message = "Enter the student's registration")
    private String registration;

    @NotBlank(message = "Enter the gender the students gender")
    private String gender;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @Min(0)
    @Max(10)
    private double note1 , note2;

    private double average;

    public Student(){}

    public Long getId() {
        return id;
    }

    public Long setId(Long id) {
        return this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
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
    public String toString() {
        return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", registration="
                + registration + ", gender=" + gender + ", birthDay=" + birthDay + ", status="
                + status + ", note1=" + note1 + ", note2=" + note2 + ", average=" + average + "]";
    }

    
}

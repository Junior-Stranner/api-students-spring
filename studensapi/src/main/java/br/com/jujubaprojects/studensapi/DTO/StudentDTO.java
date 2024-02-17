package br.com.jujubaprojects.studensapi.DTO;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import br.com.jujubaprojects.studensapi.Model.Student;
import br.com.jujubaprojects.studensapi.enums.StudentStatus;


@JsonPropertyOrder({"id", "firstName", "status", "note1","note2", "average"})
public class StudentDTO  {

  //  private static final long SerializableUID = 1L;
    
 //  @JsonProperty("id")
 //  @Mapping("id") // Mapping apensa funciona com DozerMApper
    private Long id;
    private String firstname;
    private StudentStatus status;
    private double note1 , note2;
    private double average;

    public StudentDTO(){
        
    }

    public StudentDTO(Long id, String firstname, StudentStatus status, double note1, double note2, double average) {
        this.id = id;
        this.firstname = firstname;
        this.status = status;
        this.note1 = note1;
        this.note2 = note2;
    }

    public StudentDTO(Student student) {
        BeanUtils.copyProperties(student, this);

    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}

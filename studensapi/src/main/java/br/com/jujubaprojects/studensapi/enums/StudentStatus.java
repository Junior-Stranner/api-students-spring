package br.com.jujubaprojects.studensapi.enums;

public enum StudentStatus {   
   APPROVED,
   DISAPPROVED,
   RECOVERY;


    private String status;

    public String getStatus() {
        return status;
    }
    
}

package br.com.jujubaprojects.studensapi.exeptions;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String ex){
     super(ex);
    }
}

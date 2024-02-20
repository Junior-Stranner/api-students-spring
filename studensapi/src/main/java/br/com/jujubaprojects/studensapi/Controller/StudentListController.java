package br.com.jujubaprojects.studensapi.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student", description = "Endpoints for Managing Student")
public class StudentListController {
    
}

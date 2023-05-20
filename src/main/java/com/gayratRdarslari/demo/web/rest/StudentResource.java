package com.gayratRdarslari.demo.web.rest;

import com.gayratRdarslari.demo.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {

//    @GetMapping("/students")
//    @RequestMapping(value = "/students", method = RequestMethod.GET)
//    public String hello(){
//        return "Hello World";
//    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity getOne(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam String lastName,
                                 @RequestParam String course){
        Student student = new Student(id, name, lastName, course);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/student/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody Student student){
        Student student1 = new Student(1L, "El", "Nmov", "IT");
        student1.setId(id);
        student1.setLastName(student.getLastName());
        student1.setCourse(student.getCourse());
        return ResponseEntity.ok(student);
    }
}

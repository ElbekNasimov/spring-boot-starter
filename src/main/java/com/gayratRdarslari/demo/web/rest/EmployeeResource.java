package com.gayratRdarslari.demo.web.rest;

import com.gayratRdarslari.demo.domain.Employee;
import com.gayratRdarslari.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    create new iten
    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody Employee employee){
        Employee employee1 = employeeService.save(employee);
        return ResponseEntity.ok(employee1);
    }

//    update the current item
    @PutMapping("/employees")
    public ResponseEntity update(@RequestBody Employee employee){
//       can add some code
        Employee employee1 = employeeService.save(employee);
        return ResponseEntity.ok(employee1);

    }
//get all items from database
    @GetMapping("/employees")
    public ResponseEntity getAll(){
        List<Employee> employeeList = employeeService.findAll();
        return ResponseEntity.ok(employeeList);
    }

    // get data items using url
    @GetMapping("/employees/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        List<Employee> employeeListByName = employeeService.findByName(name);
        return ResponseEntity.ok(employeeListByName);
    }


    @GetMapping("employees/and")
    public ResponseEntity findByNameAndLastName(@RequestParam String name, @RequestParam String lastName){
        List<Employee> employeeListByNameAndLastName = employeeService.findByNameAndLastName(name, lastName);
        return ResponseEntity.ok(employeeListByNameAndLastName);
    }

    @GetMapping("/employees/by/{name}")
    public ResponseEntity findByNameQuery(@PathVariable String name){
        List<Employee> employeeListFindByNameQuery = employeeService.findByNameQuery(name);
        return ResponseEntity.ok(employeeListFindByNameQuery);
    }

    @GetMapping("/employees/native/{name}")
    public ResponseEntity findByNameNativeQuery(@PathVariable String name) {
        List<Employee> employeeListNameByNative = employeeService.findByNameNativeQuery(name);
        return ResponseEntity.ok(employeeListNameByNative);

    }

    @GetMapping("/employees/search/")
    public ResponseEntity findAllByLike(@RequestParam String name) {
        List<Employee> employeeListByLike = employeeService.findAllByLike(name);
        return ResponseEntity.ok(employeeListByLike);
    }

//    delete items
    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("Deleted...");
    }


}


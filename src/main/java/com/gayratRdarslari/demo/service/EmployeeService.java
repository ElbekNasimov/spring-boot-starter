package com.gayratRdarslari.demo.service;

import com.gayratRdarslari.demo.domain.Employee;
import com.gayratRdarslari.demo.repository.EmployeeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }

    public List<Employee> findByName(String name){
        return employeeRepository.findByName(name);
    }

    public List<Employee> findByNameAndLastName(String name, String lastName){
        return employeeRepository.findByNameAndLastName(name, lastName);
    }

    public List<Employee> findByNameQuery(String name){
        return employeeRepository.findByNameQuery(name);
    }

    public List<Employee> findByNameNativeQuery(String name){
        return employeeRepository.findByNameNativeQuery(name);
    }

    public List<Employee> findAllByLike(String name){
        return employeeRepository.findAllNativeLike(name);
    }

    public void delete(Long id){
        Employee employee = employeeRepository.getReferenceById(id);
        employeeRepository.delete(employee);
    }

    @Scheduled(cron = "0 20 17 * * *")
    public Employee saveScheduled(){
        Employee employee = new Employee();
        employee.setName("Faxriddin");
        employee.setLastName("Raximov");
        employee.setCourse("IVAT");
        return employeeRepository.save(employee);
    }
}

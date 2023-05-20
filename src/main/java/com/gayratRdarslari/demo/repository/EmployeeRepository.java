package com.gayratRdarslari.demo.repository;

import com.gayratRdarslari.demo.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByNameAndLastName(String name, String lastName);

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findByNameQuery(@Param("name") String name);

    @Query(value = "SELECT * FROM Employee e WHERE e.name=:name", nativeQuery = true)
    List<Employee> findByNameNativeQuery(@Param("name") String name);

    List<Employee> findByNameLike(String name);

    List<Employee> findByNameStartingWith(String name);

    List<Employee> findByNameEndingWith(String name);

    @Query("SELECT e FROM Employee e WHERE e.name like upper(concat('%', :name, '%'))")
    List<Employee> findAllByLike(@Param("name") String name);

    @Query(value = "SELECT * FROM employee e where upper(e.name) LIKE upper(concat('%', :name, '%'))", nativeQuery = true)
    List<Employee> findAllNativeLike(@Param("name") String name);

}

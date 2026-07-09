package com.infosis.nexus.Employee;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService services) {
        this.service = services;
    }
    @GetMapping
    public List<Employee> getAll()
    {
        return service.getAll();
    }
   
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id)
    {
        return service.getById(id);
    }
    @PostMapping
    public Employee create(@Valid @RequestBody Employee employee)
    {
        return service.create(employee);
    }
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id , @Valid @RequestBody Employee employee)
    {
        return  service.update(id,employee);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id)
    {
        service.delete(id);
        return "Employee deleted successfully";
    }
}

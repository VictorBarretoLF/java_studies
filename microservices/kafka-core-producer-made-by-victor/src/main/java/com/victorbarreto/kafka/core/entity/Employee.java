package com.victorbarreto.kafka.core.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


import java.time.LocalDate;

// This is what the JsonNaming is doing: {"employee-id":"E204","name":"John Doe46","birth-date":"2018-02-04"}
// This is without the JsonNaming KebabCaseStrategy: {"employeeId":"E337","name":"John Doe84","birthDate":"2011-04-12"}
@JsonNaming(value = PropertyNamingStrategies.KebabCaseStrategy.class)
public class Employee {

    private String employeeId;

    private String name;

    private LocalDate birthDate;

    public Employee() {

    }

    public Employee(String employeeId, String name, LocalDate birthDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}

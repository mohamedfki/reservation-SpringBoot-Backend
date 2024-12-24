package com.example.backend.dto;

public class DeviceDto {

    private Long id;
    private String name;
    private String serialNumber; 
    private Long departmentId;   

    // Constructors
    public DeviceDto() {}

    public DeviceDto(Long id, String name, String serialNumber, Long departmentId) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        
        this.departmentId = departmentId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}

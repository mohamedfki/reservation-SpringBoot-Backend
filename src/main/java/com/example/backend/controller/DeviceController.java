package com.example.backend.controller;

import com.example.backend.dto.DeviceDto;
import com.example.backend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<DeviceDto> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public DeviceDto getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }

    @PostMapping()
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto) {
        // Check if the ID is provided (it should not be)
        if (deviceDto.getId() != null) {
            throw new IllegalArgumentException("ID should not be provided during creation.");
        }
    
        // Validate if departmentId is provided (add this validation)
        if (deviceDto.getDepartmentId() == null) {
            throw new IllegalArgumentException("Department ID must be provided.");
        }
    
        // Create device logic here...
        DeviceDto createdDevice = deviceService.createDevice(deviceDto);
    
        // Return the created device with the HTTP Status CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDevice);
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
public ResponseEntity<DeviceDto> updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto) {
    // Validate the request and update the device
    DeviceDto updatedDevice = deviceService.updateDevice(id, deviceDto);

    // Return the updated device with HTTP status OK
    return ResponseEntity.ok(updatedDevice);
}
}

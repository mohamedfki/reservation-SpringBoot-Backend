package com.example.backend.service;

import com.example.backend.dto.DeviceDto;
import com.example.backend.entity.Device;
import com.example.backend.entity.Department;
import com.example.backend.repository.DeviceRepository;
import com.example.backend.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Get all devices
    public List<DeviceDto> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream()
                .map(device -> convertToDto(device))
                .collect(Collectors.toList());
    }

    // Get device by ID
    public DeviceDto getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        return convertToDto(device);
    }

    @Transactional
    // Create a new device
    public DeviceDto createDevice(DeviceDto deviceDto) {
        Department department = departmentRepository.findById(deviceDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Device device = new Device();
        device.setName(deviceDto.getName());
        device.setSerialNumber(deviceDto.getSerialNumber());
        device.setDepartment(department);

        // Save and return the created device as DTO
        device = deviceRepository.save(device);
        return convertToDto(device);
    }

    @Transactional
    // Delete a device by ID
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    // Helper method to convert Device to DeviceDto
    private DeviceDto convertToDto(Device device) {
        return new DeviceDto(
                device.getId(),
                device.getName(),
                device.getSerialNumber(),
                Optional.ofNullable(device.getDepartment())
                        .map(Department::getId)
                        .orElse(null)  // Correctly handling null case for department
        );
    }
    @Transactional
public DeviceDto updateDevice(Long id, DeviceDto deviceDto) {
    // Check if the device exists
    Device device = deviceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Device not found"));

    // Check if the department exists
    Department department = departmentRepository.findById(deviceDto.getDepartmentId())
            .orElseThrow(() -> new RuntimeException("Department not found"));

    // Update device fields
    device.setName(deviceDto.getName());
    device.setSerialNumber(deviceDto.getSerialNumber());
    device.setDepartment(department);

    // Save and return the updated device as DTO
    device = deviceRepository.save(device);
    return convertToDto(device);
}
}

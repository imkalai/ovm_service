package com.design.ovm.catalog.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.design.ovm.catalog.api.CustomerApi;
import com.design.ovm.catalog.model.Customer;
import com.design.ovm.catalog.model.DeviceInfo;
import com.design.ovm.catalog.repository.CustomerRepository;

import com.design.ovm.catalog.repository.DeviceInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerApi {

    @Autowired
    CustomerRepository repository;

    @Autowired
    DeviceInfoRepository deviceInfoRepository;

    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        customer.setCreatedDateTime(LocalDateTime.now());
        customer.setLastModifiedDateTime(LocalDateTime.now());
        return ResponseEntity.ok(repository.saveAndFlush(customer));
    }

    @Override
    public ResponseEntity<?> registerToken(DeviceInfo deviceInfo) {
        try {

            List<DeviceInfo> existingDeviceInfo = deviceInfoRepository.findByCustomer(deviceInfo.getCustomer());
            if (existingDeviceInfo == null || !existingDeviceInfo.stream().anyMatch(deviceInfo1 -> deviceInfo1.getDeviceToken().equalsIgnoreCase(deviceInfo.getDeviceToken()) )) {
                deviceInfo.setCreatedDateTime(LocalDateTime.now());
                deviceInfo.setLastModifiedDateTime(LocalDateTime.now());
                return ResponseEntity.ok(deviceInfoRepository.saveAndFlush(deviceInfo));
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Invalid deviceInfo");

        }

        return ResponseEntity.ok("DeviceInfo exists already");
    }


    @Override
    public ResponseEntity<List<Customer>> createCustomerBatch(List<Customer> customers) {
        return ResponseEntity.ok(repository.saveAll(customers));

    }

    @Override
    public ResponseEntity<Customer> editCustomer(long customer_id, Customer customer) {
        Customer oldCustomer = repository.findById(customer_id);
        BeanUtils.copyProperties(customer,oldCustomer);
        oldCustomer.setLastModifiedDateTime(LocalDateTime.now());
        return ResponseEntity.ok(repository.save(oldCustomer));
    }

    @Override
    public ResponseEntity<String> deleteCustomer(long customer_id) {
        repository.delete(repository.findById(customer_id));
        return ResponseEntity.ok("Customer removed successfully");
    }

    @Override
    public ResponseEntity<Customer> getCustomer(long customer_id) {
        Customer customer = repository.findById(customer_id);
        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<Customer> getCustomerbyEmail(String email) {
        Customer customer = repository.findByEmail(email);
        return ResponseEntity.ok(customer);
    }


}

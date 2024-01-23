package ua.ivanyshen.customer.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ivanyshen.customer.dto.CreateCustomerDTO;
import ua.ivanyshen.customer.dto.CustomerResponseDTO;
import ua.ivanyshen.customer.dto.UpdateCustomerDTO;
import ua.ivanyshen.customer.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping()
    public List<CustomerResponseDTO> getAllCustomers() {
        return service.getAllCustomers();
    }

    @PostMapping()
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CreateCustomerDTO newCustomer) {
        return ResponseEntity.ok(service.saveCustomer(newCustomer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id) {
        CustomerResponseDTO resp = service.getCustomerById(id);
        return (resp == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok(resp);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id,
                                                              @RequestBody UpdateCustomerDTO updateCustomerDTO) {
        CustomerResponseDTO found = service.getCustomerById(id);
        if(found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(service.updateCustomer(updateCustomerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> deleteCustomer(@PathVariable Long id) {
        CustomerResponseDTO found = service.getCustomerById(id);
        if(found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        service.deleteCustomerById(id);
        return ResponseEntity.ok(found);
    }
}

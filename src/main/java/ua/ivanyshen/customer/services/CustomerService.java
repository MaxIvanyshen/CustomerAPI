package ua.ivanyshen.customer.services;

import org.springframework.stereotype.Service;
import ua.ivanyshen.customer.dto.CreateCustomerDTO;
import ua.ivanyshen.customer.dto.CustomerResponseDTO;
import ua.ivanyshen.customer.dto.UpdateCustomerDTO;
import ua.ivanyshen.customer.repositories.CustomerRepository;
import ua.ivanyshen.customer.models.Customer;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        var customerList = repo.findAll();
        List<CustomerResponseDTO> list = new ArrayList<>();
        for(Customer customer : customerList) {
            list.add(new CustomerResponseDTO(customer));
        }
        return list;
    }

    public CustomerResponseDTO saveCustomer(CreateCustomerDTO newCustomerDTO) {
        Customer customer = new Customer(
                newCustomerDTO.fullName,
                newCustomerDTO.email,
                newCustomerDTO.phone,
                Instant.now().getEpochSecond(),
                Instant.now().getEpochSecond(),
                true);
        this.repo.save(customer);
        return new CustomerResponseDTO(customer);
    }

    public CustomerResponseDTO getCustomerById(Long id) {
        try {
            Customer found = repo.findById(id).get();
            return new CustomerResponseDTO(found);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void deleteCustomerById(Long id) {
        Customer found = repo.findById(id).get();
        found.setActive(false);
        found.setUpdated(Instant.now().getEpochSecond());
        repo.save(found);
    }

    public CustomerResponseDTO updateCustomer(UpdateCustomerDTO updateCustomerDTO) {
        Customer found = repo.findById(updateCustomerDTO.id).get();
        found.setFullName(updateCustomerDTO.fullName);
        found.setPhone(updateCustomerDTO.phone);
        repo.save(found);
        return new CustomerResponseDTO(found);
    }
}

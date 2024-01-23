package ua.ivanyshen.customer.dto;

import ua.ivanyshen.customer.models.Customer;

import java.util.Optional;

public class CustomerResponseDTO {

    public Long id;
    public String fullName;
    public String email;
    public String phone;

    public CustomerResponseDTO(Customer customer) {
        id = customer.getId();
        fullName = customer.getFullName();
        email = customer.getEmail();
        phone = customer.getPhone();
    }
}

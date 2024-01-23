package ua.ivanyshen.customer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_SEQ")
    @SequenceGenerator(name = "customers_SEQ", sequenceName = "customers_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String phone;

    private Long created;
    private Long updated;

    @Column(name = "is_active")
    private boolean isActive;

    public Customer(String fullName, String email, String phone, Long created, Long updated, boolean isActive) {
        this.fullName = fullName;
        this.email = email;
        this.created = created;
        this.updated = updated;
        this.phone = phone;
        this.isActive = isActive;
    }
}

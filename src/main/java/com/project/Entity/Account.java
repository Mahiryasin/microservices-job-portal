package com.project.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {
     // many to one relationship   
    // 1 musteerinin birden fazla hesabı olabilir 
    // bir hesap sadece bir musteriye aittir 
    

    @Id
    @Column(name = "account_number",nullable = false)
    private Long account_number;

    @Column(name = "account_type",nullable = false)
    private String account_type;

    @Column(name = "branch_address",nullable = false)
    private String branch_address;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customer_id",nullable = false,unique=true)
    private Customer customer;

}

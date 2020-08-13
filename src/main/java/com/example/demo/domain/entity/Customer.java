package com.example.demo.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@SuperBuilder
public class Customer {
    @Id
    private long customerNumber;

    private String customerName;
}

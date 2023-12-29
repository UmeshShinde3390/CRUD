package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int empId;

    @Size(min = 2, message = "Name should br atleast 2 characters")
    private String empName;

    private String empAddress;

    private double empSalary;

    @Column(unique = true)
    @Range(min = 1000000000, max = 9999999999L, message = "Contact Number must be 10 digit")
    private long empContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    @Column(unique = true)
    private long empUID;

    @Column(unique = true)
    private String empPanCard;

    @Column(unique = true)
    @Email(message = "EmailId must be valid")
    private String empEmailId;

    @Size(min = 4, message = "Password Should be atleast 4 characters")
    private String empPassword;
}
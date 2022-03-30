package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@SuperBuilder
public class PinRequest {
    @Pattern(regexp="^([0-9][0-9]*)$", message="only accept number")
    @Length(max = 6, min = 6, message="pin must be 6 character")
    @NotBlank(message = "pin is required")
    private String pin;
}

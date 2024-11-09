package com.codekion.vecino_seguro.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @Size(max = 50)
    String email;


    @Size(max = 50)
    String password;

}

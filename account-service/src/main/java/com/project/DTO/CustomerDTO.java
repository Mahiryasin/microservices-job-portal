package com.project.DTO;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

   @NotEmpty(message = "Name is mandatory")
   @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
   @Schema(
      description = "Customer name",
      example = "Mahir Yasin Bashkes"
   )
   private String name;

   @NotEmpty(message = "Email is mandatory")
   @Size(min = 3, max = 50, message = "Email must be between 3 and 50 characters")
   @Email(message = "Invalid Email")
   @Schema(
      description = "Customer email",
      example = "[EMAIL_ADDRESS]"
   )

   private String Email;
 
   @NotEmpty(message = "Mobile Number is mandatory")
   @Pattern(regexp = "^[0-9]{10}$",message = "Invalid MobileNumber Format")
   @Schema(
      description = "Customer mobile number",
      example = "1234567890"
   )
   private String mobileNumber;


}

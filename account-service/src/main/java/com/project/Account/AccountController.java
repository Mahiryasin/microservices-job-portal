package com.project.Account;

import java.util.List;
import java.util.Map;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.infoDTO;
import com.project.Constant.ACCOUNT_CONSTANTS;
import com.project.DTO.AccountDTO;
import com.project.DTO.CustomerDTO;
import com.project.DTO.ErrorResponseDTO;
import com.project.DTO.ResponseDTO;
import com.project.Properties.properties;
import com.project.Service.AccountService;

import org.springframework.cloud.context.config.annotation.RefreshScope;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/Rest",produces = MediaType.APPLICATION_JSON_VALUE)
// swagger dokumantasyon Rest ile baslıyan apiler hangi contentype kabul edicek  Response content type  response json kabul edicek !!
@RequiredArgsConstructor
@Validated
@Tag(name = "AccountController",description = "API")
public class AccountController {

    private final properties properties_;

    private final org.springframework.core.env.Environment environment;
      
    private final AccountService _accountService;

    private final infoDTO accountProperties;


    

  
      
    @GetMapping("/greeting")
    
    public String sayHello(){
        return "hello";
    }

    @ApiResponse(
        responseCode = "201",
        description = "Account created successfully"
 )

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
        _accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(ACCOUNT_CONSTANTS.STATUS_201,ACCOUNT_CONSTANTS.MESSAGE_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<List<AccountDTO>> fetchAccountDetails(@RequestParam(value = "mobileNumber")  
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid MobileNumber Format")

    String MobileNumber){
        List<AccountDTO> accountDTOs=_accountService.fetchAccountDetails(MobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOs);
    }


    @PutMapping("/update")
    @Operation(summary = "updateAccount",description = "updateAccount")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Account updated successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Resource Not Found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "417",
                description = "Not updated !",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)
                )
            )
        }
    )
    public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountDTO accountDTO){
       Boolean isUpdated= _accountService.updateAccount(accountDTO);  

       if(isUpdated)
        return ResponseEntity.status(200).body(isUpdated);
        else{
            return ResponseEntity.status(417).body(new ResponseDTO(ACCOUNT_CONSTANTS.STATUS_417,ACCOUNT_CONSTANTS.MESSAGE_417_UPDATE));
        }
    }

    @ApiResponses(
        value = { 
            @ApiResponse(
                responseCode="200",
                description="Account deleted successfully"
            ),
            @ApiResponse(
                responseCode ="404",
                description= "Resource Not Found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "417",
                description = "Not deleted !",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class)
                )
            )
        }
    )
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable(value = "mobileNumber") 
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid MobileNumber Format")
    String mobileNumber){
          return ResponseEntity.status(HttpStatus.OK).body(_accountService.deleteAccount(mobileNumber));  
    }

    @Operation(summary="Get version",description = "processes version")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "version get successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class)
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
                )
            )
        }
    )
    @GetMapping("/version")
    public ResponseEntity<infoDTO> GetVersion(){
       infoDTO dto = new infoDTO(accountProperties.getMessage(), accountProperties.getContactDetails(), accountProperties.getOncallsupport());
       return ResponseEntity.ok().body(dto);
    }
    @Operation(summary = "Get Build Info", description = "Returns build configuration properties")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Build info retrieved successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = properties.class)
                )
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)
                )
            )
        }
    )
    @GetMapping("/build-info")
    public ResponseEntity<infoDTO> getBuildInfo(){
        infoDTO dto = new infoDTO(accountProperties.getMessage(), accountProperties.getContactDetails(), accountProperties.getOncallsupport());
        return ResponseEntity.ok().body(dto);
    }
   
}

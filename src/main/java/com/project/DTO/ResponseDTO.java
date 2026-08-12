package com.project.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    @Schema(
        description = "Status Code",
        example = "200"
    )
    private String statusCode;

    @Schema(
        description = "Status Message",
        example = "Operation completed successfully"
    )
    private String StatusMsg;

}

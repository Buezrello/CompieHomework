package org.compie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.compie.model.response.StatusResponse;
import org.compie.service.CompieService;
import org.compie.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class CompieController {

    private final CompieService compieService;

    @Operation(summary="Retrieve All Players")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200",description= "Deliveries found"),
            @ApiResponse(responseCode="400",description= Constants.BAD_REQUEST, content=@Content),
            @ApiResponse(responseCode="401",description= Constants.UNAUTHORIZED, content=@Content),
            @ApiResponse(responseCode="403",description= Constants.FORBIDDEN, content=@Content),
            @ApiResponse(responseCode="404",description= Constants.NOT_FOUND, content=@Content),
            @ApiResponse(responseCode="500",description=Constants.INTERNAL_SERVER_ERROR, content=@Content)})
    @GetMapping("/players")
    public ResponseEntity<StatusResponse> getPlayersDetails() throws FileNotFoundException {
        return ResponseEntity.ok(compieService.getPlayersDetails());
    }
}

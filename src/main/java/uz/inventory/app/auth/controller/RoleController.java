package uz.inventory.app.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.inventory.app.util.dto.PaginationRequestDto;
import uz.inventory.app.core.payload.CustomPageResponse;
import uz.inventory.app.auth.service.RoleService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/list")
    @Operation(summary = "Get all roles of project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomPageResponse.class))}),
    })
    public ResponseEntity<?> getCompanies(@RequestBody PaginationRequestDto paginationRequestDto) {
        return roleService.getRoles(paginationRequestDto);
    }
}


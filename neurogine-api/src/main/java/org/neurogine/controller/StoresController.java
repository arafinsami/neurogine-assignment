package org.neurogine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.neurogine.dto.StoresDTO;
import org.neurogine.entity.Stores;
import org.neurogine.service.IStoresService;
import org.neurogine.validators.StoresValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import static org.neurogine.exception.ApiError.fromFieldError;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static org.neurogine.utils.ResponseBuilder.error;
import static org.neurogine.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "stores")
@Tag(name = "Store's Data")
public class StoresController {

    private final StoresValidator storesValidator;

    private final IStoresService storesService;

    @PostMapping("/save")
    @Operation(summary = "save stores data", description = "description of save stores data")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(
                    implementation = StoresDTO.class)
            )
    })
    public ResponseEntity<JSONObject> save(@Valid @RequestBody StoresDTO dto, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(storesValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fromFieldError(bindingResult)).getJson());
        }

        Stores stores = dto.to();
        storesService.save(stores);
        return ok(success(StoresDTO.from(stores)).getJson());
    }

    @PutMapping("/update")
    @Operation(summary = "update stores data", description = "description of update stores data")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(
                    implementation = StoresDTO.class)
            )
    })
    public ResponseEntity<JSONObject> update(@Valid @RequestBody StoresDTO dto, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(storesValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fromFieldError(bindingResult)).getJson());
        }

        Stores stores = storesService.findById(dto.getId());
        dto.update(stores);
        stores = storesService.update(stores);
        return ok(success(StoresDTO.from(stores)).getJson());
    }

    @GetMapping("/{storesId}")
    @Operation(summary = "get stores data by storesId", description = "description of get stores data by storesId")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(
                    implementation = StoresDTO.class)
            )
    })
    public ResponseEntity<JSONObject> findById(@PathVariable String storesId) {
        Stores stores = storesService.findById(storesId);
        return ok(success(StoresDTO.from(stores)).getJson());
    }

    @GetMapping("/all-stores")
    @Operation(summary = "get all stores data", description = "description of get all stores data")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(
                    implementation = StoresDTO.class)
            )
    })
    public ResponseEntity<JSONObject> findAll() {
        List<StoresDTO> storesDTOS = storesService.findAll().stream().map(StoresDTO::from).collect(Collectors.toList());
        return ok(success(storesDTOS).getJson());
    }

    @DeleteMapping("/{storesId}")
    @Operation(summary = "delete stores data by storesId", description = "description of delete stores data by storesId")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(
                    implementation = StoresDTO.class)
            )
    })
    public ResponseEntity<JSONObject> delete(@PathVariable String storesId) {
        storesService.delete(storesId);
        return ok(success("delete store data by: "+storesId).getJson());
    }
}

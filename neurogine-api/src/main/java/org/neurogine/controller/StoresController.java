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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static org.neurogine.exception.ApiError.fromFieldError;
import static org.neurogine.utils.ResponseBuilder.error;
import static org.neurogine.utils.ResponseBuilder.success;
import static org.neurogine.utils.StringUtils.isEmpty;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "stores")
@Tag(name = "Store's Data")
public class StoresController {

    private final IStoresService storesService;

    @PostMapping("/save")
    @Operation(summary = "save stores data", description = "description of save stores data")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StoresDTO.class))})
    public ResponseEntity<JSONObject> save(@Valid @RequestBody StoresDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fromFieldError(bindingResult)).getJson());
        }

        Stores stores = dto.to();
        storesService.save(stores);
        return ok(success(StoresDTO.from(stores)).getJson());
    }

    @PutMapping("/update")
    @Operation(summary = "update stores data", description = "description of update stores data")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StoresDTO.class))})
    public ResponseEntity<JSONObject> update(@Valid @RequestBody StoresDTO dto, BindingResult bindingResult) {

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
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StoresDTO.class))})
    public ResponseEntity<JSONObject> findById(@PathVariable String storesId) {
        Stores stores = storesService.findById(storesId);
        return ok(success(StoresDTO.from(stores)).getJson());
    }

    @PostMapping("/all-stores")
    @Operation(summary = "get all stores data", description = "description of get all stores data")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StoresDTO.class))})
    public ResponseEntity<JSONObject> findAll(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "category", defaultValue = "") String category, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response;

        if (isEmpty(name) && isEmpty(category)) {
            response = storesService.findAll(pageable);
        } else {
            response = storesService.findByCategoryOrName(name, category, pageable);
        }

        return ok(success(response).getJson());
    }

    @DeleteMapping("/{storesId}")
    @Operation(summary = "delete stores data by storesId", description = "description of delete stores data by storesId")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StoresDTO.class))})
    public ResponseEntity<JSONObject> delete(@PathVariable String storesId) {
        storesService.delete(storesId);
        return ok(success("delete store data by: " + storesId).getJson());
    }

    @PostMapping("/setup")
    public ResponseEntity<JSONObject> setup() {
        storesService.setUp();
        return ok(success("set up all data done !!!").getJson());
    }
}

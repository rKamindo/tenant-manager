package com.example.tenantmanagementsystem.tenant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tenants")
public class TenantController {
    private final TenantService tenantService;
    private final TenantDTOMapper tenantDTOMapper;
    public TenantController(TenantService tenantService, TenantDTOMapper tenantDTOMapper) {
        this.tenantService = tenantService;
        this.tenantDTOMapper = tenantDTOMapper;
    }

    @GetMapping
    public ResponseEntity<List<TenantDTO>> getTenants() {
        List<TenantDTO> tenantDTOS = tenantService.getAllTenants()
                .stream().map(tenant -> tenantDTOMapper.apply(tenant))
                .collect(Collectors.toList());
        return new ResponseEntity<>(tenantDTOS, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TenantDTO> getTenant(@PathVariable("id") Long tenantId) {
        Tenant tenant = tenantService.getTenant(tenantId);
        return new ResponseEntity<>(tenantDTOMapper.apply(tenant), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TenantDTO> createTenant(@RequestBody TenantCreateRequest request) {
        Tenant tenant = tenantService.addTenant(request);
        return new ResponseEntity<>(tenantDTOMapper.apply(tenant), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTenant(@PathVariable("id") Long tenantId) {
        tenantService.deleteTenantById(tenantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<TenantDTO> updateTenant(
            @PathVariable("id") Long tenantId,
            @RequestBody TenantUpdateRequest updateRequest) {
        Tenant tenant = tenantService.updateTenant(tenantId, updateRequest);
        return new ResponseEntity<>(tenantDTOMapper.apply(tenant), HttpStatus.OK);
    }


}

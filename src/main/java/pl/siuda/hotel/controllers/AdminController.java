package pl.siuda.hotel.controllers;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.dto.AdminDto;
import pl.siuda.hotel.requests.AdminRequest;
import pl.siuda.hotel.services.AdminService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin-management")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminDto> getAllAdmins(){
        return adminService.findAllAdmins();
    }

    @GetMapping("{id}")
    public AdminDto getAdminById(@PathVariable("id") UUID id){
        return adminService.getAdminById(id);
    }

    @PostMapping
    public void saveAdmin(@RequestBody AdminRequest request){
        adminService.saveAdmin(request);
    }

    @PutMapping("{id}")
    public void updateAdmin(@PathVariable UUID id, @RequestBody AdminRequest request){
        adminService.updateAdmin(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable UUID id){
        adminService.deleteAdmin(id);
    }
}

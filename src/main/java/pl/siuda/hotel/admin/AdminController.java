package pl.siuda.hotel.admin;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/admins")
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
    public AdminDto getAdminById(@PathVariable("id") Long id){
        return adminService.getAdminById(id);
    }

    @PostMapping
    public void saveAdmin(@RequestBody AdminRequest request){
        adminService.saveAdmin(request);
    }

    @PutMapping("{id}")
    public void updateAdmin(@PathVariable Long id, @RequestBody AdminRequest request){
        adminService.updateAdmin(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
    }
}

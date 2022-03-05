package pl.siuda.hotel.admin;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("management/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminDto> getAllAdmins(){
        return adminService.findAll().stream().map(AdminDto::adminToDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public AdminDto getAdminById(@PathVariable("id") Long id){
        return AdminDto.adminToDto(adminService.getAdminById(id));
    }

    @PostMapping
    public AdminDto save(@RequestBody AdminRequest request){
        return AdminDto.adminToDto(adminService.save(request));
    }

    @PutMapping("{id}")
    public AdminDto update(@PathVariable Long id, @RequestBody AdminRequest request){
        return AdminDto.adminToDto(adminService.updateAdmin(id, request));
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
    }
}

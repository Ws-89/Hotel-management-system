package pl.siuda.hotel.admin;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin save(@RequestBody AdminRequest request){
        return adminService.save(request);
    }

}

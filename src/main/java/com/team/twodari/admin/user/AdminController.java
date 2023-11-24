package com.team.twodari.admin.user;

import com.team.twodari.user.service.UserService;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin/users")
public class AdminController{

    private final AdminUserService adminUserService;

    public AdminController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("")
    public String getAllUsers(@PageableDefault(size=20) Pageable pageable, Model model) {
        model.addAttribute("users", adminUserService.getAllUsers(pageable));
        return "admin/user/user_list"; // html
    }

    @PostMapping("/update/{user-seq}")
    public String updateUser(@PathVariable("user-seq") Long userSeq) {
        adminUserService.updateUser(userSeq);
        return "redirect:/admin/users"; // url
    }

    @PostMapping("/delete/{user-seq}")
    public String deleteUser(@PathVariable("user-seq") Long userSeq) {
        adminUserService.deleteUser(userSeq);
        return "redirect:/admin/users";
    }
}

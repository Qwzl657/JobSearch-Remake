package kg.attractor.jobsearch_remake.controller.mvc;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employers")
@RequiredArgsConstructor
public class EmployerMvcController {

    private final UserService userService;

    @GetMapping
    public String employersList(Model model) {
        List<UserDto> employers = userService.getEmployers();
        model.addAttribute("employers", employers);
        return "employers/list";
    }
}
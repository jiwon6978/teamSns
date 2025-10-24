package com.example.demo.controller;




import com.example.demo.domain.user.service.UserService;
import com.example.demo.domain.user.dtos.JoinDto;
import com.example.demo.domain.user.dtos.LoginDto;
import com.example.demo.domain.user.view.LoginUserView;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/sns")
public class UserController {
    @Autowired
    private UserService userService;

    //회원가입
    @GetMapping("/join")
    public void add_join_get() throws Exception{
        log.info("GET /sns/join");
    }
    @PostMapping("/join")
    public String add_join_post(@Valid JoinDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception {
        log.info("POST /sns/join" + dto);

        //유효성검증
        log.info("유효성 오류 발생여부 : " + bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.info("Error Field :" + error.getField() + "Error Message :" + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "redirect:/sns/join?error=true";
        }

        //서비스 요청
        Long insertedId = userService.joinRegistration(dto);
        if (insertedId != null) {
            redirectAttributes.addFlashAttribute("message", "회원가입완료!");
        }

        //뷰로 이동
        return insertedId != null ? "redirect:/sns/login" : "sns/join";
    }

    //로그인 (로컬)
    @GetMapping("/login")
    public String loginForm() {
        return "sns/login";
    }
    @PostMapping("/login")
    public String login(
            @Valid LoginDto dto,
            BindingResult bindingResult,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/sns/login";
        }
        var user = userService.login(dto);
        session.setAttribute("loginUser", LoginUserView.from(user));
        return "redirect:/sns/main";
    }


}

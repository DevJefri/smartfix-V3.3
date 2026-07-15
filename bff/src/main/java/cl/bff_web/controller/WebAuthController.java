package cl.bff_web.controller;

import cl.bff_web.dto.LoginRequestDTO;
import cl.bff_web.dto.LoginResponseDTO;
import cl.bff_web.service.BffService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/auth")
public class WebAuthController {

    private final BffService bffService;

    public WebAuthController(BffService bffService) {
        this.bffService = bffService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return bffService.login(dto);
    }

    @PostMapping("/register")
    public LoginResponseDTO register(@Valid @RequestBody LoginRequestDTO dto) {
        return bffService.register(dto);
    }
}

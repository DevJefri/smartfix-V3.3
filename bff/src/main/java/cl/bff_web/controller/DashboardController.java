package cl.bff_web.controller;

import cl.bff_web.dto.DashboardResponseDTO;
import cl.bff_web.service.BffService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/dashboard")
public class DashboardController {

    private final BffService bffService;

    public DashboardController(BffService bffService) {
        this.bffService = bffService;
    }

    @GetMapping("/{rut}")
    public DashboardResponseDTO getDashboard(
            @PathVariable String rut,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        String token = authHeader.substring(7); // removes "Bearer "
        return bffService.getDashboard(rut, token);
    }
}

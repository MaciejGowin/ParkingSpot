package parkingspot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import parkingspot.model.Reservation;
import parkingspot.model.ReservationForm;
import parkingspot.service.CustomerService;
import parkingspot.service.ReservationService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ApplicationController {

    private final ReservationService reservationService;
    private final CustomerService customerService;
    private String loggedUserLogin;

    @Autowired
    public ApplicationController(ReservationService reservationService, CustomerService customerService) {
        this.reservationService = reservationService;
        this.customerService = customerService;
        this.loggedUserLogin = "";
    }

    @GetMapping("/")
    public ModelAndView root() {
        return new ModelAndView("root");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        loggedUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> model = Map.of(
                "username", loggedUserLogin,
                "reservationForm", new ReservationForm());
        return new ModelAndView("profile", model);
    }

    @PostMapping("/reservation")
    public ModelAndView reservation(@Valid @ModelAttribute("reservationForm") ReservationForm reservationForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            String error = bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return new ModelAndView("reservationerror", Map.of("error", error));
        }

        LocalDate parsedDate = LocalDate.parse(reservationForm.getDate());
        Reservation reservation = Reservation.builder()
                .localDate(parsedDate)
                .build();

        if (reservationService.isDateAvailable(parsedDate)) {
            reservationService.addReservation(reservation);
            customerService.addUserReservation(loggedUserLogin, reservation);
            return new ModelAndView("reservation", Map.of("reservation", reservation));
        } else {
            return new ModelAndView("datenotavailable", Map.of("reservation", reservation));
        }
    }

    @GetMapping("/reservations")
    public ModelAndView getReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ModelAndView("reservations", Map.of("reservations", reservations));
    }

    @GetMapping("/my-reservations")
    public ModelAndView getMyReservations() {
        List<Reservation> userReservations = customerService.getUserReservations(loggedUserLogin);
        return new ModelAndView("myreservations", Map.of("userReservations", userReservations));
    }
}

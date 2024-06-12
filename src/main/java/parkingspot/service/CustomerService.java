package parkingspot.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import parkingspot.model.Customer;
import parkingspot.model.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerService() {
        List<Reservation> firstUserReservations = new ArrayList<>();
        firstUserReservations.add(Reservation.builder().localDate(LocalDate.parse("2024-07-01")).build());
        firstUserReservations.add(Reservation.builder().localDate(LocalDate.parse("2024-06-30")).build());

        List<Reservation> secondUserReservations = new ArrayList<>();
        secondUserReservations.add(Reservation.builder().localDate(LocalDate.parse("2024-07-03")).build());
        secondUserReservations.add(Reservation.builder().localDate(LocalDate.parse("2024-07-04")).build());

        customers.add(Customer.builder()
                .id(1)
                .login("Marysia")
                .password("pwd")
                .reservations(firstUserReservations)
                .build());

        customers.add(Customer.builder()
                .id(2)
                .login("Mateusz")
                .password("pwd")
                .reservations(secondUserReservations)
                .build());
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addUserReservation(String username, Reservation reservation) {
        Customer customer = findByUsername(username);
        customer.getReservations().add(reservation);

        customers.remove(findByUsername(username));
        customers.add(customer);
    }

    public List<Reservation> getUserReservations(String username) {
        List<Reservation> reservations = findByUsername(username).getReservations();
        reservations.sort(Comparator.comparing(Reservation::getLocalDate));
        return reservations;
    }

    public Customer findByUsername(String username) {
        return customers.stream().filter(c -> c.getLogin().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}

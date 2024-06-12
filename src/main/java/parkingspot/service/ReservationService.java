package parkingspot.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import parkingspot.model.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReservationService {

    @Getter
    private final List<Reservation> mockedReservations = new ArrayList<>();

    public ReservationService() {
        mockedReservations.add(getReservation("2024-07-01"));
        mockedReservations.add(getReservation("2024-06-30"));
        mockedReservations.add(getReservation("2024-07-03"));
        mockedReservations.add(getReservation("2024-07-04"));
    }

    public List<Reservation> getAllReservations() {
        mockedReservations.sort(Comparator.comparing(Reservation::getLocalDate));
        return mockedReservations;
    }

    public void addReservation(Reservation reservation) {
        mockedReservations.add(reservation);
    }

    public boolean isDateAvailable(LocalDate localDate) {
        return mockedReservations.stream().noneMatch(reservation -> reservation.getLocalDate().equals(localDate));
    }

    private Reservation getReservation(String date) {
        return Reservation.builder().localDate(LocalDate.parse(date)).build();
    }
}

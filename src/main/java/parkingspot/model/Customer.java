package parkingspot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Customer {

    private int id;
    private String login;
    private String password;
    private List<Reservation> reservations;
}

package parkingspot.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto {

    private int id;
    private String login;
    private String password;
    private List<ReservationDto> reservations;
}

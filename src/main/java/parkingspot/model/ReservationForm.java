package parkingspot.model;

import lombok.Getter;
import lombok.Setter;
import parkingspot.validation.FutureDateString;

@Setter
@Getter
public class ReservationForm {

    @FutureDateString
    private String date;
}

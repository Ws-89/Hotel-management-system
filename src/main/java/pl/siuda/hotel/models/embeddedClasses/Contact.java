package pl.siuda.hotel.models.embeddedClasses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String phoneNumber;
    private String email;



}

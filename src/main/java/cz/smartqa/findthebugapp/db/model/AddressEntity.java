package cz.smartqa.findthebugapp.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressEntity extends AbstractEntity {

    private String street1;
    private String street2;
    private String city;
    private String zip;
    private String country;
}

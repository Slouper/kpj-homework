package cz.smartqa.findthebugapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddressDto extends AbstractDto {
    @NotNull
    private String street1;
    private String street2;
    @NotNull
    private String city;
    @NotNull
    private String zip;
    @NotNull
    private String country;
}

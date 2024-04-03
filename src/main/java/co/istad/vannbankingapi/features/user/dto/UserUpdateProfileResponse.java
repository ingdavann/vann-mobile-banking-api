package co.istad.vannbankingapi.features.user.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UserUpdateProfileResponse(

    @Size(max = 100)
    String cityOrProvince,

    @Size(max = 100)
    String khanOrDistrict,

    @Size(max = 100)
    String sangkatOrCommune,

    @Size(max = 100)
    String village,

    @Size(max = 100)
    String street,

    @Size(max = 100)
    String employeeType,

    @Size(max = 100)
    String position,

    @Size(max = 100)
    String companyName,

    @Size(max = 100)
    String mainSourceOfIncome,

    @Positive
    BigDecimal monthlyIncomeRange

) {
}

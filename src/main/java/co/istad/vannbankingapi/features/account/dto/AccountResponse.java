package co.istad.vannbankingapi.features.account.dto;

import co.istad.vannbankingapi.features.accounttype.dto.AccountTypeResponse;
import co.istad.vannbankingapi.features.user.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse(
        String alias,
        String actNo,
        String actName,
        BigDecimal balance,
        BigDecimal transferLimit,
        AccountTypeResponse accountType,
        UserResponse user
) {
}

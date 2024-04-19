package co.istad.vannbankingapi.features.mail;

import co.istad.vannbankingapi.features.mail.dto.MailRequest;
import co.istad.vannbankingapi.features.mail.dto.MailResponse;

public interface MailService {
    MailResponse sendMail(MailRequest mailRequest);
}

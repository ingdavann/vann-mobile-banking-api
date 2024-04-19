package co.istad.vannbankingapi.features.mail;

import co.istad.vannbankingapi.features.mail.dto.MailRequest;
import co.istad.vannbankingapi.features.mail.dto.MailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mails")
public class MailController {
    private final MailService mailService;

    @PostMapping
    MailResponse send(@Valid @RequestBody MailRequest mailRequest){
        return mailService.sendMail(mailRequest);
    }
}

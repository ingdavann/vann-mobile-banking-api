package co.istad.vannbankingapi.features.mail;

import co.istad.vannbankingapi.features.mail.dto.MailRequest;
import co.istad.vannbankingapi.features.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImplement implements MailService{

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Override
    public MailResponse sendMail(MailRequest mailRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariable("name", "ISTAD");

        //temple engine inject above
        String htmlTemplate = templateEngine.process("mail/welcome", context);


        try {
            mimeMessageHelper.setTo(mailRequest.to());
            mimeMessageHelper.setSubject(mailRequest.subject());
            mimeMessageHelper.setText(mailRequest.text());
        }
        catch (MessagingException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }
        javaMailSender.send(mimeMessage);
        return new MailResponse("Mail has been sent");
    }
}

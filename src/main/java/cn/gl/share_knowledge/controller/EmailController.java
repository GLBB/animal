package cn.gl.share_knowledge.controller;

import cn.gl.share_knowledge.dto.BooleanResult;
import cn.gl.share_knowledge.message.EmailRegisterMessage;
import cn.gl.share_knowledge.service.EmailService;
import cn.gl.share_knowledge.validation.EmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2CodecSupport;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;


@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping("email/register")
    @ResponseBody
    public EmailRegisterMessage registerEmail(@Validated EmailValidation emailValidation, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            EmailRegisterMessage message = new EmailRegisterMessage(false, fieldError.getDefaultMessage());
            return message;
        }
        boolean b = emailService.registerEmail(emailValidation.getEmail());
        EmailRegisterMessage message = new EmailRegisterMessage(true, "已发送");
        return message;
    }
}

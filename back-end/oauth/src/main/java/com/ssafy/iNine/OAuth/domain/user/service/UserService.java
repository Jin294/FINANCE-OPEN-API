package com.ssafy.iNine.OAuth.domain.user.service;

import com.ssafy.iNine.OAuth.common.entity.user.User;
import com.ssafy.iNine.OAuth.common.exception.CommonException;
import com.ssafy.iNine.OAuth.common.exception.ExceptionType;
import com.ssafy.iNine.OAuth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    public void sendEmail(String email) {
        long verifiedCode = Math.round(100000 + Math.random() * 899999);
        Optional<User> user = userRepository.findById(email);
        if(user.isPresent()) {
            log.info("user is present");
            userRepository.setUserPassword(Long.toString(verifiedCode), user.get().getId());
            userRepository.setAuthSendTime(LocalDateTime.now(), user.get().getId());
        }
        else {
            log.info("user is not present");
            User newUser = User.builder()
                    .id(email)
                    .password(Long.toString(verifiedCode))
                    .nickname(email)
                    .state("Y")
                    .authSendTime(LocalDateTime.now())
                    .build();
            userRepository.save(newUser);
        }

        MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), "UTF-8");
        try {
            messageHelper.setTo(email);
            messageHelper.setSubject("SSAFY FINANCIAL OPEN API 이메일 인증코드 안내");
            messageHelper.setText("    <div style=\"width: 700px; height: 500px; margin: 50px\">\n" +
                    "      <img src=\"https://i.ibb.co/ctnXLZr/email-ting-logo-removebg-preview.png\" alt=\"ting logo\" style=\"width: 100px\" />\n" +
                    "      <h2 style=\"font-weight: 900\">Ting 서비스의 이메일 확인을 위해 인증번호를 보내드려요</h2>\n" +
                    "      <p>이메일 인증 화면에서 아래의 인증번호를 입력하고 인증을 완료해주세요.</p>\n" +
                    "      <h1>" + verifiedCode + "</h1>\n" +
                    "\n" +
                    "      <hr />\n" +
                    "      <pre>\n" +
                    "혹시 요청하지 않은 인증 메일을 받으셨나요?\n" +
                    "누군가 실수로 메일 주소를 잘못 입력했을 수 있어요. 계정이 도용된 것은 아니니 안심하세요.\n" +
                    "직접요청한 인증 메일이 아닌 경우 무시해주세요.\n" +
                    "      </pre>\n" +
                    "      <hr style=\"border: 0; height: 3px; background: #ccc\" />\n" +
                    "\n" +
                    "<pre>\n" +
                    "이 메일은 발신 전용 메일이에요.\n" +
                    "SSAFY FINANCIAL OPEN API에 궁금한 점이 있으시면 답장을 통해 질문해주세요. © SSAFY Ting Inc.\n" +
                    "</pre>\n" +
                    "    </div>", true);
            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new CommonException(ExceptionType.EMAIL_SEND_FAIL);
        }
    }
}

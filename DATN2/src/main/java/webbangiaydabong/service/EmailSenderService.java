package webbangiaydabong.service;

public interface EmailSenderService {
    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body);
}

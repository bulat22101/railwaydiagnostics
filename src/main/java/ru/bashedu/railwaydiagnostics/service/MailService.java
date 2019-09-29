package ru.bashedu.railwaydiagnostics.service;

public interface MailService {
    void sendMessage(String to, String subject, String text);
}

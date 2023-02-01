package br.com.alura.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
        private val javaMailSender: JavaMailSender
) {
    fun notificar(emailAutor: String) {
        val message = SimpleMailMessage()
        message.setSubject("[Forum] Resposta recebida")
        message.setText("Olá, o seu tópico foi respondido")
        message.setTo(emailAutor)

        javaMailSender.send(message)
    }
}
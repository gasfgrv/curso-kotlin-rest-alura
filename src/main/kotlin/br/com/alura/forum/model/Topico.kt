package br.com.alura.forum.model

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Topico(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var titulo: String,
        var mensagem: String,
        val dataCriacao: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        val curso: Curso,
        @ManyToOne
        val autor: Usuario,
        @Enumerated(value = EnumType.STRING)
        val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        @OneToMany(mappedBy = "topico")
        @JsonBackReference
        val respostas: List<Resposta> = ArrayList(),
        var dataAlteracao: LocalDate? = null
)
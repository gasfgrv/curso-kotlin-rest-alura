package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico não encontrado"
) {

    fun listar(): List<TopicoView> {
        return topicos.map { topico -> topicoViewMapper.map(topico) }
    }

    fun buscarPorId(id: Long): TopicoView {
        return topicos
            .filter { topico -> topico.id == id }
            .map { topico -> topicoViewMapper.map(topico) }
            .getOrElse(0) { throw NotFoundException(notFoundMessage) }
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1L
        topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos
            .filter { topico -> topico.id == form.id }
            .getOrElse(0) { throw NotFoundException(notFoundMessage) }

        val topicoAtualizado = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )

        topicos = topicos
            .minus(topico)
            .plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos
            .filter { topico -> topico.id == id }
            .getOrElse(0) { throw NotFoundException(notFoundMessage) }

        topicos = topicos.minus(topico)
    }

}
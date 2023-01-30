package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    private val topicos = PageImpl(listOf(TopicoTest.build()))

    private val paginacao: Pageable = mockk()
    private val repository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }
    private val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }
    private val topicoFormMapper: TopicoFormMapper = mockk()

    private val topicoService = TopicoService(repository, topicoViewMapper, topicoFormMapper)


    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoService.listar("Kotlin avançado", paginacao)
        verify(exactly = 1) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { repository.findAll(paginacao) }
    }

    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo`() {
        topicoService.listar(null, paginacao)
        verify(exactly = 0) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { repository.findAll(paginacao) }
    }

    @Test
    fun `deve lancar not found exception quando topico nao for achado`() {
         every { repository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }

        assertThat(atual.message).isEqualTo("Topico não encontrado")
    }

}
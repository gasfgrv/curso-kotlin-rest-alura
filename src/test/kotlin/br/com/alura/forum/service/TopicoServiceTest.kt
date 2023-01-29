package br.com.alura.forum.service

import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicoServiceTest {

    private val topicos = PageImpl(listOf(TopicoTest.build()))

    private val paginacao: Pageable = mockk()
    private val repository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }
    private val topicoViewMapper: TopicoViewMapper = mockk()
    private val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(repository, topicoViewMapper, topicoFormMapper)


    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        every { topicoViewMapper.map(any()) } returns TopicoViewTest.build()
        topicoService.listar("Kotlin avançado", paginacao)
        verify(exactly = 1) { repository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { repository.findAll(paginacao) }
    }
}
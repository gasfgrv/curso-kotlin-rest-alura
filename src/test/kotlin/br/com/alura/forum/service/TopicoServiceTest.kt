package br.com.alura.forum.service

import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.domain.PageImpl

class TopicoServiceTest {

    private val topicos = PageImpl(listOf(TopicoTest.build()))

    private val repository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
    }
    private val topicoViewMapper: TopicoViewMapper = mockk()
    private val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(repository, topicoViewMapper, topicoFormMapper)


}
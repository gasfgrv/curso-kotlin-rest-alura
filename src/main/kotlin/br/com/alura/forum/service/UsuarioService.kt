package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(
            id = 1L,
            nome = "Givaldo",
            email = "givaldo@email.com"
        )

        usuarios = listOf(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios
            .filter { usuario -> usuario.id == id }
            .first()
    }

}

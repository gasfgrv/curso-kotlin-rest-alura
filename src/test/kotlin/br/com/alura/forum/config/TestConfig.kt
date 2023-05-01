package br.com.alura.forum.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class TestConfig {
    companion object ContainerConfig {

        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0")
            .apply {
                withDatabaseName("testedb")
                withUsername("teste")
                withPassword("teste")
                withReuse(true)
            }

        @Container
        private val redisContainer = GenericContainer<Nothing>("redis:latest")
            .apply {
                withExposedPorts(6379)
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { mysqlContainer.jdbcUrl }
            registry.add("spring.datasource.username") { mysqlContainer.username }
            registry.add("spring.datasource.password") { mysqlContainer.password }

            registry.add("spring.redis.host") { redisContainer.containerIpAddress }
            registry.add("spring.redis.port") { redisContainer.firstMappedPort }
        }

    }

}

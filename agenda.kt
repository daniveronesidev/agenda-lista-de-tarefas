// Sistema simples de Lista de Tarefas em Kotlin
// Estrutura com boas práticas ✨
//
// Conceitos usados:
// - data class
// - enum
// - separação de responsabilidades
// - funções pequenas
// - mutabilidade controlada
// - validação básica
// - organização limpa

enum class Prioridade {
    BAIXA,
    MEDIA,
    ALTA
}

data class Tarefa(
    val id: Int,
    var titulo: String,
    var concluida: Boolean = false,
    var prioridade: Prioridade = Prioridade.MEDIA
)

class GerenciadorTarefas {

    // Encapsulamento:
    // a lista só pode ser alterada pela própria classe
    private val tarefas = mutableListOf<Tarefa>()

    private var proximoId = 1

    fun adicionarTarefa(
        titulo: String,
        prioridade: Prioridade = Prioridade.MEDIA
    ) {
        if (titulo.isBlank()) {
            println("❌ O título não pode estar vazio.")
            return
        }

        val tarefa = Tarefa(
            id = proximoId++,
            titulo = titulo.trim(),
            prioridade = prioridade
        )

        tarefas.add(tarefa)

        println("✅ Tarefa adicionada com sucesso!")
    }

    fun listarTarefas() {
        if (tarefas.isEmpty()) {
            println("📭 Nenhuma tarefa cadastrada.")
            return
        }

        println("\n📋 LISTA DE TAREFAS")
        println("---------------------------")

        tarefas.forEach { tarefa ->

            val status = if (tarefa.concluida) "✔" else "⏳"

            println(
                """
                ID: ${tarefa.id}
                Título: ${tarefa.titulo}
                Prioridade: ${tarefa.prioridade}
                Status: $status
                ---------------------------
                """.trimIndent()
            )
        }
    }

    fun concluirTarefa(id: Int) {

        val tarefa = tarefas.find { it.id == id }

        if (tarefa == null) {
            println("❌ Tarefa não encontrada.")
            return
        }

        tarefa.concluida = true

        println("🎉 Tarefa concluída!")
    }

    fun removerTarefa(id: Int) {

        val removida = tarefas.removeIf { it.id == id }

        if (removida) {
            println("🗑 Tarefa removida.")
        } else {
            println("❌ Tarefa não encontrada.")
        }
    }
}

fun main() {

    val gerenciador = GerenciadorTarefas()

    gerenciador.adicionarTarefa(
        "Estudar Kotlin",
        Prioridade.ALTA
    )

    gerenciador.adicionarTarefa(
        "Fazer exercícios",
        Prioridade.MEDIA
    )

    gerenciador.adicionarTarefa(
        "Organizar GitHub",
        Prioridade.BAIXA
    )

    gerenciador.listarTarefas()

    gerenciador.concluirTarefa(1)

    gerenciador.removerTarefa(3)

    gerenciador.listarTarefas()
}

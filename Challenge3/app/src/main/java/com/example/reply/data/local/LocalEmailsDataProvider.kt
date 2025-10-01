package com.example.reply.data.local

import com.example.reply.data.Email
import com.example.reply.data.MailboxType

object LocalEmailsDataProvider {

    val allEmails = listOf(
        Email(
            id = 1L,
            sender = LocalAccountsDataProvider.getContactAccountByUid(6L),
            recipients = listOf(LocalAccountsDataProvider.getDefaultUserAccount()),
            subject = "Bora resenhar calabreso?",
            body = """
                Calma, meu Gado! 
                
                E aí, tá fazendo o que de bom? Tava pensando em botar uma carne pra assar aqui em casa e tomar uma gelada. Se tiver de boa, cola aí pra gente botar o papo em dia. É o simples, o básico!
                
                Tmj,
                Davi
            """.trimIndent(),
            mailbox = MailboxType.Inbox,
            createAt = "5 mins atrás"
        ),
        Email(
            id = 2L,
            sender = LocalAccountsDataProvider.getContactAccountByUid(5L),
            recipients = listOf(LocalAccountsDataProvider.getDefaultUserAccount()),
            subject = "Racha hoje à noite?",
            body = """
                Fala, campeão!
                
                Seguinte, a galera tá se organizando pra um racha hoje lá na quadra do parque, lá pelas 19h. Topa colar? O rei já confirmou presença, hahaha.
                
                Vê se não amarela!
                
                Abraço,
                LeBron
            """.trimIndent(),
            mailbox = MailboxType.Inbox,
            createAt = "20 mins atrás",
            isStarred = true
        ),
        Email(
            id = 3L,
            sender = LocalAccountsDataProvider.getContactAccountByUid(4L),
            recipients = listOf(LocalAccountsDataProvider.getDefaultUserAccount()),
            subject = "He he",
            body = """
                Psiu... Não conta pra ninguém, visse?
                
                Aquela história toda foi só pra tirar umas férias. E quer saber? Acabei me apaixonando pelo Nordeste! A vida aqui em Pernambuco é arretada demais. Tô comendo um cuscuz com bode guisado que é melhor que qualquer prêmio que já ganhei.
                
                A música daqui, o frevo... Tive uma ideia pra um passo novo, uma mistura de Moonwalk com passo de frevo. Coisa de outro mundo!
                
                Tô precisando de uma opinião. Topa colar aqui pra gente testar e tomar um caldo de cana?
                
                Abraço,
                MJ
                
                P.S.: A luva branca agora é de crochê, pra combinar com o calor.
            """.trimIndent(),
            mailbox = MailboxType.Inbox,
            createAt = "1 hora atrás"
        ),
        Email(
            id = 4L,
            sender = LocalAccountsDataProvider.getContactAccountByUid(7L),
            recipients = listOf(LocalAccountsDataProvider.getDefaultUserAccount()),
            subject = "Um convite da sua Rainha!",
            body = """
                Oi, meu baixinho!
                
                Estou organizando um café da tarde especial com a antiga turma do programa. Vai ter bolo, paquitas e muita história pra contar. Queria muito que você viesse! Vai ser nesse sábado.
                
                Me avisa se o seu coração mandar você vir, tá?
                
                Beijinho, beijinho!
                Xuxa
            """.trimIndent(),
            mailbox = MailboxType.Inbox,
            createAt = "3 horas atrás",
            isStarred = true
        )
    )

    fun getAllFolders() = listOf(
        "Recibos",
        "Faculdade",
        "Férias",
        "Cupons",
        "Contas"
    )
}
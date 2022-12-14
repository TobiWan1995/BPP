package com.example.bpp.model

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/* Es existieren 3 unterschiedliche Typen von Tickets mit anderen Konditionen für den Preis. Während ein Standardticket immer für ein
* ganzes Wochenende gültig ist (also 3 Tage - Freitag bis Samstag), kann ein Tagesticket nur für einen Tag gültig sein und
* berechnet den Preis mit einem anderen Faktor(1,2). Ein Vip-Ticket kann mehrere oder
* nur einen Tag gültig sein und besitzt einen höheren Preisfaktor (1,5). Ein Tag soll
* mit dem normalen Preisfaktor = 1, 80€ kosten. Der Preis der Tickets wird vor dem speichern für den Gültigkeitszeitraum und
* den Typ jedes Tickets über den entsprechenden Faktor berechnet im Backend berechnet.*/
@Entity
class Ticket(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @NotNull var id: Long? = null,
    var ticketNummer: Long = 0,
    var typ: Typ = Typ.STANDARD,
    var gueltigVon: LocalDateTime = LocalDateTime.of(2022, 9, 2, 0, 0),
    var gueltigBis: LocalDateTime = LocalDateTime.of(2022, 9, 4, 23, 59),
    var preis: Double? = 240.00,
    var kundeId: Long = 0
)

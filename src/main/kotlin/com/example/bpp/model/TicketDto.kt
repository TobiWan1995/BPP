package com.example.bpp.model

import java.time.LocalDateTime

data class TicketDto(val ticketNummer: Long, var typ: String, var gueltigVon: LocalDateTime, var gueltigBis: LocalDateTime, var preis: Double?, var kunde: Kunde)

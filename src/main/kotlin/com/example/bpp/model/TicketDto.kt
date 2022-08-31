package com.example.bpp.model

import com.example.bpp.mockKundenApi.Kunde
import java.time.LocalDateTime

data class TicketDto(val ticketNummer: Long, var typ: String, var gueltigVon: LocalDateTime, var gueltigBis: LocalDateTime, var preis: Double?, val kunde: Kunde)

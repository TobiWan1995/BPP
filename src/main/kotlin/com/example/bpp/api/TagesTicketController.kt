package com.example.bpp.api

import com.example.bpp.model.TicketDto
import com.example.bpp.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class TagesTicketController @Autowired constructor(val ticketService: TicketService) {
    @GetMapping("/tag/{nummer}")
    fun getTagesTicket(@PathVariable("nummer") ticketNummer: Long): TicketDto {
        return ticketService.getTicket(ticketNummer)
    }

    @PostMapping("/tag")
    fun createTagesTicket(ticketDto: TicketDto): TicketDto {
        // berechnen des Preises -> Schlechtes Beispiel: Business-Logik die in den Controller gehört
        val days =  ticketDto.gueltigBis.dayOfYear - ticketDto.gueltigVon.dayOfYear + 1
        ticketDto.preis = ticketDto.typ.faktor*(days * 80.00)
        return ticketService.saveTicket(ticketDto)
    }

    @PutMapping("/tag")
    fun updateTagesTicket(ticketDto: TicketDto): TicketDto {
        // berechnen des Preises -> Schlechtes Beispiel: Business-Logik die in den Controller gehört
        val days =  ticketDto.gueltigBis.dayOfYear - ticketDto.gueltigVon.dayOfYear + 1
        ticketDto.preis = ticketDto.typ.faktor*(days * 80.00)
        return ticketService.updateTicket(ticketDto)
    }

    @DeleteMapping("/tag/{nummer}")
    fun deleteTagesTicket(@PathVariable("nummer") ticketNummer: Long) {
        ticketService.deleteTicket(ticketNummer)
    }
}

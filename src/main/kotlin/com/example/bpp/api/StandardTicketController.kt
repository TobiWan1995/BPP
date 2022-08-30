package com.example.bpp.api

import com.example.bpp.model.TicketDto
import com.example.bpp.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class StandardTicketController @Autowired constructor(val ticketService: TicketService) {

    @GetMapping("/standard/{nummer}")
    fun getStandardTicket(@PathVariable("nummer") ticketNummer: Long): TicketDto {
        return ticketService.getTicket(ticketNummer)
    }

    @PostMapping("/standard")
    fun createStandardTicket(ticketDto: TicketDto): TicketDto {
        // berechnen des Preises -> Schlechtes Beispiel: Business-Logik die in den Controller gehört
        ticketDto.preis = 240.00;
        return ticketService.saveTicket(ticketDto)
    }

    @PutMapping("/standard")
    fun updateStandardTicket(ticketDto: TicketDto): TicketDto {
        // berechnen des Preises -> Schlechtes Beispiel: Business-Logik die in den Controller gehört
        ticketDto.preis = 240.00;
        return ticketService.updateTicket(ticketDto)
    }

    @DeleteMapping("/standard/{nummer}")
    fun deleteStandardTicket(@PathVariable("nummer") ticketNummer: Long) {
        ticketService.deleteTicket(ticketNummer)
    }
}

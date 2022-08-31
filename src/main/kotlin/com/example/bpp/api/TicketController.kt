package com.example.bpp.api

import com.example.bpp.model.TicketDto
import com.example.bpp.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ticket")
class TicketController @Autowired constructor(val ticketService: TicketService) {

    @GetMapping("/{nummer}")
    fun retrieveTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long): TicketDto {
        return ticketService.getTicket(ticketNummer)
    }

    @PostMapping("/{typ}")
    fun createTicketByTyp(@RequestBody ticketDto: TicketDto): TicketDto {
        return ticketService.saveTicket(ticketDto)
    }

    @PutMapping("/{typ}")
    fun updateTicketByTyp(@RequestBody ticketDto: TicketDto): TicketDto {
        return ticketService.updateTicket(ticketDto)
    }

    @DeleteMapping("/{nummer}")
    fun deleteTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long) {
        ticketService.deleteTicket(ticketNummer)
    }
}

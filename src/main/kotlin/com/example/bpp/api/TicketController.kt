package com.example.bpp.api

import com.example.bpp.model.TicketDto
import com.example.bpp.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ticket")
class TicketController @Autowired constructor(val ticketService: TicketService) {

    // ToDo: implement Mapping with Mapstruct

    @GetMapping("/{nummer}")
    fun retrieveTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long): TicketDto {
        // return ticketService.retrieveTicketByTicketNummer(ticketNummer)
    }

    @PostMapping("/{typ}")
    fun createTicketByTyp(@RequestBody @PathVariable("typ") ticketDto: TicketDto): TicketDto {
        // return ticketService.saveTicketByTyp(ticket)
    }

    @PutMapping("/{typ}")
    fun updateTicketByTyp(@RequestBody @PathVariable("typ") ticketDto: TicketDto): TicketDto {
        // return ticketService.updateTicketByTyp(ticket)
    }

    @DeleteMapping("/{nummer}")
    fun deleteTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long) {
        ticketService.deleteTicketByTicketNummer(ticketNummer)
    }
}

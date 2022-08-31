package com.example.bpp.api

import com.example.bpp.mapping.TicketMapper
import com.example.bpp.model.TicketDto
import com.example.bpp.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/*
* To create or update a Ticket (via Postman) you need to add a corresponding Kunde to the h2-db first.
* You can do this via localhost:8080/h2-console or with a SQL-Script which I might add in the future.
* This is necessary because of the scenario this Ticket-Service is working. Normally it would have a
* corresponding Kunden-Service in the environment which already synced kunden-data into the Ticket-Service Database.
*  */
@RestController
@RequestMapping("/ticket")
class TicketController @Autowired constructor(private val ticketService: TicketService, private val ticketMapper: TicketMapper) {

    @GetMapping("/{nummer}")
    fun retrieveTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long): TicketDto {
        return ticketMapper.mapTicketToDto(ticketService.retrieveTicketByTicketNummer(ticketNummer))
    }

    @PostMapping("/{typ}")
    fun createTicketByTyp(@RequestBody ticketDto: TicketDto): TicketDto {
        return ticketMapper.mapTicketToDto(ticketService.saveTicketByTyp(ticketMapper.mapDtoToTicket(ticketDto)))
    }

    @PutMapping("/{typ}")
    fun updateTicketByTyp(@RequestBody ticketDto: TicketDto): TicketDto {
        return ticketMapper.mapTicketToDto(ticketService.updateTicketByTyp(ticketMapper.mapDtoToTicket(ticketDto)))
    }

    @DeleteMapping("/{nummer}")
    fun deleteTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long) {
        ticketService.deleteTicketByTicketNummer(ticketNummer)
    }
}

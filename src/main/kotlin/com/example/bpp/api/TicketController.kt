package com.example.bpp.api

import com.example.bpp.mapping.TicketMapper
import com.example.bpp.model.TicketDto
import com.example.bpp.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ticket")
class TicketController @Autowired constructor(val ticketService: TicketService, val ticketMapper: TicketMapper) {

    @GetMapping("/{nummer}")
    fun retrieveTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long): TicketDto {
        return ticketMapper.mapTicketToDto(ticketService.retrieveTicketByTicketNummer(ticketNummer))
    }

    @PostMapping("/{typ}")
    fun createTicketByTyp(@RequestBody @PathVariable("typ") ticketDto: TicketDto): TicketDto {
        return ticketMapper.mapTicketToDto(ticketService.saveTicketByTyp(ticketMapper.mapDtoToTicket(ticketDto)))
    }

    @PutMapping("/{typ}")
    fun updateTicketByTyp(@RequestBody @PathVariable("typ") ticketDto: TicketDto): TicketDto {
        return ticketMapper.mapTicketToDto(ticketService.updateTicketByTyp(ticketMapper.mapDtoToTicket(ticketDto)))
    }

    @DeleteMapping("/{nummer}")
    fun deleteTicketByTicketNummer(@PathVariable("nummer") ticketNummer: Long) {
        ticketService.deleteTicketByTicketNummer(ticketNummer)
    }
}

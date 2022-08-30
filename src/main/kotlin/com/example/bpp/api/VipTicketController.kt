package com.example.bpp.api

import com.example.bpp.model.TicketDto
import com.example.bpp.model.Typ
import com.example.bpp.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
class VipTicketController @Autowired constructor(val ticketService: TicketService) {

    @GetMapping("/vip/{nummer}")
    fun getVipTicket(@PathVariable("nummer") ticketNummer: Long): TicketDto {
        return ticketService.getTicket(ticketNummer)
    }

    @PostMapping("/vip")
    fun createVipTicket(@RequestBody ticketDto: TicketDto): TicketDto {
        // berechnen des Preises -> Schlechtes Beispiel: Business-Logik die in den Controller gehört
        val days =  ticketDto.gueltigBis.dayOfYear - ticketDto.gueltigVon.dayOfYear + 1
        ticketDto.preis = Typ.valueOf(ticketDto.typ).faktor*(days * 80.00)
        return ticketService.saveTicket(ticketDto)
    }

    @PutMapping("/vip")
    fun updateVipTicket(@RequestBody ticketDto: TicketDto): TicketDto {
        // berechnen des Preises -> Schlechtes Beispiel: Business-Logik die in den Controller gehört
        val days =  ticketDto.gueltigBis.dayOfYear - ticketDto.gueltigVon.dayOfYear + 1
        ticketDto.preis = Typ.valueOf(ticketDto.typ).faktor*(days * 80.00)
        return ticketService.updateTicket(ticketDto)
    }

    @DeleteMapping("/vip/{nummer}")
    fun deleteVipTicket(@PathVariable("nummer") ticketNummer: Long) {
        ticketService.deleteTicket(ticketNummer)
    }
}

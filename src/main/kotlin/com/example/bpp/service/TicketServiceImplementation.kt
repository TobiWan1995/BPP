package com.example.bpp.service

import com.example.bpp.db.TicketRepository
import com.example.bpp.mockKundenApi.MockKundenService
import com.example.bpp.model.Ticket
import com.example.bpp.model.TicketDto
import com.example.bpp.model.Typ
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TicketServiceImplementation @Autowired constructor(var ticketRepository: TicketRepository, var mockKundenService: MockKundenService): TicketService {

    override fun getTicket(ticketNummer: Long): TicketDto {
        val ticket = ticketRepository.findByTicketNummer(ticketNummer) ?: throw RuntimeException("No such Element")
        return buildTicketDto(ticket)
    }

    override fun saveTicket(ticketDto: TicketDto): TicketDto {
        val ticket = ticketRepository.save(Ticket(null, ticketDto.ticketNummer, Typ.valueOf(ticketDto.typ), ticketDto.gueltigVon, ticketDto.gueltigBis, ticketDto.preis, ticketDto.kunde?.id))
        return buildTicketDto(ticket)
    }

    override fun updateTicket(ticketDto: TicketDto): TicketDto {
        // prüfe ob das Ticket existiert
        val ticket = ticketRepository.findByTicketNummer(ticketDto.ticketNummer!!)
            ?: throw RuntimeException("No such Element")
        // update Ticket - ticketnummer should not change
        ticket.typ = Typ.valueOf(ticketDto.typ)
        ticket.gueltigBis = ticketDto.gueltigBis
        ticket.gueltigVon = ticketDto.gueltigVon
        ticket.preis = ticketDto.preis
        ticket.kundeId = ticketDto.kunde?.id
        return buildTicketDto(ticketRepository.save(ticket))
    }

    override fun deleteTicket(ticketNummer: Long) {
        // prüfe ob das Ticket existiert
        val ticket = ticketRepository.findByTicketNummer(ticketNummer) ?: throw RuntimeException("No such Element")
        ticketRepository.deleteById(ticket.id!!)
    }

    fun buildTicketDto(ticket: Ticket): TicketDto {
        return TicketDto(ticket.ticketNummer!!, ticket.typ.name, ticket.gueltigVon, ticket.gueltigBis, ticket.preis, mockKundenService.retrieveKundeByIdFromMockApi(ticket.kundeId))
    }
}

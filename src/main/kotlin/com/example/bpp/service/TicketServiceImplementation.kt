package com.example.bpp.service

import com.example.bpp.db.TicketRepository
import com.example.bpp.mockKundenApi.MockKundenService
import com.example.bpp.model.Ticket
import com.example.bpp.model.TicketDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TicketServiceImplementation @Autowired constructor(var ticketRepository: TicketRepository, var mockKundenService: MockKundenService): TicketService {

    override fun getTicket(ticketNummer: Long): TicketDto {
        val ticket = ticketRepository.findByTicketNummer(ticketNummer) ?: throw RuntimeException("No such Element")
        return buildTicketDto(ticket)
    }

    override fun saveTicket(ticketDto: TicketDto): TicketDto {
        return buildTicketDto(ticketRepository.save(Ticket(null, null, ticketDto.typ, ticketDto.gueltigVon, ticketDto.gueltigBis, ticketDto.preis, ticketDto.kunde?.id)))
    }

    override fun updateTicket(ticketDto: TicketDto): TicketDto {
        // prüfe ob das Ticket existiert
        val ticket = ticketRepository.findByTicketNummer(ticketDto.ticketNummer)
            ?: throw RuntimeException("No such Element")
        // update Ticket
        ticket.gueltigBis = ticketDto.gueltigBis
        ticket.gueltigVon = ticketDto.gueltigVon
        ticket.preis = ticketDto.preis
        ticket.typ = ticketDto.typ
        ticket.kundeId = ticketDto.kunde?.id
        return buildTicketDto(ticketRepository.save(ticket))
    }

    override fun deleteTicket(ticketNummer: Long) {
        // prüfe ob das Ticket existiert
        val ticket = ticketRepository.findByTicketNummer(ticketNummer) ?: throw RuntimeException("No such Element")
        ticketRepository.deleteById(ticket.id!!)
    }

    fun buildTicketDto(ticket: Ticket): TicketDto {
        return TicketDto(ticket.ticketNummer!!, ticket.typ, ticket.gueltigVon, ticket.gueltigBis, ticket.preis, mockKundenService.retrieveKundeByIdFromMockApi(ticket.kundeId))
    }
}

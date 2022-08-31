package com.example.bpp.service

import com.example.bpp.db.TicketRepository
import com.example.bpp.mockKundenApi.MockKundenService
import com.example.bpp.model.Ticket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TicketServiceImplementation @Autowired constructor(var ticketRepository: TicketRepository, var mockKundenService: MockKundenService): TicketService {

    override fun retrieveTicketByTicketNummer(ticketNummer: Long): Ticket {
        // prüfe ob das Ticket existiert und return falls true
        return ticketRepository.findByTicketNummer(ticketNummer) ?: throw RuntimeException("No such Element")
    }

    @Transactional
    override fun saveTicketByTyp(ticket: Ticket): Ticket {
        ticket.preis = (ticket.gueltigBis.dayOfMonth - ticket.gueltigVon.dayOfMonth)*(ticket.typ.faktor*80.0)
        return ticketRepository.save(ticket)
    }

    @Transactional
    override fun updateTicketByTyp(updatedTicket: Ticket): Ticket {
        // prüfe ob das Ticket existiert
        val oldTicket = ticketRepository.findByTicketNummer(updatedTicket.ticketNummer)
            ?: throw RuntimeException("No such Element")
        // update Ticket
        updatedTicket.id = oldTicket.id
        updatedTicket.ticketNummer = oldTicket.ticketNummer
        updatedTicket.preis = (updatedTicket.gueltigBis.dayOfMonth - updatedTicket.gueltigVon.dayOfMonth)*(updatedTicket.typ.faktor*80.0)
        return ticketRepository.save(updatedTicket)
    }

    @Transactional
    override fun deleteTicketByTicketNummer(ticketNummer: Long) {
        // prüfe ob das Ticket existiert
        val ticket = ticketRepository.findByTicketNummer(ticketNummer) ?: throw RuntimeException("No such Element")
        ticketRepository.deleteById(ticket.id!!)
    }
}

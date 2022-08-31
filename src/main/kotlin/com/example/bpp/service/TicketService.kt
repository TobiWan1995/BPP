package com.example.bpp.service

import com.example.bpp.model.Ticket

interface TicketService {
    fun retrieveTicketByTicketNummer(ticketNummer: Long): Ticket

    fun saveTicketByTyp(ticket: Ticket): Ticket

    fun updateTicketByTyp(updatedTicket: Ticket): Ticket

    fun deleteTicketByTicketNummer(ticketNummer: Long)
}

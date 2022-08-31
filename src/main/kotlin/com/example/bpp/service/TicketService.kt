package com.example.bpp.service

import com.example.bpp.model.Ticket
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Validated
interface TicketService {
    fun retrieveTicketByTicketNummer(ticketNummer: Long): Ticket

    fun saveTicketByTyp(@Valid ticket: Ticket): Ticket

    fun updateTicketByTyp(@Valid updatedTicket: Ticket): Ticket

    fun deleteTicketByTicketNummer(ticketNummer: Long)
}

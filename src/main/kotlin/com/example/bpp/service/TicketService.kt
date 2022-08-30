package com.example.bpp.service

import com.example.bpp.model.TicketDto

interface TicketService {
    fun getTicket(ticketNummer: Long): TicketDto

    fun saveTicket(ticketDto: TicketDto): TicketDto

    fun updateTicket(ticketDto: TicketDto): TicketDto

    fun deleteTicket(ticketNummer: Long)
}

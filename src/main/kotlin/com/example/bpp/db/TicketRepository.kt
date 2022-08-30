package com.example.bpp.db

import com.example.bpp.model.Ticket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : CrudRepository<Ticket, Long> {
    fun findByTicketNummer(nummer: Long): Ticket?
}

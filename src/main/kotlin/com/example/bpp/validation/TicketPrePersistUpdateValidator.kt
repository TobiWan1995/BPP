package com.example.bpp.validation

import com.example.bpp.model.Ticket
import com.example.bpp.model.Typ
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TicketPrePersistUpdateValidator : ConstraintValidator<TicketPrePersistUpdateAnnotation, Ticket> {
    override fun isValid(ticket: Ticket?, context: ConstraintValidatorContext?): Boolean {
        if(ticket == null) return false
        // prüfe ob der Gültigkeitszeitraum zum Typ des Tickets passt: vip = 1-3, tag = 1, standard = 3
        val days = (ticket.gueltigBis.dayOfMonth - ticket.gueltigVon.dayOfMonth) + 1
        return ((days == 3) && (ticket.typ == Typ.STANDARD)) || ((days in (1..3)) && (ticket.typ == Typ.VIP)) || ((days == 1) && (ticket.typ == Typ.TAG))
    }
}

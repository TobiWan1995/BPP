package com.example.bpp.mapping


import com.example.bpp.mockKundenApi.MockKundenService
import com.example.bpp.model.Ticket
import com.example.bpp.model.TicketDto
import org.mapstruct.AfterMapping
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.ReportingPolicy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR ,unmappedTargetPolicy = ReportingPolicy.WARN)
abstract class TicketMapper @Autowired constructor(val mockKundenService: MockKundenService? = null){

    @Mapping(target = "kunde", ignore = true)
    abstract fun mapTicketToDto(ticket: Ticket): TicketDto

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "kunde.id", target = "kundeId")
    abstract fun mapDtoToTicket(ticketDto: TicketDto): Ticket

    @AfterMapping
    fun completeDto(ticket: Ticket, @MappingTarget ticketDto: TicketDto) {
        val kunde = mockKundenService?.retrieveKundeByIdFromMockApi(ticket.kundeId)
            ?: throw RuntimeException("cannot map Ticket to TicketDto: no Kunde found")
        ticketDto.kunde = kunde
        ticketDto.typ = ticket.typ.name
    }


}

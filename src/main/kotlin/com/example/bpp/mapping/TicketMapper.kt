package com.example.bpp.mapping


import com.example.bpp.mockKundenApi.Kunde
import com.example.bpp.mockKundenApi.MockKundenService
import com.example.bpp.model.Ticket
import com.example.bpp.model.TicketDto
import org.mapstruct.*
import org.springframework.beans.factory.annotation.Autowired

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.WARN)
abstract class TicketMapper @Autowired constructor(private var mockKundenService: MockKundenService = MockKundenService()) {

    @Mapping(source = "kundeId", target = "kunde", qualifiedByName = ["addKundeToTicketDto"])
    abstract fun mapTicketToDto(ticket: Ticket): TicketDto

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "kunde.id", target = "kundeId")
    abstract fun mapDtoToTicket(ticketDto: TicketDto): Ticket

    @Named("addKundeToTicketDto")
    fun addKundeToTicketDto(id: Long): Kunde {
        return mockKundenService.retrieveKundeByIdFromMockApi(id) ?: throw RuntimeException("No matching Kunde found")
    }
}

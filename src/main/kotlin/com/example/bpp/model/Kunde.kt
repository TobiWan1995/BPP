package com.example.bpp.model

import javax.persistence.Entity
import javax.persistence.Id

/* After the refactoring Kunde is part of the TicketService-Database to make the DTO not dependent on the KundenServiceApi.
* The Data for Kunde is redundant and should be synced with tools like Kafka. It is desirable to keep the data of the Kunden-Table-Copy
* as minimal as possible. */
@Entity
data class Kunde(@Id val id: Long? = null, val name: String? = null, var alter: Int? = null, val geschlecht: String? = null)

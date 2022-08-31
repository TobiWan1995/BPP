package com.example.bpp.mockKundenApi

import com.example.bpp.model.Kunde
import org.springframework.stereotype.Service

/*
* Dieser Service repräsentiert eine API zu einem anderen Service. Dieser dient dazu die Verletzung der
* Prinzipien von Micro Services zu veranschaulichen. Ohne die Daten aus diesem Service kann der Ticket-Service keine
* Datentransfer-Objekte verarbeiten. Dadurch ist die Datenunabhängigkeit verletzt und der Service nicht mehr diskret.
* Diese Verletzungen wurden nun nach dem Refactoring durch eine Spiegelung der Daten von Kunde in der Ticket-Service-Db auf ein Minimum
* entfernt - die Daten müssen allerdings über Tools wie Kafka oder einen Request vom Kunden-Service bei Änderungen in Sync gehalten werden.
* */
@Service
class MockKundenService {
    // Repräsentiert eine Mock-Datenbank für den Kunden-Service
    var mockKundenDb = arrayOf(
            Kunde(1, "Max Mustermann", 30, "m"),
            Kunde(2, "Sophie Müller", 21, "w"),
            Kunde(3, "John Newman", 44, "m"),
            Kunde(4, "Katharina Heinrich", 20, "w"),
            Kunde(5, "Sven Friedrich", 34, "m"))

    // Mock-Funktion um Daten aus dem Kunde-Service zu erhalten
    fun retrieveKundeByIdFromMockApi(id: Long?): Kunde? {
        val kunde = mockKundenDb.first { kunde -> kunde.id == id }
        return kunde
    }
}

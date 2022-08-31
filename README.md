# Entwicklung eines stand. Micro-Service in Kotlin
Im Rahmen der Berufspratkischen-Phase an der Technischen Hochschule Mittelhessen, dient dieses Projekt
als Veranschaulichung der Aufgaben während des Praktikums im Unternehmen IT-Frankfurt GmbH.

Während des Praktikums soll ein Rest-Service in einen standardisierten Micro-Service refactored werden.
Der Begriff "standardisiert" beschreibt in diesem Kontext das einhalten der "Standard-Code-Practice" im Unternhemen
und vor allem den Prinzipien denen ein Micro Service zu folgen hat.

Zur Veranschaulichung wird ein Rest-Service mit fehlerhafter "Practice" hinsichtlich dem Unternehmen und
der eines Micro-Services erstellt, welcher sich auf dem Branch "unrefactored" befindet.
Im Branch "main" ist der überarbeitete, Micro-Service, zu finden.

Die Entwicklung erfolgt in Kotlin.

Der Service spiegelt den Use-Case eines Ticket-Systems für ein Event das über ein Wochenende stattfindet wider. Dabei existieren unterschiedliche
Typen von Tickets mit unterschiedlichen Konditionen (Tages-, VIP-, Standard-Ticket), wobei jedes dieser Tickets eine ähnliche Logik
im Service benötigt, die sich nur geringfügig unterscheidet. Die Attribute sind bei jedem Typ gleich und die Tickets
werden über ein Typ-Attribut differenziert.

Probleme die auf dem Branch "unrefactored" auftreten sind nachfolgend aufgelistet.

**Code-Practice und Architektur**

- Business-Logik in den Controllern
- Redundanter Code (folgt unter anderem aus ersterem)
- Mapping findet im Service statt und läuft nicht über Mapstruct
- Schlechte Benennung von Funktionen und Variablen
- Keine Validierungen implementiert - Tickets können unzulässig verändert werden
- Endpoints und Controller sind nicht generisch
- Schlechte Benennung der URI's
- Fehlerhaftes Mapping an den Endpoints
- Falsch aufgebaute Datentransfer-Objekte
- Mehrere Controller für gleichen Use-Case

**Micro-Service Prinzipien**

- Datenabhängigkeit aufgrund benötigter Daten eines anderen Services im DTO bzw. Frontend
- Folglich ist der Service nicht diskret und benötigt einen weiteren Service um zu handeln

Diese Verletzung der Prinzipien lässt sich durch mehrere Lösungsansätze, welche im Bericht
erläutert werden, umgehen. Der hier verwendete Lösungsansatz reduziert die benötigten Daten aus
dem anderen Service auf ein erforderliches Minimum und speichert diese als Kopie in der eigenen Datenbank
des Micro-Service. Dabei müssen diese Daten immer wieder synchronisiert werden, falls sich eine Änderung
(Create, Update, Delete) im anderen Service ergibt. Dadurch sind Daten im Worst-Case nur nicht aktuell aber jeder
Service in seiner Funktion diskret und Daten des jeweiligen Service nicht voneinander nur abhängig.

Ein weiterer interessanter Lösungsansatz ist die Verwendung von GraphQL. Dadurch können Daten im Backend
so reduziert werden das diese lediglich über eine ID aufeinander verweisen und im Frontend in eigeneständige 
Datenobjekte aus verschiedenen Services zusammen gebaut werden.

Im zugehörigen Bericht ist der Weg zum überarbeiteten Service mit allen
Lösungsansätzen zu finden. Dort findet sich ebenfalls eine Erläuterung der Vorteile
der überarbeiteten Version und weitere Informationen zu Micro-Services und dem
Praktikum im Unternehmen.

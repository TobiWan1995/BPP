package com.example.bpp.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [TicketPrePersistUpdateValidator::class])
annotation class TicketPrePersistUpdateAnnotation(
    val message: String = "Ticket ist nicht gültig.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

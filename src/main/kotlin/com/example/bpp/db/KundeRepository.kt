package com.example.bpp.db;

import com.example.bpp.model.Kunde
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository;

@Repository
interface KundeRepository : CrudRepository<Kunde, Long>



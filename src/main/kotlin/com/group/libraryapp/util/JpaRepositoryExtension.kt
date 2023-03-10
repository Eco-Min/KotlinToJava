package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

class JpaRepositoryExtension {
    fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
        return this.findByIdOrNull(id) ?: fail()
    }
}
package com.github.biancacristina.HelloWorldSS.repositories

import com.github.biancacristina.HelloWorldSS.domain.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository: JpaRepository<Profile, Long> {
    fun findByRolename(rolename: String): Profile
}
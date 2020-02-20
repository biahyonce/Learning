package com.github.biancacristina.HelloWorldSS.repositories

import com.github.biancacristina.HelloWorldSS.domain.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository: JpaRepository<Users, Long> {
    fun findOneByUserName(userName: String): Users?
}
package com.github.biancacristina.HelloWorldSS.services

import com.github.biancacristina.HelloWorldSS.domain.CustomUserDetails
import com.github.biancacristina.HelloWorldSS.repositories.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
open class CustomUserDetailsService (private val userRepository: UsersRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return CustomUserDetails(userRepository.findOneByUserName(username)!!)
    }

}
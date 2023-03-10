package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import org.springframework.stereotype.Service
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    private val userRepository: UserRepository,
){
    @Transactional
    fun saveUser(request: UserCreateRequest) {
        userRepository.save(User(request.name, request.age))
    }

    @Transactional
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            .map(UserResponse::of)
    }

    @Transactional(readOnly = true)
    fun updateUserName(request: UserUpdateRequest) {
        val user = userRepository.findByIdOrNull(request.id) ?: fail()
        user.updateName(request.name)
    }
    @Transactional
    fun deleteUser(name: String) {
        val user = userRepository.findByName(name)?:fail()
        userRepository.delete(user)
    }
}
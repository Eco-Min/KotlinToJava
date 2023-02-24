package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.JUser;
import com.group.libraryapp.domain.user.JUserRepository;
import com.group.libraryapp.dto.user.request.JUserCreateRequest;
import com.group.libraryapp.dto.user.request.JUserUpdateRequest;
import com.group.libraryapp.dto.user.response.JUserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JUserService {

  private final JUserRepository userRepository;

  public JUserService(JUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public void saveUser(JUserCreateRequest request) {
    JUser newJUser = new JUser(request.getName(), request.getAge());
    userRepository.save(newJUser);
  }

  @Transactional(readOnly = true)
  public List<JUserResponse> getUsers() {
    return userRepository.findAll().stream()
        .map(JUserResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public void updateUserName(JUserUpdateRequest request) {
    JUser user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
    user.updateName(request.getName());
  }

  @Transactional
  public void deleteUser(String name) {
    JUser user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    userRepository.delete(user);
  }

}

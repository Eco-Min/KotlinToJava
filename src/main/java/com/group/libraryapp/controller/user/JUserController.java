package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.JUserCreateRequest;
import com.group.libraryapp.dto.user.request.JUserUpdateRequest;
import com.group.libraryapp.dto.user.response.JUserResponse;
import com.group.libraryapp.service.user.JUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JUserController {

  private final JUserService jUserService;

  public JUserController(JUserService jUserService) {
    this.jUserService = jUserService;
  }

  @PostMapping("/user")
  public void saveUser(@RequestBody JUserCreateRequest request) {
    jUserService.saveUser(request);
  }

  @GetMapping("/user")
  public List<JUserResponse> getUsers() {
    return jUserService.getUsers();
  }

  @PutMapping("/user")
  public void updateUserName(@RequestBody JUserUpdateRequest request) {
    jUserService.updateUserName(request);
  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestParam String name) {
    jUserService.deleteUser(name);
  }

}

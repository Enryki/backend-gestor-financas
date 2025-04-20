package com.henrique.gestaofinancas.dto;

public class UserCreateDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String confirm_password;

    public UserCreateDTO(Long id, String username, String email, String password, String confirm_password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirm_password() {
        return confirm_password;
    }
    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}

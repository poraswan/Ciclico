package com.ciclico.models;

import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="users")
public class User {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @NotEmpty(message="El campo de UserName es obligatorio.")
 @Size(min=2, max=15, message="UserName debe de tener entre 2 y 30 caracteres.")
 private String username;
 
 @NotEmpty(message="El campo de Password es obligatorio.")
 @Size(min=6, max=128, message="Password debe de tener entre 6 y 128 caracteres.")
 private String password;
 
 @Transient
 @NotEmpty(message="El campo de Confirmación es obligatorio.")
 @Size(min=6, max=128, message="Confirmación debe de tener entre 6 y 128 caracteres.")
 private String passwordConfirmation;
 
 @Column(updatable=false)
 @DateTimeFormat(pattern="yyyy-MM-dd")
 private Date createdAt;
 
 @DateTimeFormat(pattern="yyyy-MM-dd")
 private Date updatedAt;
 
 @ManyToMany(fetch = FetchType.EAGER)
 @JoinTable(
     name = "users_roles", 
     joinColumns = @JoinColumn(name = "user_id"), 
     inverseJoinColumns = @JoinColumn(name = "role_id"))
 private List<Role> roles;
 
 @OneToMany(mappedBy="creator_product",fetch = FetchType.LAZY)
private List<Product> products;
 	
 	
 public User() {
 }

public List<Product> getProducts() {
	return products;
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
 public String getPassword() {
     return password;
 }
 public void setPassword(String password) {
     this.password = password;
 }
 public String getPasswordConfirmation() {
     return passwordConfirmation;
 }
 public void setPasswordConfirmation(String passwordConfirmation) {
     this.passwordConfirmation = passwordConfirmation;
 }
 

public Date getCreatedAt() {
     return createdAt;
 }
 public void setCreatedAt(Date createdAt) {
     this.createdAt = createdAt;
 }
 public Date getUpdatedAt() {
     return updatedAt;
 }
 public void setUpdatedAt(Date updatedAt) {
     this.updatedAt = updatedAt;
 }
 public List<Role> getRoles() {
     return roles;
 }
 public void setRoles(List<Role> roles) {
     this.roles = roles;
 }
 
 @PrePersist
 protected void onCreate(){
     this.createdAt = new Date();
 }
 @PreUpdate
 protected void onUpdate(){
     this.updatedAt = new Date();
 }
}
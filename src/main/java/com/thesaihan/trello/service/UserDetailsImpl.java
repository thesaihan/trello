package com.thesaihan.trello.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.thesaihan.trello.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Autowired
  private Account account;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = this.account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.account.getPassword();
  }

  @Override
  public String getUsername() {
    return this.account.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.account.getVerified().equals((short) 1);
  }

}
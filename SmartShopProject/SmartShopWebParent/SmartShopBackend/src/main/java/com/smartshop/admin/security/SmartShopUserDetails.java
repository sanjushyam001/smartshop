package com.smartshop.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartshop.common.entity.Role;
import com.smartshop.common.entity.User;

public class SmartShopUserDetails implements UserDetails{

	private User user;
	
	
	public SmartShopUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Role> roles = user.getRoles();
		
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		roles.forEach(role-> authorities.add(new SimpleGrantedAuthority(role.getName()) ));
		return authorities;
//		return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
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
		
		return user.isEnabled()	;
	}
	
	public String getFullname() {
		return this.user.getFirstName()+" "+this.user.getLastName();
	}

}
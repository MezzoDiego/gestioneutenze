package it.prova.gestioneutenze.security;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.prova.gestioneutenze.model.Utente;
import it.prova.gestioneutenze.repository.utente.UtenteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente user = utenteRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, getAuthorities(user));

	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Utente user) {
		String userRole = user.getRuolo().getDescrizione();
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRole);
		return authorities;
	}

}

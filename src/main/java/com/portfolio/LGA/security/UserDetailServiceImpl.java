package com.portfolio.LGA.security;


import com.portfolio.LGA.model.Usuario;
import com.portfolio.LGA.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioRepository usarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario =  usarioRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuari con el email "+email+" no existe"));

        return new UserDetailsImpl(usuario);
    }
}

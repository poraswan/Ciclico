package com.ciclico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ciclico.models.Product;
import com.ciclico.models.User;
import com.ciclico.repositories.ProductRepository;
import com.ciclico.repositories.RoleRepository;
import com.ciclico.repositories.UserRepository;


@Service
public class AppService {
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    
    @Autowired
    private ProductRepository productRepository;
    

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;    

    //Guardar un Usuario tipo ROLE_USER
    public User saveWithUserRole(User user, BindingResult result) {

        //Verificar password manualmente
        if(! user.getPassword().equals(user.getPasswordConfirmation())) {
            result.rejectValue("confirmPassword", "Matches", "Las contraseñas no coinciden");
        }

        if(!result.hasErrors()) {
            //Estamos estableciendo una nueva contraseña escriptandola al mismo tiempo que el usuario ingresa su password
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            //Asignandole un ROLE al usuario
            user.setRoles(roleRepository.findByName("ROLE_USER"));
            return userRepository.save(user);
        }else {
            return null;
        }
    }

    //Guardar un Usuario tipo ROLE_ADMIN
    public User saveWithAdminRole(User user, BindingResult result) {

        //Verificar password manualmente
        if(! user.getPassword().equals(user.getPasswordConfirmation())) {
            result.rejectValue("confirmPassword", "Matches", "Las contraseñas no coinciden");
        }

        if(!result.hasErrors()) {
            //Estamos estableciendo una nueva contraseña escriptandola al mismo tiempo que el usuario ingresa su password
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            //Asignandole un ROLE al usuario
            user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
            return userRepository.save(user);
        }else {
            return null;
        }
    }

    //Regresa Usuario en base a su username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    //Encontrar usuario por id
    public User findUserById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }
    
    //Crear un nuevo producto
    public Product newProduct(Product newProduct, User user) {
    	newProduct.setCreator_product(user);
    	return productRepository.save(newProduct);
    }
    
    //Busca todos los productos
    public List<Product> findAllProducts(){
    	return productRepository.findAll();
    }
    
}
    
package com.trilhaluxfacta.cadastrocliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trilhaluxfacta.cadastrocliente.domain.Cliente;
import com.trilhaluxfacta.cadastrocliente.dto.ClienteDTO;
import com.trilhaluxfacta.cadastrocliente.dto.ClienteNewDTO;
import com.trilhaluxfacta.cadastrocliente.repository.ClienteRepository;
import com.trilhaluxfacta.cadastrocliente.service.exception.DataIntegrityException;
import com.trilhaluxfacta.cadastrocliente.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired 
	private BCryptPasswordEncoder pe;
	
	public Cliente findById(Integer id) {
		Cliente cliente = repo.findById(id).orElseThrow( 
											() -> new ObjectNotFoundException(
												"Objeto não encontrado! Id:" + 
												id + ", Tipo: " + 
												Cliente.class.getName()));
		return cliente;
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir uma Cliente ");
		}
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = repo.findById(obj.getId()).orElseThrow( 
													() -> new ObjectNotFoundException(
																"Objeto não encontrado! Id:" + obj.getId() + ", Tipo: " + 
																Cliente.class.getName()));
	updateData(newObj, obj);
	return repo.save(newObj);
	}
	
	public Cliente updateSomeParams(Cliente obj) {
		Cliente newObj = repo.findById(obj.getId()).orElseThrow( 
				() -> new ObjectNotFoundException(
							"Objeto não encontrado! Id:" + obj.getId() + ", Tipo: " + 
							Cliente.class.getName()));
		updateDataSomeParams(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateData(Cliente newObj, Cliente obj) {
		newObj.setCpf(obj.getCpf());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void updateDataSomeParams(Cliente newObj, Cliente obj) {
		if(obj.getCpf() != null) {
			newObj.setCpf(obj.getCpf());
		}
		if(obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
		if(obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
	}
	
	public Cliente FromNewDTO(ClienteNewDTO objDto) {
		return new Cliente(objDto.getId(), 
						   objDto.getCpf(), 
						   objDto.getNome(),
						   objDto.getEmail(),
						   pe.encode(objDto.getSenha()),
						   objDto.getStatus());
	}
	
	public Cliente FromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), 
						   null, 
						   objDto.getNome(), 
						   objDto.getEmail(),
						   null,
						   objDto.getStatus());
	}
}

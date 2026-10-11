package br.edu.ifrs.petbemestar.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String telefone;
	
	public Tutor() {}
	
	public Tutor(String nome, String email, String telefone) {
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
	}
	
	public void setNome(String nome) {
		if(nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido");
		} else {
			this.nome = nome;
		}
	}
	
	public void setEmail(String email) {
		if(email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Email inválido");
		} else {
			this.email = email;
		}
	}
	
	public void setTelefone(String telefone) {
		if(telefone == null || telefone.trim().isEmpty()) {
			throw new IllegalArgumentException("Telefone inválido");
		} else {
			this.telefone = telefone;
		}
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	public String getTelefone() {
		return this.telefone;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}

	
	
}

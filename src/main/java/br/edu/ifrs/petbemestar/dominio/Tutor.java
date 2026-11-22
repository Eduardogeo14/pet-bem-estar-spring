package br.edu.ifrs.petbemestar.dominio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatorio")
	private String nome;
	
	@NotBlank(message = "Email é obrigatório")
	private String email;
	
	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tutor")
	private List<Bicho> bichos = new ArrayList<Bicho>();
	
	public Tutor() {}
	
	public Tutor(String nome, String email, String telefone) {
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
			this.nome = nome;
	}
	
	public void setEmail(String email) {
			this.email = email;
	}
	
	public void setTelefone(String telefone) {
			this.telefone = telefone;
	
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
	
	public List<Bicho> getBichos() {
		return bichos;
	}

	public void setBichos(List<Bicho> bichos) {
		this.bichos = bichos;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}

	

	
	
}

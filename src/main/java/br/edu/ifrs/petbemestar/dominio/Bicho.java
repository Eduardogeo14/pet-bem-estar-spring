package br.edu.ifrs.petbemestar.dominio;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Bicho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome do pet é obrigatório")
	private String nome;
	
	@NotNull(message = "Escolha o tipo de pet")
	@Enumerated(EnumType.STRING)
	private TipoAnimalEnum tipo;
	
	@NotNull(message = "Escolha o tamanho do pet")
	@Enumerated(EnumType.STRING)
	private TamanhoEnum tamanho;
	
	@NotNull(message = "Escolha o dono")
	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor dono;
	
	public Bicho() {}
	
	public Bicho(String nome, TipoAnimalEnum tipo, TamanhoEnum tamanho, Tutor dono) {
		setNome(nome);
		setTipo(tipo);
		setTamanho(tamanho);
		setDono(dono);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoAnimalEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoAnimalEnum tipo) {
		this.tipo = tipo;
	}

	public TamanhoEnum getTamanho() {
		return tamanho;
	}

	public void setTamanho(TamanhoEnum tamanho) {
		this.tamanho = tamanho;
	}

	public Tutor getDono() {
		return dono;
	}

	public void setDono(Tutor dono) {
		this.dono = dono;
	}

	@Override
	public String toString() {
		return "Bicho [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", tamanho=" + tamanho + ", dono=" + dono
				+ "]";
	}

	
	
}

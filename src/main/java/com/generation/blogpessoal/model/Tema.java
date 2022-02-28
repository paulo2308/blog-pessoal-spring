package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tema")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Você precisa preencher o tema")
	@Size(min = 1)
	private String descricao;
	
	/**
	 *  A Anotação @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL): indica 
	 *  que a Classe Tema terá um relacionamento do tipo One To Many (Um para Muitos) com a Classe 
	 *  Postagem
	 *  
	 *  mappedBy = "tema": Indica qual Objeto será utilizado como "chave estrangeira" no relacionamento,
	 *  em nosso exemplo será o objeto tema inserido na Classe Postagem
	 * 
	 *  cascade = CascadeType.ALL: Indica que toda e qualquer mudança efetuada num objeto da Classe 
	 *  Tema se propagará para todos os respectivos objetos associados.
	 *  Exemplo: Se eu apagar um tema, todas as postagens associadas ao tema apagado também serão apagadas.
	 * 
	 *  A Anotação @JsonIgnoreProperties("postagem") desabilita a recursividade
	 *  infinita durante a exibição dos dados no formato JSON
	 * 
	 */
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
}

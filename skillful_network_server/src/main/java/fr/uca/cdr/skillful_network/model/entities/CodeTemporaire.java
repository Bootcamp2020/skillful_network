package fr.uca.cdr.skillful_network.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.uca.cdr.skillful_network.security.CodeGeneration;


@Entity
@Table(name = "mot_de_passe_temporaire")
public class CodeTemporaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mot_de_passe_provisoire")
	private String codeTempo;

	@Column(name = "date_expiration")
	private LocalDateTime dateExpiration;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;

	@Override
	public String toString() {
		return "MotDePasse [id= " + id + ", code temporaire = " + codeTempo + ", dateExpiration=" + dateExpiration + ", user=" + user + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeTempo() {
		return codeTempo;
	}

	public void setCodeTempo(String codeTempo) {
		this.codeTempo = codeTempo;
	}

	public LocalDateTime getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(LocalDateTime dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public CodeTemporaire() {
		super();
		this.codeTempo = CodeGeneration.generateCode(8);
		this.dateExpiration = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
	}

//	public MotDePasse(Long id, String pwd, LocalDateTime dateExpiration) {
//		super();
//		this.id = id;
//		this.pwd = CodeGeneration.generateCode(8);
//		this.dateExpiration = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
//	}
//
//	public MotDePasse(String pwd, LocalDateTime dateExpiration) {
//		super();
//		this.pwd = CodeGeneration.generateCode(8);
//		this.dateExpiration = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
//	}
//	
//	public MotDePasse(String pwd) {
//		super();
//		this.pwd = CodeGeneration.generateCode(8);
//		this.dateExpiration = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
//	}

}

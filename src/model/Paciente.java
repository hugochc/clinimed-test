package model;

// Generated 07/06/2011 18:08:25 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Paciente generated by hbm2java
 */
@Entity
@Table(name = "pacientes")
public class Paciente implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codPac;
	private String nomePac;
	private String cpfPac;
	private String endPac;
	private String telPac;
	private String celPac;
	private Date dtNasc;

	public Paciente() {
	}

	public Paciente(String nomePac, String cpfPac) {
		this.nomePac = nomePac;
		this.cpfPac = cpfPac;
	}

	public Paciente(String nomePac, String cpfPac,
			String endPac, String telPac, String celPac, Date dtNasc) {
		this.nomePac = nomePac;
		this.cpfPac = cpfPac;
		this.endPac = endPac;
		this.telPac = telPac;
		this.celPac = celPac;
		this.dtNasc = dtNasc;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codPac", unique = true, nullable = false)
	public Long getCodPac() {
		return this.codPac;
	}

	public void setCodPac(Long codPac) {
		this.codPac = codPac;
	}

	@Column(name = "nomePac", nullable = false)
	public String getNomePac() {
		return this.nomePac;
	}

	public void setNomePac(String nomePac) {
		this.nomePac = nomePac;
	}

	@Column(name = "cpfPac", unique = true, nullable = false, length = 11)
	public String getCpfPac() {
		return this.cpfPac;
	}

	public void setCpfPac(String cpfPac) {
		this.cpfPac = cpfPac;
	}

	@Column(name = "endPac")
	public String getEndPac() {
		return this.endPac;
	}

	public void setEndPac(String endPac) {
		this.endPac = endPac;
	}

	@Column(name = "telPac", length = 20)
	public String getTelPac() {
		return this.telPac;
	}

	public void setTelPac(String telPac) {
		this.telPac = telPac;
	}

	@Column(name = "celPac", length = 20)
	public String getCelPac() {
		return this.celPac;
	}

	public void setCelPac(String celPac) {
		this.celPac = celPac;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtNasc", length = 10)
	public Date getDtNasc() {
		return this.dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

}
package com.trilhaluxfacta.cadastrocliente.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Fatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Integer idCliente;
	
	@Column(name = "dataComp")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date dataComp;
	
	@Column(name = "dataPag")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date datePag;
	
	@Column(name = "valor")
	private Double valor;
	
	public Fatura() {
	}

	public Fatura(Integer idCliente, Date dataComp, Date datePag, Double valor) {
		super();
		this.idCliente = idCliente;
		this.dataComp = dataComp;
		this.datePag = datePag;
		this.valor = valor;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataComp() {
		return dataComp;
	}

	public void setDataComp(Date dataComp) {
		this.dataComp = dataComp;
	}

	public Date getDatePag() {
		return datePag;
	}

	public void setDatePag(Date datePag) {
		this.datePag = datePag;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}

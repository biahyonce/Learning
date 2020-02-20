 package Aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		// COMENTEI A PARTE ABAIXO PORQUE ESSAS PESSOAS JA ESTAO SALVAS NO BD
		/*
		// Seta todos os "Integer" para null porque o BD eh quem vai dar o ID pra cada pessoa
		Pessoa p1 = new Pessoa(null,"Bianca","bianca@gmail.com");
		Pessoa p2 = new Pessoa(null,"Jose", "jose@gmail.com");
		Pessoa p3 = new Pessoa(null,"Maria", "maria@gmail.com");
		*/
		
		// EntityManager faz a conexao com o BD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa"); // INSTANCIA EntityManagerFactory com as config do "persistence.xml"
		EntityManager em = emf.createEntityManager(); // Usa o "emf" pra instanciar EntityManager
		
		/* COMENTEI A PARTE ABAIXO PORQUE ESSAS PESSOAS JA ESTAO SALVAS NO BD
		// Salvando cada pessoa no BD usando o metodo "persiste" do em
		em.getTransaction().begin(); // INICIA
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit(); // FINALIZA E CONFIRMA ALTERACOES
		*/
		
		// COMENTEI PORQUE AGORA IREI TESTAR COMO APAGA UMA PESSOA DO BANCO DE DADOS
		/*
		// BUSCANDO UMA PESSOA NO BD (ELA PRECISA ESTAR SALVA):
		Pessoa p = em.find(Pessoa.class, 2); // BUSCANDO PESSOA COM ID "2"
		
		System.out.println(p);
		*/
		
		// APAGANDO UMA PESSOA DO BD
		//Pessoa p = new Pessoa(2,null,null); // ISSO AQUI VAI DAR ERRO!
		// O JPA SO REMOVE UMA ENTIDADE MONITORADA
		// OBJETO MONITORADO PODE SER UM OBJETO QUE ACABEI DE INSERIR OU EH UM OBJETO QUE BUSQUEI NO BD E AINDA NAO FECHEI O "EM"
		Pessoa p = em.find(Pessoa.class, 3); // Jeito correto de remover!	
		em.getTransaction().begin(); // SE NAO FOR SO CONSULTA, PRECISA COLOCAR TRANSACAO!
		em.remove(p);
		em.getTransaction().commit();
		
		System.out.println("Finalizou aqui!");
		em.close(); // FECHA EM
		emf.close(); // FECHA EMF
	}

}

package com.example.demo;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.demo.dao.EnseignantRepository;
import com.example.demo.dao.MembreRepository;
import com.example.demo.service.IMembreService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor 
@EnableDiscoveryClient
public class MembreServiceApplication implements CommandLineRunner {
	
	MembreRepository membreRepository;
	EnseignantRepository enseignantRepository;
	IMembreService memberService;
	public static void main(String[] args) {
		SpringApplication.run(MembreServiceApplication.class, args);
	}
	public void run(String... args) throws Exception{
		
		//Créer et enregistrer deux étudiants
		Etudiant etd1=Etudiant.builder()
				.cin("11142931")
				.dateInscription(new Date())				
				.dateNaissance(new Date())
				.diplome("ingénieur")
				.email("abir.grati@enis.tn")
				.password("******")
				.encadrant(null)
				.cv("cv1")
				.nom("grati")
				.prenom("abir")
				.sujet("blockchain")
				//.Photo(null)
				.build();
		membreRepository.save(etd1);
		
		Etudiant etd2=Etudiant.builder()
				.cin("11132561")
				.dateInscription(new Date())				
				.dateNaissance(new Date())
				.diplome("mastère")
				.email("med.abid@enis.tn")
				.password("******")
				.encadrant(null)
				.cv("cv1")
				.nom("abid")
				.prenom("med")
				.sujet("devops")
				//.Photo(null)
				.build();
		membreRepository.save(etd2);
		
		//Créer et enregistrer d'un enseignant chercheur
		EnseignantChercheur ens1=EnseignantChercheur.builder()
				.cin("11111111")
				.dateNaissance(new Date())				
				.nom("nomEns1")
				.prenom("prenomEns1")
				.email("@gmail.com")
				.password("******")
				.cv("cv1")
				.grade("grade1")
				.etablissement("ENIS")
				.etudiant(null)
				.build();
		enseignantRepository.save(ens1);
		
		EnseignantChercheur ens2=EnseignantChercheur.builder()
				.cin("11111111")
				.dateNaissance(new Date())				
				.nom("nomEns2")
				.prenom("prenomEns2")
				.email("@gmail.com")
				.password("******")
				.cv("cv2")
				.grade("chercheur")
				.etablissement("ENIS")
				.etudiant(null)
				.build();
		enseignantRepository.save(ens2);
		
		//chercher un membre par son Id
		Membre m= membreRepository.findById(1L).get();
		System.out.println(m.getNom());
		
		//modifier un membre
		m.setCv("cv1.pdf");
		memberService.updateMember(m);		
		
		memberService.deleteMember(2L);
		
		
		memberService.AffecterEtudiantToEnseignant(1L,24L);
		memberService.AffecterEtudiantToEnseignant(2L,24L);
		
		memberService.ChercherEtdForEns(ens1);
		
		
		
	}	
	
			
	}



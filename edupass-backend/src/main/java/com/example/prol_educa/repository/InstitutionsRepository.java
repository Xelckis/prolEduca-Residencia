package com.example.prol_educa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.example.prol_educa.entities.Institutions;
import com.example.prol_educa.models.InstitutionsFilterDto;
import com.example.prol_educa.models.FilterHeroDto;
import java.util.List;

@Repository
public interface InstitutionsRepository extends JpaRepository<Institutions, Integer>{

    @Query("SELECT new com.example.prol_educa.models.InstitutionsFilterDto(i.id, i.name, i.city, i.neighborhood) FROM Institutions i")
    List<InstitutionsFilterDto> findInstituicoesFilter();

}

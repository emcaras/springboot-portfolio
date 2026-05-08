package com.emcaras.portfolio.service;

import com.emcaras.portfolio.model.Skill;
import com.emcaras.portfolio.repository.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SkillServiceTest {
    @Autowired
    private ISkillService skillService;
    @Autowired
    private ISkillRepository skillRepository;


    @Test
    public void testSaveValidSkill(){
        Skill validSkill = new Skill(null, "Italiano", 80, "icono.png", null);

        Skill skillSaved = this.skillService.save(validSkill);

        assertNotNull(skillSaved.getId(), "El Objeto guardado debe tener un ID asignado");
        assertNotNull(this.skillRepository
                .findById(skillSaved.getId()).orElse(null), "El objeto guardado debe existir en la base de datos");
    }

    @Test
    public void testSaveInvaliudSkill(){
        Skill invalidSkill = new Skill(null, "Italiano", 80, "icono.png", null);

         assertThrows(null,() -> this.skillService.save(invalidSkill));
    }
}

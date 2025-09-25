package com.smarttmt.causrequ;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class caurequRepositoryTest {

    @Autowired
    CausrequIRepository causrequIRepository;

    @Test
    void findByAll() {

        CausrequEntity causrequEntity = new CausrequEntity();
        causrequEntity.setCarecodi("999");
        causrequEntity.setCaredesc("data prueba");

        causrequIRepository.save(causrequEntity);

        List<CausrequEntity> listCausrequEntityResul = causrequIRepository.findAll();

        CausrequEntity causrequResul = listCausrequEntityResul.get(0);

        assertEquals(listCausrequEntityResul.size(), 1);
        assertEquals(causrequResul.getCaredesc(), "data prueba");

    }

}

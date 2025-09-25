package com.smarttmt.requerim;

import com.smarttmt.utilities.Constantes;
import com.smarttmt.utilities.Utilidades;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JPAFilterVwRequerim implements IJPAFilterVwRequerim {

    Logger logger = LoggerFactory.getLogger(JPAFilterVwRequerim.class);

    @Override
    public Specification getFilters(String... params) {

        logger.info("init getFilters params:{}", params);

        try {

            String requclie = params[0];
            String requcodi = params[1];
            String requfech = params[2];
            String requfeco = params[3];
            String requesta = params[4];
            String requdeta = params[5];
            String requfechInit = params[6];
            String requfechFina = params[7];

            List<Predicate> predicates = new ArrayList<>();

            return (root, query, cb) -> {

                predicates.clear();

                if (!requclie.isEmpty()) {
                    logger.info("getFilters requclie:{}", requclie);
                    predicates.add(cb.equal(root.get("requclie"), requclie));
                }
                if (!requcodi.isEmpty()) {
                    logger.info("getFilters requcodi:{}", requcodi);
                    predicates.add(cb.equal(root.get("requcodi"), requcodi));
                }
                if (!requfech.isEmpty()) {
                    logger.info("getFilters requfech:{}", requfech);
                    Date fecha = Utilidades.getDateSimpleDateFormat(Constantes.FORMATO_ESTANDAR_FECHA, requfech);
                    if (fecha != null) {
                        predicates.add(cb.equal(root.get("requfech"), fecha));
                    }
                }
                if (!requfeco.isEmpty()) {
                    logger.info("getFilters requfeco:{}", requfeco);
                    Date fecha = Utilidades.getDateSimpleDateFormat(Constantes.FORMATO_ESTANDAR_FECHA, requfeco);
                    if (fecha != null) {
                        predicates.add(cb.equal(root.get("requfeco"), fecha));
                    }
                }
                if (!requesta.isEmpty()) {
                    logger.info("getFilters requesta:{}", requesta);
                    predicates.add(cb.like(cb.lower(root.get("requesta")), requesta));
                }

                if (!requdeta.isEmpty()) {
                    logger.info("getFilters requdeta:{}", requdeta);
                    String requdetaLike = "%" + requdeta.toLowerCase() + "%";
                    predicates.add(cb.like(cb.lower(root.get("requdeta")), requdetaLike));
                }

                if (!requfechInit.isEmpty() && !requfechFina.isEmpty()) {
                    logger.info("getFilters requfechInit:{} requfechFina:{}", requfechInit, requfechFina);
                    String fechCambioFormaInic = Utilidades.getFormatStr(requfechInit, "dd-MM-yyyy", "yyyy-MM-dd");
                    String fechCambioFormaFina = Utilidades.getFormatStr(requfechFina, "dd-MM-yyyy", "yyyy-MM-dd");
                    Date fechInic = Utilidades.getFechaDesdeCadena(fechCambioFormaInic, "yyyy-MM-dd");
                    Date fechFina = Utilidades.getFechaDesdeCadena(fechCambioFormaFina, "yyyy-MM-dd");
                    predicates.add(cb.between(root.get("requfech"), fechInic, fechFina));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            };
        } catch (Exception ex) {
            logger.error("Error Exception:{}", ex.toString());
        }

        return null;
    }

}

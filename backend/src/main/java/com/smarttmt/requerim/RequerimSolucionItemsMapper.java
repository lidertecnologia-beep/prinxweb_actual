package com.smarttmt.requerim;

import com.smarttmt.modelos.RequerimCreateModel;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class RequerimSolucionItemsMapper implements Function<RequerimCreateModel, String> {

    @Override
    public String apply(RequerimCreateModel requerim) {
        StringBuilder sb = new StringBuilder();
        sb.append("SOLUCODI:").append(StringUtils.isEmpty(requerim.getRequcodi()) ? "" : requerim.getRequcodi()).append("|");
        sb.append("SOLUESTA:").append(StringUtils.isEmpty(requerim.getRequesta()) ? "" : requerim.getRequesta()).append("|");
        sb.append("SOLUPERE:").append(StringUtils.isEmpty(requerim.getRequpere()) ? "" : requerim.getRequpere()).append("|");
        sb.append("SOLUPESO:").append(StringUtils.isEmpty(requerim.getRequpeso()) ? "" : requerim.getRequpeso()).append("|");
        sb.append("SOLUCLIE:").append(StringUtils.isEmpty(requerim.getRequclie()) ? "" : requerim.getRequclie()).append("|");
        sb.append("SOLUMECO:").append(StringUtils.isEmpty(requerim.getRequmeco()) ? "" : requerim.getRequmeco()).append("|");
        sb.append("SOLUTIPO:").append(StringUtils.isEmpty(requerim.getRequtipo()) ? "" : requerim.getRequtipo()).append("|");
        sb.append("SOLUSOLI:").append(StringUtils.isEmpty(requerim.getRequsoli()) ? "" : requerim.getRequsoli()).append("|");
        sb.append("SOLUFESI:").append(StringUtils.isEmpty(requerim.getRequfech()) ? "" : requerim.getRequfech()).append("|");
        sb.append("SOLUPROB:").append(StringUtils.isEmpty(requerim.getRequdeta()) ? "" : requerim.getRequdeta()).append("|");
        sb.append("SOLUTIRE:").append(StringUtils.isEmpty(requerim.getRequtire()) ? "" : requerim.getRequtire()).append("|");
        sb.append("SOLUPRIO:").append(StringUtils.isEmpty(requerim.getRequprio()) ? "" : requerim.getRequprio()).append("|");
        sb.append("SOLUPROD:").append(StringUtils.isEmpty(requerim.getRequprod()) ? "" : requerim.getRequprod()).append("|");
        sb.append("SOLUOBJE:").append(StringUtils.isEmpty(requerim.getRequobpr()) ? "" : requerim.getRequobpr()).append("|");
        sb.append("SOLUPERS:").append(StringUtils.isEmpty(requerim.getRequpere()) ? "" : requerim.getRequpere()).append("|");
        sb.append("SOLUFESO:").append(StringUtils.isEmpty(requerim.getRequfech()) ? "" : requerim.getRequfech()).append("|");
        sb.append("SOLUCLAS:").append(StringUtils.isEmpty(requerim.getRequclas()) ? "" : requerim.getRequclas()).append("|");
        sb.append("SOLUSOLU:").append(".");
        return sb.toString();
    }
}

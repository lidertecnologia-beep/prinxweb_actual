package com.smarttmt.requerim;

import org.springframework.data.jpa.domain.Specification;

public interface IJPAFilterVwRequerim<T> {

    Specification<T> getFilters(String... params);

}

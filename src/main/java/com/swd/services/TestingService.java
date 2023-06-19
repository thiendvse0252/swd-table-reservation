package com.swd.services;

import com.swd.cms.criteria.TestingCriteria;
import com.swd.entities.Testing;
import com.swd.exception.EntityNotFoundException;
import com.swd.repositories.TestingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestingService {
    @Autowired
    private TestingRepository testingRepository;

    public Testing getById(Long id) {
        return testingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Testing.class, "id", id.toString()));
    }

    public Testing saveTesting(Testing testing) {
        return testingRepository.save(testing);
    }

    public Page<Testing> getByCriteria(TestingCriteria criteria, Pageable page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("id").ascending());
        final Specification<Testing> specification = createCriteria(criteria);
        return testingRepository.findAll(specification, pageable);
    }

    public static Specification<Testing> createCriteria(final TestingCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(criteria.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + criteria.getName() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Boolean existsById(Long id) {
        return testingRepository.existsById(id);
    }

    public void deleteTestingById(Long id) {
        testingRepository.deleteById(id);
    }
}

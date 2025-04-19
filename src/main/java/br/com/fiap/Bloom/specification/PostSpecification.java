package br.com.fiap.Bloom.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Bloom.model.Post;
import br.com.fiap.Bloom.model.PostFilter;
import jakarta.persistence.criteria.Predicate;

public class PostSpecification {

    public static Specification<Post> withFilters(PostFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtros por título
            if (filter.title() != null) {
                predicates.add(cb.like(
                    cb.lower(root.get("titulo")), "%" + filter.title().toLowerCase() + "%"));
            }

            // Filtros por autor
            if (filter.author() != null && !filter.author().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("user").get("nome")), "%" + filter.author().toLowerCase() + "%"));
            }            

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("date"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(
                        cb.equal(root.get("date"), filter.startDate()));
            }


            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);

        };
    }
}

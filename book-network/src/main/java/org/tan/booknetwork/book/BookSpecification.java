package org.tan.booknetwork.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    //In order to create a specification for the repository we have to
    // define a method as a static for better execution.
    public static Specification<Book> withOwnerId(Integer ownerId){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"),ownerId));
    }
}

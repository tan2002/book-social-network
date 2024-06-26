package org.tan.booknetwork.history;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.tan.booknetwork.book.Book;
import org.tan.booknetwork.common.BaseEntity;
import org.tan.booknetwork.user.User;

import java.util.List;
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BookTransactionHistory extends BaseEntity {

    //User relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //Book Relationship
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private boolean returned;
    private boolean returnApproved;

}

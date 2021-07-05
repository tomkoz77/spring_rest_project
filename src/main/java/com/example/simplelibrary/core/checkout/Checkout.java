package com.example.simplelibrary.core.checkout;

import com.example.simplelibrary.core.book.Book;
import com.example.simplelibrary.core.reader.Reader;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="checkouts")
public class Checkout {

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "checkout_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "checkout_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkout_id_seq")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_readers")
    private Reader reader;

    @Column(name = "checkout_date")
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "checkouts_books",
            joinColumns = {@JoinColumn(name = "id_checkouts")},
            inverseJoinColumns = {@JoinColumn(name = "id_books")})
    private Set<Book> books = new HashSet<>();

    public long getId() {
        return id;
    }

    public Reader getReader() {
        return reader;
    }

    public Date getDate() {
        return date;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}

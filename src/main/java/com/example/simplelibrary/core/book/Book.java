package com.example.simplelibrary.core.book;

import com.example.simplelibrary.core.author.Author;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "book_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "book_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="shot_desc")
    private String shortDescription;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "authors_books",
            joinColumns = { @JoinColumn(name = "id_books") },
            inverseJoinColumns = { @JoinColumn(name = "id_authors") })
    private Set<Author> authors = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}

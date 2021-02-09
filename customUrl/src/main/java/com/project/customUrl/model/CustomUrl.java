package com.project.customUrl.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@MappedSuperclass
public class CustomUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String longUrl;
    private String shortUrl;
    private int accessCount;
    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;
    private Date expirationDate;
}

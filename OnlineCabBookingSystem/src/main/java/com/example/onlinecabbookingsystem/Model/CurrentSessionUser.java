package com.example.onlinecabbookingsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentSessionUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer userId;
    private String uuid;
    private LocalDateTime localDateTime;

    public CurrentSessionUser(Integer userId, String uuid, LocalDateTime localDateTime) {
        super();
        this.userId = userId;
        this.uuid = uuid;
        this.localDateTime = localDateTime;
    }
}

package com.cab.fab5cabbooking.Model;

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
  
    @Column(unique = true)
    private Integer id;
    
    private String uuid;
    
    private LocalDateTime localDateTime;
   
    private String role;
   
}

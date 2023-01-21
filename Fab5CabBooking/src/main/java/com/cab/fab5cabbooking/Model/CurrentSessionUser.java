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
//    @Column(unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer currentsessionid;
    private Integer userid;
    
    private String uuid;
    
    private LocalDateTime localDateTime;
   
    private String role;

    public CurrentSessionUser(Integer userid, String uuid, LocalDateTime localDateTime, String role) {
        this.userid = userid;
        this.uuid = uuid;
        this.localDateTime = localDateTime;
        this.role = role;
    }
}

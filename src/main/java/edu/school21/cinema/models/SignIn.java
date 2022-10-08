package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignIn {
    private Long id;
    private Long userId;
    private Timestamp date;
    private String ip;
}

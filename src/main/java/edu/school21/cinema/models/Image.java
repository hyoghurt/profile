package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private UUID id;
    private Long userId;
    private String name;
    private Long size;
    private String mime;
}

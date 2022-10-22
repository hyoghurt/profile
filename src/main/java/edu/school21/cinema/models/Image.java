package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private String id;
    private Long userId;
    private String name;
    private Long size;
    private String mime;
}

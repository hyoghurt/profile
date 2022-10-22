package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private String id;
    private String name;
    private String size;
    private String mime;
}

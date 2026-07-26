package com.ncv.security_testing.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntityDto {
    private Long id;
    private String title;
    private String description;
}

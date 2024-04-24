package org.example.Entities.dto;

import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private String name;
    private Date birthDate;
}

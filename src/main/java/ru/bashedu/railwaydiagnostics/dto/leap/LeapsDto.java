package ru.bashedu.railwaydiagnostics.dto.leap;

import lombok.Data;

import java.util.List;

@Data
public class LeapsDto {
    private Long id;
    private List<LeapDto> leaps;
}

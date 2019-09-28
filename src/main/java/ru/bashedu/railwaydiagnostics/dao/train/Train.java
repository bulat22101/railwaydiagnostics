package ru.bashedu.railwaydiagnostics.dao.train;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Train {
    private Long id;
    private Double coefficient;
}

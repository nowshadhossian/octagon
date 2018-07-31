package com.kids.crm.json.graph;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphStudentResult
{
    private LocalDate date;
    private long correct;
    private long wrong;
}

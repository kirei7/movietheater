package com.epam.rd.movietheater.util.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatView {
    private Long number;
    private boolean booked;
}

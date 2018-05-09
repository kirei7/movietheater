package com.epam.rd.movietheater.dao.impl.jpa.repository;

import com.epam.rd.movietheater.model.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}

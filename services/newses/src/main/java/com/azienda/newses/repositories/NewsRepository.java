package com.azienda.newses.repositories;

import com.azienda.newses.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Long>
{

}

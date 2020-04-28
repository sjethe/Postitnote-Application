package com.backendexample.postitnotes.dao;

import com.backendexample.postitnotes.dao.entity.Postit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostitnoteRepository extends JpaRepository<Postit, Long> {

}

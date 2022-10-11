package com.nashtech.rookies.repository.spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.rookies.model.Song;
import com.nashtech.rookies.model.SongId;

@Repository
public interface SongsRepository extends CrudRepository<Song, SongId> {}
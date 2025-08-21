package com.example.project0820;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    List<Board> findAll();
    Optional<Board> findById(Long id);
    void save(Board board);
    void update(Board board);
    void deleteById(Long id);
}
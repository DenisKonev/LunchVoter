package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.MenuItem;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM MenuItem mi WHERE mi.id=:id AND mi.menu.id=:menuId")
    int delete(@Param("id") Long id, @Param("menuId") Long menuId);

    MenuItem getReferenceById(Long id);

    @Query("SELECT mi FROM MenuItem mi WHERE mi.menu.id=:menuId ORDER BY mi.name")
    List<MenuItem> getAllByMenuId(@Param("menuId") Long menuId);

    @Query("SELECT mi FROM MenuItem mi JOIN FETCH mi.menu WHERE mi.id = ?1 and mi.menu.id = ?2")
    MenuItem getWithMenu(Long id, Long menuId);
}
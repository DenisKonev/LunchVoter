package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.MenuItem;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM MenuItem mi WHERE mi.id=:id AND mi.menu.id=:menuId")
    int delete(@Param("id") Integer id, @Param("menuId") Integer menuId);

    MenuItem getReferenceById(Integer id);

    @Query("SELECT mi FROM MenuItem mi WHERE mi.menu.id=:menuId ORDER BY mi.name")
    List<MenuItem> getAllByMenuId(@Param("menuId") Integer menuId);
}
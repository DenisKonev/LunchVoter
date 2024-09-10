package ru.topjava.lunchvoter.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.MenuItem;

import java.util.List;

@Repository
public class MenuItemRepository extends BaseRepository<MenuItem, Long> {

    private final CrudMenuItemRepository crudMenuItemRepository;
    private final CrudMenuRepository crudMenuRepository;

    public MenuItemRepository(CrudMenuItemRepository crudMenuItemRepository, CrudMenuRepository crudMenuRepository) {
        super(crudMenuItemRepository);
        this.crudMenuItemRepository = crudMenuItemRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Transactional
    public MenuItem save(MenuItem menuItem, Long menuId) {
        if (!menuItem.isNew() && get(menuItem.getId(), menuId) == null) {
            return null;
        }
        menuItem.setMenu(crudMenuRepository.getReferenceById(menuId));
        return crudMenuItemRepository.save(menuItem);
    }

    public boolean delete(Long id, Long menuId) {
        return crudMenuItemRepository.delete(id, menuId) != 0;
    }

    public MenuItem get(Long id, Long menuId) {
        return crudMenuItemRepository.findById(id)
                .filter(menuItem -> menuItem.getMenu().getId().equals(menuId))
                .orElse(null);
    }

    public List<MenuItem> getAll(Long menuId) {
        return crudMenuItemRepository.getAllByMenuId(menuId);
    }

    public MenuItem getWithMenu(Long id, Long menuId) {
        return crudMenuItemRepository.getWithMenu(id, menuId);
    }
}

package ru.topjava.lunchvoter.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.MenuItem;

import java.util.List;

@Repository
public class MenuItemRepository extends BaseRepository<MenuItem, Integer> {

    private final CrudMenuItemRepository crudMenuItemRepository;
    private final CrudMenuRepository crudMenuRepository;

    public MenuItemRepository(CrudMenuItemRepository crudMenuItemRepository, CrudMenuRepository crudMenuRepository) {
        super(crudMenuItemRepository);
        this.crudMenuItemRepository = crudMenuItemRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    public MenuItem get(Integer id, Integer menuId) {
        return crudMenuItemRepository.findById(id)
                .filter(menuItem -> menuItem.getMenu().getId().equals(menuId))
                .orElse(null);
    }

    public List<MenuItem> getAll(Integer menuId) {
        return crudMenuItemRepository.getAllByMenuId(menuId);
    }

    @Transactional
    public MenuItem save(MenuItem menuItem, Integer menuId) {
        if (!menuItem.isNew() && get(menuItem.getId(), menuId) == null) {
            return null;
        }
        menuItem.setMenu(crudMenuRepository.getReferenceById(menuId));
        return crudMenuItemRepository.save(menuItem);
    }

    @Transactional
    public boolean delete(Integer id, Integer menuId) {
        return crudMenuItemRepository.delete(id, menuId) != 0;
    }
}

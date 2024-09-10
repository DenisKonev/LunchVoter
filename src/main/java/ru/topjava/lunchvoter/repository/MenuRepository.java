package ru.topjava.lunchvoter.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Menu;

import java.util.List;

@Repository
public class MenuRepository extends BaseRepository<Menu, Long> {

    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public MenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        super(crudMenuRepository);
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Menu save(Menu menu, Long restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getReferenceById(restaurantId));
        return crudMenuRepository.save(menu);
    }

    public boolean delete(Long id, Long restaurantId) {
        return crudMenuRepository.delete(id, restaurantId) != 0;
    }

    public Menu get(Long id, Long restaurantId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId().equals(restaurantId))
                .orElse(null);
    }

    public List<Menu> getAll(Long restaurantId) {
        return crudMenuRepository.getAllByRestaurantId(restaurantId);
    }

    public Menu getWithRestaurant(Long id, Long restaurantId) {
        return crudMenuRepository.getWithRestaurant(id, restaurantId);
    }
}

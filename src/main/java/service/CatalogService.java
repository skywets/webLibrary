package service;


import dao.CatalogDao;
import entity.Catalog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CatalogService {
    private CatalogDao catalogDao;

    public List<Catalog> getAll() {
        return catalogDao.getAll();
    }

    public Catalog getItem(long id) {
        return catalogDao.get(id).orElseThrow();
    }

    public Long createCatalog(Catalog catalog) {
       return catalogDao.create(catalog);
    }

    public void editCatalog(long id, Catalog catalog) {
        catalog.setId(id);
        catalogDao.update(catalog);
    }

    public void deleteCatalog(Catalog catalog) {
        catalogDao.delete(catalog);
    }
}

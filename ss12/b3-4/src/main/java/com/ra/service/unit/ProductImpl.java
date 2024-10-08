package com.ra.service.unit;

import com.ra.model.Product;
import com.ra.dao.IGenericDao;
import com.ra.dao.unit.ProductDaoImpl;
import com.ra.service.IGenericService;

import java.util.List;

public class ProductImpl implements IGenericService<Product,Integer> {
    private static final IGenericDao<Product,Integer> productDao = new ProductDaoImpl();
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void addAndUpdate(Product product) {
        Integer id =product.getProductId();
        int index=findIndexByID(id);
        if(index!=-1){
            Product existingProduct=findAll().get(index);
            existingProduct.setProductName(product.getProductName());
            existingProduct.setManufacturer(product.getManufacturer());
            existingProduct.setBatch(product.getBatch());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCreated(product.getCreated());
            existingProduct.setProductStatus(product.isProductStatus());
            productDao.andAndUpdate(existingProduct);
        }

        productDao.andAndUpdate(product);


    }

    @Override
    public void delete(Integer id) {
        productDao.remove(id);

    }

    @Override
    public int findIndexByID(Integer id) {
        return productDao.findIndexByID(id);
    }
}
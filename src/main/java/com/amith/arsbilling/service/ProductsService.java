package com.amith.arsbilling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amith.arsbilling.exception.RecordNotFoundException;
import com.amith.arsbilling.model.ProductsEntity;
import com.amith.arsbilling.repository.ProductsRepository;
 
@Service
public class ProductsService {
     
    @Autowired
    ProductsRepository repository;
     
    private List<ProductsEntity> purchasedProducts = new ArrayList<>();
	
	public void setPurchasedProducts(ProductsEntity product) {     
		purchasedProducts.add(product);
	}
	
	public List<ProductsEntity> getPurchasedProducts() {     
	    return purchasedProducts;
	}
	
    public List<ProductsEntity> getAllProducts()
    {
        List<ProductsEntity> productList = repository.findAll();
         
        if(productList.size() > 0) {
            return productList;
        } else {
            return new ArrayList<ProductsEntity>();
        }
    }
  
    public String purchaseProducts(ProductsEntity product)
    {
    	String status = "Item not available";
    	if(product != null) {
    		Optional<ProductsEntity> productEntity = repository.findByName(product.getName());
	        
	        if(productEntity.isPresent())
	        {
	        	ProductsEntity newEntity = productEntity.get();
	        	int existingCount = newEntity.getCount();
	        	if( existingCount > 0) {
	        		int noOfProductNeeded  = product.getCount();
    	        	
	        		int newCount = existingCount - noOfProductNeeded;
	        		
	        		if(newCount >= 0) {
	        			newEntity.setCount(newCount);
    	 
	        			newEntity = repository.save(newEntity);
	        			setPurchasedProducts(newEntity);
	        			status = "purchased";
	        		}
	        	}
	        	
	        } 
    	}
    	
    	return status;
    }
    
   
    public void deleteProductById(Long id) throws RecordNotFoundException
    {
        Optional<ProductsEntity> product = repository.findById(id);
         
        if(product.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }

	public void endPurchase() {
		purchasedProducts.clear();
		
	}

	public List<ProductsEntity> getNewStocks() {
		Optional<List<ProductsEntity>> productEntity = repository.findByCount(0);
		 if(productEntity.isPresent())
	        {
			 return  productEntity.get();
	        }
		return null;
	}
}
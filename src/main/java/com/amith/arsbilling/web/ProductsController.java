package com.amith.arsbilling.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amith.arsbilling.exception.RecordNotFoundException;
import com.amith.arsbilling.model.ProductsEntity;
import com.amith.arsbilling.service.ProductsService;
 
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController
{
    @Autowired
    ProductsService service;
 
    //list all products
    @GetMapping
    public ResponseEntity<List<ProductsEntity>> getAllProducts() {
        List<ProductsEntity> list = service.getAllProducts();
 
        return new ResponseEntity<List<ProductsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    //user submit for billing
    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseProducts(@RequestBody ProductsEntity payload) {
        String status = service.purchaseProducts(payload);
 
        return new ResponseEntity<String>(status, new HttpHeaders(), HttpStatus.OK);
    }
    
    //purchased item
    @GetMapping("/purchaseditems")
    public ResponseEntity<List<ProductsEntity>> getPurchasedItems() {
        List<ProductsEntity> list = service.getPurchasedProducts();
 
        return new ResponseEntity<List<ProductsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    //new stock list
    
    @GetMapping("/newstocks")
    public ResponseEntity<List<ProductsEntity>> getAllNewStocks() {
        List<ProductsEntity> list = service.getNewStocks();
 
        return new ResponseEntity<List<ProductsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    //end purchase
    
    @GetMapping("/endpurchase")
    public ResponseEntity<List<ProductsEntity>> endPurchase() {
    	service.endPurchase();
        List<ProductsEntity> list = service.getPurchasedProducts();
 
        return new ResponseEntity<List<ProductsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteProductById(id);
        return HttpStatus.FORBIDDEN;
    }
    
    
    
 
}
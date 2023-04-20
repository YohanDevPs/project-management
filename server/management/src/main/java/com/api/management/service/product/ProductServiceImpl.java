package com.api.management.service.product;

import com.api.management.dto.ProductDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Product;
import com.api.management.repositorys.ProductRepository;
import com.api.management.repositorys.ReplenishmentRepository;
import com.api.management.repositorys.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.mapper.UtilModelMapper.parseObject;

@Service
public class ProductServiceImpl implements ProductSevice {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReplenishmentRepository replenishmentRepository;
    @Autowired
    private SaleRepository saleRepository;
    
    @Override
    public ProductDTO findById(Long id) {
        var entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        return parseObject(entity, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findProductsBySaleId(Long id) {
        var sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        return parseListObjects(productRepository.findBySaleId(sale.getId()), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findProductsByReplenishmentId(Long id) {
        var replenishment = replenishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Replenishment not found"));

        return parseListObjects(productRepository.findByReplenishmentId(replenishment.getId()), ProductDTO.class);
    }

    @Override
    public ProductDTO createProductToReplenishment(Long replenishmentId, ProductDTO dto) {
        var replenishment = replenishmentRepository.findById(replenishmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Replenishment not found"));

        var product = parseObject(dto, Product.class);
        product.setReplenishment(replenishment);

        return parseObject(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public ProductDTO createProductToSale(Long saleId, ProductDTO dto) {
        var sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Replenishment not found"));

        var product = parseObject(dto, Product.class);
        product.setSale(sale);

        return parseObject(productRepository.save(product), ProductDTO.class);
    }


    @Override
    public ProductDTO update(ProductDTO dto) {
        var product = productRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setUnitType(dto.getUnitType());
        product.setAmount(dto.getAmount());
        product.setUnitValue(dto.getUnitValue());

        return parseObject(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public void delete(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productRepository.delete(product);
    }
}

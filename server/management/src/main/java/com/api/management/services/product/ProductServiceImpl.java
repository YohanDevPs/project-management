package com.api.management.services.product;

import com.api.management.controllers.ProductController;
import com.api.management.dto.ProductDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Product;
import com.api.management.repositorys.ProductRepository;
import com.api.management.repositorys.ReplenishmentRepository;
import com.api.management.repositorys.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.util.constants.ErrorMessageConstants.PRODUCT_NOT_FOUND_MSG;
import static com.api.management.util.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        var productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PRODUCT_NOT_FOUND_MSG, id)));

        var productDTO = parseObject(productEntity, ProductDTO.class);
        productDTO.add(linkTo(methodOn(ProductController.class).findProductById(id)).withSelfRel());
        return productDTO;
    }

    @Override
    public List<ProductDTO> findProductsBySaleId(Long id) {
        var saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        var productsDTOs =  parseListObjects(productRepository.findBySaleId(saleEntity.getId()), ProductDTO.class);

        for (ProductDTO dto: productsDTOs) {
            dto.add(linkTo(methodOn(ProductController.class)
                    .findProductById(dto.getId()))
                    .withSelfRel());
        }

        return productsDTOs;
    }

    @Override
    public List<ProductDTO> findProductsByReplenishmentId(Long id) {
        var replenishmentEntity = replenishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Replenishment not found"));

        var productsDTOs = parseListObjects(
                productRepository.findByReplenishmentId(replenishmentEntity.getId()), ProductDTO.class
        );

        for (ProductDTO dto: productsDTOs) {
            dto.add(linkTo(methodOn(ProductController.class)
                    .findProductById(id))
                    .withSelfRel());
        }
        return productsDTOs;
    }

    @Override
    public ProductDTO createProductToReplenishment(Long replenishmentId, ProductDTO dto) {
        var replenishmentEntity = replenishmentRepository.findById(replenishmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Replenishment not found"));

        var productEntity = parseObject(dto, Product.class);
        productEntity.setReplenishment(replenishmentEntity);

        var productPersistedDTO =  parseObject(productRepository.save(productEntity), ProductDTO.class);
        productPersistedDTO.add(linkTo(methodOn(ProductController.class)
                .findProductById(productPersistedDTO.getId()))
                .withSelfRel());

        return productPersistedDTO;
    }

    @Override
    public ProductDTO createProductToSale(Long saleId, ProductDTO dto) {
        var saleEntity = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Replenishment not found"));

        var productEntity = parseObject(dto, Product.class);
        productEntity.setSale(saleEntity);

        var productPersistedDTO =  parseObject(productRepository.save(productEntity), ProductDTO.class);
        productPersistedDTO.add(linkTo(methodOn(ProductController.class).
                findProductById(productPersistedDTO.getId()))
                .withSelfRel());

        return productPersistedDTO;
    }


    @Override
    public ProductDTO update(ProductDTO dto) {
        var productEntity = productRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productEntity.setName(dto.getName());
        productEntity.setDescription(dto.getDescription());
        productEntity.setUnitType(dto.getUnitType());
        productEntity.setAmount(dto.getAmount());
        productEntity.setUnitValue(dto.getUnitValue());

        var productPersistedDTO = parseObject(productRepository.save(productEntity), ProductDTO.class);
        productPersistedDTO.add(linkTo(methodOn(ProductController.class)
                .findProductById(productPersistedDTO.getId()))
                .withSelfRel());

        return productPersistedDTO;
    }

    @Override
    public void delete(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productRepository.delete(product);
    }
}

package com.example.assign.product;

import com.example.assign.category.CategoryDTO;
import com.example.assign.category.CategoryDTOMapper;
import com.example.assign.category.CategoryService;
import com.example.assign.constant.SystemConstant;
import com.example.assign.exception.ApiRequestException;
import com.example.assign.exception.ResourceNotFoundException;
import com.example.assign.gallery.GalleryDTO;
import com.example.assign.gallery.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final ProductDTOMapper productDTOMapper;

    private final CategoryDTOMapper categoryDTOMapper;

    private final CategoryService categoryService;

    private final GalleryService galleryService;


    @Override
    public void addProduct(UUID uuid, ProductAddRequest request) {
        boolean isValidName = existsByName(request.getName());
        if (isValidName) {
            throw new ApiRequestException("product is already name: " + request.getName());
        }

        CategoryDTO category = categoryService
                .findCategoryByIdAndStatus(uuid, SystemConstant.STATUS_CATEGORY);

        Product product = Product.builder()
                .name(request.getName())
                .category(categoryDTOMapper.toEntity(category))
                .shortDescription(request.getShortDescription())
                .longDescription(request.getLongDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .discount(request.getDiscount())
                .status(SystemConstant.STATUS_PRODUCT)
                .build();

        Product save = productRepo.save(product);
        List<GalleryDTO> galleryDTOS = request.getGalleries().stream()
                .peek(g -> g.setProduct(productDTOMapper.toDTO(save)))
                .toList();
        galleryService.addAllGallery(galleryDTOS);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepo.existsByName(name);
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        return findProductsByStatus(SystemConstant.STATUS_PRODUCT);
    }

    @Override
    public ProductResponse findAllProduct(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(Optional.of(page - 1).orElse(0),
                                            Optional.of(limit).orElse(20));
        return ProductResponse.builder()
                .page(page)
                .limit(limit)
                .totalItem(count())
                .totalPage((int) Math.ceil((double) count() / limit))
                .listResult(
                        productRepo.findAll(pageable)
                                .stream()
                                .map(productDTOMapper::toDTO)
                                .toList())
                .build();
    }

    @Override
    public ProductDTO findOneProductById(UUID id) {
        return productRepo.findById(id)
                .map(productDTOMapper::toDTO)
                .orElseThrow(() -> new ApiRequestException("product find by id: " + id + " not found!.."));
    }

    @Override
    public ProductDTO findProductByIdAndStatus(UUID uuid, Integer status) {
        return productRepo.findProductByIdAndStatus(uuid, status)
                .map(productDTOMapper::toDTO)
                .orElseThrow(() -> new ApiRequestException("product find by id: " + uuid + " not found!.."));
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(UUID id) {
        List<Product> products = productRepo.findAllByCategoryId(id)
                .orElseThrow(() -> new ApiRequestException("Category id: " + id + "not found!.."));
        return products.stream()
                .map(productDTOMapper::toDTO)
                .toList();
    }

    @Override
    public void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status) {
        productRepo.updateQuantityByIdAndStatus(quantity, id, status);
    }

    @Override
    public List<ProductDTO> findProductsByStatus(Integer status) {
        return productRepo.findProductsByStatus(status)
                .stream()
                .map(productDTOMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteProduct(UUID uuid) {
        Product product = productRepo.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("product find by id: " + uuid + " not found!.."));
        product.setStatus(SystemConstant.STATUS_PRODUCT_NO_ACTIVE);
        productRepo.save(product);
    }

    @Override
    public List<ProductStatisticalRevenue> findAllRevenueByCategory() {
        List<Object[]> result = productRepo.findAllRevenueByCategory();
        return productDTOMapper.toStatisticalRevenue(result);
    }

    @Override
    public Integer count() {
        return productRepo.countAll();
    }
}

package com.example.assign.order;

import com.example.assign.constant.SystemConstant;
import com.example.assign.exception.ApiRequestException;
import com.example.assign.exception.ResourceNotFoundException;
import com.example.assign.orderdetail.OrderDetail;
import com.example.assign.orderdetail.OrderDetailService;
import com.example.assign.product.ProductDTO;
import com.example.assign.product.ProductDTOMapper;
import com.example.assign.product.ProductService;
import com.example.assign.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final OrderDTOMapper orderDTOMapper;

    private final ProductDTOMapper productDTOMapper;

    private final ProductService productService;

    private final OrderDetailService orderDetailsService;


    @Override
    public List<OrderDTO> findAllByIdAndStatus(UUID id, Integer status) {
        return orderRepo.findAllByIdAndStatus(id, status)
                .stream()
                .map(orderDTOMapper::toDTO)
                .toList();
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepo.findAll()
                .stream()
                .map(orderDTOMapper::toDTO)
                .toList();
    }

    @Override
    public OrderDTO findOrderById(UUID id) {
        return orderRepo.findById(id)
                .map(orderDTOMapper::toDTO)
                .orElseThrow(() -> new ApiRequestException("Order id: " + id + "not found!.."));
    }

    @Override
    public List<OrderStatisticRevenue> getRevenueByMonth() {
        List<Object[]> revenue = orderRepo.getRevenueByMonth();
        return orderDTOMapper.orderStatisticRevenues(revenue);
    }

    @Override
    public void addOrder(OrderAddRequest request) {
        List<ProductDTO> productDTOS = request.getProducts()
                .stream()
                .peek(p -> {
                    ProductDTO product = getProductDTO(p);
                    if (product.getQuantity() == 0 || product.getQuantity() - p.getQuantity() < 0)
                        throw new ApiRequestException(product.getName() + " invalid quantity");
                }).toList();

        List<OrderDetail> orderDetails = productDTOS.stream()
                .map(p -> {
                    ProductDTO product = getProductDTO(p);
                    productService.updateQuantityByIdAndStatus(
                            product.getQuantity() - p.getQuantity(),
                            product.getId(),
                            SystemConstant.STATUS_PRODUCT
                    );
                    return OrderDetail.builder()
                            .product(productDTOMapper.toEntity(product))
                            .price(p.getPrice())
                            .quantity(p.getQuantity())
                            .totalMoney(p.getPrice() * p.getQuantity())
                            .build();
                }).toList();

        double totalMoney = orderDetails.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        var order = Order.builder()
                .orderDate(new Date(System.currentTimeMillis()))
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .email(request.getEmail())
                .totalMoney(totalMoney)
                .status(SystemConstant.STATUS_ORDER_PENDING)
                .user((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .build();

        var finalOrder = orderRepo.save(order);
        orderDetails.forEach(orderDetail -> orderDetail.setOrder(finalOrder));
        orderDetailsService.addAllOrderDetail(orderDetails);
    }

    @Override
    public void updateOrder(OrderUpdateRequest request, UUID oid) {
        Order order = getOrder(oid);

        if (!request.getFullName().equals(order.getFullName()))
            order.setFullName(request.getFullName());

        if (!order.getEmail().equals(request.getEmail()))
            order.setEmail(request.getEmail());

        if (!order.getPhoneNumber().equals(request.getPhoneNumber()))
            order.setPhoneNumber(request.getPhoneNumber());

        if (!order.getAddress().equals(request.getAddress()))
            order.setAddress(request.getAddress());

        if (!Objects.equals(order.getTotalMoney(), request.getTotalMoney()))
            order.setTotalMoney(request.getTotalMoney());

        orderRepo.save(order);
    }

    private Order getOrder(UUID oid) {
        return orderRepo.findById(oid)
                .orElseThrow(() -> new ResourceNotFoundException("find order by id " + oid + " not found!"));
    }

    @Override
    public void deleteOrder(UUID uuid) {
        Order order = getOrder(uuid);
        order.setStatus(SystemConstant.STATUS_ORDER_DECLINE);
        orderRepo.save(order);
    }

    @Override
    public void approveOrder(UUID uuid) {
        Order order = getOrder(uuid);
        order.setStatus(SystemConstant.STATUS_ORDER_APPROVE);
        orderRepo.save(order);
    }

    @Override
    public void shipOrder(UUID uuid) {
        Order order = getOrder(uuid);
        order.setStatus(SystemConstant.STATUS_ORDER_SHIP);
        orderRepo.save(order);
    }

    @Override
    public List<OrderDTO> findOrdersByStatus(Integer status) {
        return orderRepo.findOrdersByStatus(status)
                .stream()
                .map(orderDTOMapper::toDTO)
                .toList();
    }

    private ProductDTO getProductDTO(ProductDTO p) {
        return productService.findProductByIdAndStatus(p.getId(), SystemConstant.STATUS_PRODUCT);
    }
}

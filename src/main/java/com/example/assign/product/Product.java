package com.example.assign.product;

import com.example.assign.category.Category;
import com.example.assign.gallery.Gallery;
import com.example.assign.orderdetail.OrderDetail;
import com.example.assign.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_product")
public class Product extends BaseEntity {

    @Column(length = 512, unique = true)
    @Nationalized
    private String name;
    @Column
    @Nationalized
    private String shortDescription;
    @Column(length = 512)
    @Nationalized
    private String longDescription;
    @Column
    private Double price;
    @Column
    private Integer quantity;
    @Column
    private Double discount;
    @Column
    private Integer status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Gallery> galleries = new ArrayList<>();
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

}

package com.example.assign.category;

import com.example.assign.product.Product;
import com.example.assign.util.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "_category")
public class Category extends BaseEntity {

    @Column
    @Nationalized
    private String name;
    @Column
    @Nationalized
    private String description;
    @Column
    private String image;
    @Column
    private Integer status;
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}

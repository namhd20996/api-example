package com.example.assign.order;

import com.example.assign.orderdetail.OrderDetail;
import com.example.assign.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column
    @CreatedDate
    private Date orderDate;
    @Column
    @Nationalized
    private String fullName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column(length = 512)
    @Nationalized
    private String address;
    @Column
    @Nationalized
    private String note;
    @Column
    private Integer status;
    @Column
    private Double totalMoney;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();
}

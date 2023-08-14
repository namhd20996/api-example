package com.example.assign.role;

import com.example.assign.util.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_role")
public class Role extends BaseEntity {

    @Column
    private String name;
    @Column
    private String code;
}

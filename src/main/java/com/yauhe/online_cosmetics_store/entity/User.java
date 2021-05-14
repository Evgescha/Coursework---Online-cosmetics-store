package com.yauhe.online_cosmetics_store.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String fio;

    private Date dateBorn;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private List<Order> myOrders = new ArrayList<Order>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
        super();
    }

    public List<String> getRoleListNames() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add(role.getName());
        return roleNames;
    }

    public int getInBacketCount(){
        for(Order order:myOrders){
            if(order.getStatus().getId()==1)
                return order.getOrderItems().size();
        }
        return 0;
    }

}

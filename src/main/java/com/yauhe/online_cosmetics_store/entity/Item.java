package com.yauhe.online_cosmetics_store.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Item extends AbstractEntity {

    private String name;

    private float price;

    @Column(length = 3000)
    private String description;

    private String image;

    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemType type;

    @ManyToOne
    @JoinColumn(name = "item_brand_id")
    private ItemBrand brand;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Override
    public String toString() {
        return name + ", " + price + ", " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Item item = (Item) o;

        if (Float.compare(item.price, price) != 0) return false;
        if (name != null ? !name.equals(item.name) : item.name != null)
            return false;
        if (description != null ? !description.equals(item.description) :
                item.description != null)
            return false;
        if (image != null ? !image.equals(item.image) : item.image != null)
            return false;
        if (type != null ? !type.equals(item.type) : item.type != null)
            return false;
        return brand != null ? brand.equals(item.brand) : item.brand == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price)
                : 0);
        result = 31 * result + (description != null ? description.hashCode()
                : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        return result;
    }
}

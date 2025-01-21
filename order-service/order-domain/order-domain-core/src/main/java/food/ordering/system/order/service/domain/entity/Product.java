package food.ordering.system.order.service.domain.entity;

import food.ordering.system.domain.entity.BaseEntity;
import food.ordering.system.domain.valueobject.Money;
import food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

    private String name;

    private Money price;

    public Product(ProductId id, String name, Money price) {
        super.setId(id);
        this.name = name;
        this.price = price;
    }

    public Product(ProductId productId) {
        setId(productId);
    }

    public Money getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}

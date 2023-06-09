package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.ProductApplication;
import mall.domain.StockDecreased;
import mall.domain.StockIncreased;

@Entity
@Table(name = "Inventory_table")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private Integer stock;

    @PostUpdate
    public void onPostUpdate() {
        // StockDecreased stockDecreased = new StockDecreased(this);
        // stockDecreased.publishAfterCommit();

        // StockIncreased stockIncreased = new StockIncreased(this);
        // stockIncreased.publishAfterCommit();
    }

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = ProductApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    public static void decreaseStock(DeliveryCompleted deliveryCompleted) {
        // /** Example 2:  finding and process
        
        repository().findById(deliveryCompleted.getProductId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() - deliveryCompleted.getQty()); // do something
            repository().save(inventory);

            StockDecreased stockDecreased = new StockDecreased(inventory);
            stockDecreased.publishAfterCommit();

         });
        

    }

    public static void increaseStock(DeliveryReturned deliveryReturned) {
        // /** Example 2:  finding and process
        
        repository().findById(deliveryReturned.getProductId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() + deliveryReturned.getQty()); // do something
            repository().save(inventory);

            StockIncreased stockIncreased = new StockIncreased(inventory);
            stockIncreased.publishAfterCommit();

         });
        

    }
}

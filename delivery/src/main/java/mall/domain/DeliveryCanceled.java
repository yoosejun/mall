package mall.domain;

import java.util.*;
import lombok.*;
import mall.domain.*;
import mall.infra.AbstractEvent;

@Data
@ToString
public class DeliveryCanceled extends AbstractEvent {

    private String userId;
    private Long orderId;
    private String productName;
    private Long productId;
    private Integer qty;
    private String status;
    private String courier;

    public DeliveryCanceled(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryCanceled() {
        super();
    }
}

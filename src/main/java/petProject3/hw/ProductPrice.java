package petProject3.hw;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = false)
public class ProductPrice implements Serializable {

    double productPrice;

    public ProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

}

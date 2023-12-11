package petProject3.hw;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = false)
public class ProductType implements Serializable {

    String productType;

    public ProductType(String productType) {
        this.productType = productType;
    }

}
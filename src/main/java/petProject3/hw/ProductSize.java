package petProject3.hw;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = false)
public class ProductSize implements Serializable {

    String productSize;

    public ProductSize(String productSize) {
        this.productSize = productSize;
    }

}

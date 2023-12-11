package petProject3.hw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Getter
@Setter
@ToString(includeFieldNames = false)
public class Product implements Serializable {

    public static final long serialVersionUID = 1L;

    private String fileName;
    private String productName;
    private ProductType productType;
    private ProductSize productSize;
    private ProductPrice productPrice;

    public Product(String productName, ProductType productType, ProductSize productSize, ProductPrice productPrice) {
        this.productName = productName;
        this.productType = productType;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    public Product(ProductType productType, ProductSize productSize, ProductPrice productPrice) {
        this("fish", productType, productSize, productPrice);
    }

    private void generateFileName(Product product) {
        String uuid = UUID.randomUUID().toString();
        fileName = product.getClass() + " " + uuid;
    }

    public void serialize(Product product) {
        product.generateFileName(product);
        try (OutputStream outputStream = Files.newOutputStream(Path.of(fileName));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(product);
            System.out.println("Object saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Path.of(fileName)))) {
            Product deserializedProduct = (Product) objectInputStream.readObject();
            File file = new File(fileName);
            System.out.println(deserializedProduct);
            System.out.println(file.exists() ? (file.delete() ? "File deleted: " + fileName : "File not deleted: " +
                    fileName) : "File not found: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
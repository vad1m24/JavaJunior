package petProject3.hw;

/**
 * Написать класс с двумя методами:
 * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл.
 * Название файл - class.getName() + "_" + UUID.randomUUID().toString()
 * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект
 * из файла и удаляет этот файл.
 */

public class Main {

    public static void main(String[] args) {

        ProductType pType = new ProductType("smoked");
        ProductSize pSize = new ProductSize("M");
        ProductPrice pPrice = new ProductPrice(15.75);
        Product product = new Product(pType, pSize, pPrice);

        product.serialize(product);
        product.deserialize(product.getFileName());

    }
}
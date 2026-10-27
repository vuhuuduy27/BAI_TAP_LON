package vn.edu.uet.factory;

import vn.edu.uet.model.Art;
import vn.edu.uet.model.Electronics;
import vn.edu.uet.model.Item;
import vn.edu.uet.model.Vehicle;

public class ItemFactory {

    /**
     * Khởi tạo đối tượng Item linh hoạt dựa trên chủng loại (type)
     */
    public static Item createItem(String type, String name, String description, double startingPrice, Object... extraArgs) {
        switch (type.toUpperCase()) {
            case "ELECTRONICS":
                int warrantyMonths = (extraArgs.length > 0) ? (int) extraArgs[0] : 12;
                return new Electronics(name, description, startingPrice, warrantyMonths);

            case "ART":
                String artistName = (extraArgs.length > 0) ? (String) extraArgs[0] : "Khuyết danh";
                int creationYear = (extraArgs.length > 1) ? (int) extraArgs[1] : 2026;
                return new Art(name, description, startingPrice, artistName, creationYear);

            case "VEHICLE":
                String engineType = (extraArgs.length > 0) ? (String) extraArgs[0] : "Động cơ tiêu chuẩn";
                return new Vehicle(name, description, startingPrice, engineType);

            default:
                throw new IllegalArgumentException("Loại sản phẩm không hợp lệ: " + type);
        }
    }
}
package vn.edu.uet.manager;

import vn.edu.uet.model.Auction;
import vn.edu.uet.model.User;

import java.io.*;
import java.util.Map;

public class DataManager {
    private static final String FILE_NAME = "auctions.dat";

    // Lưu toàn bộ trạng thái hệ thống xuống file cứng
    public static void saveData() {
        AuctionManager manager = AuctionManager.getInstance();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(manager.getUserDatabase());
            oos.writeObject(manager.getActiveAuctions());
            System.out.println("Đã lưu dữ liệu hệ thống vào " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi dữ liệu xuống file: " + e.getMessage());
        }
    }

    // Nạp lại dữ liệu từ file cứng lên RAM khi khởi động ứng dụng
    @SuppressWarnings("unchecked")
    public static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Chưa tìm thấy file dữ liệu cũ, khởi tạo hệ thống mới.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Map<String, User> users = (Map<String, User>) ois.readObject();
            Map<String, Auction> auctions = (Map<String, Auction>) ois.readObject();

            AuctionManager.getInstance().setData(users, auctions);
            System.out.println("Đã tải thành công dữ liệu từ file " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi nạp dữ liệu từ file: " + e.getMessage());
        }
    }
}
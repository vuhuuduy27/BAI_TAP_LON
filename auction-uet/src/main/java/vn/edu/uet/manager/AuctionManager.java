package vn.edu.uet.manager;

import vn.edu.uet.exception.AuthenticationException;
import vn.edu.uet.exception.UserAlreadyExistsException;
import vn.edu.uet.model.Auction;
import vn.edu.uet.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Lớp Quản lý trung tâm của hệ thống (Áp dụng Singleton Pattern)
 */
public class AuctionManager {
    
    // 1. Khai báo biến static lưu trữ thể hiện duy nhất của hệ thống
    private static AuctionManager instance;

    // 2. Sử dụng HashMap để tra cứu dữ liệu siêu tốc O(1) thay vì dùng ArrayList O(n)
    private Map<String, User> userDatabase;
    private Map<String, Auction> activeAuctions;

    // 3. Chặn hàm khởi tạo (Private Constructor) để ngăn bên ngoài dùng từ khóa "new"
    private AuctionManager() {
        this.userDatabase = new HashMap<>();
        this.activeAuctions = new HashMap<>();
    }

    // 4. Hàm cung cấp điểm truy cập toàn cầu (Global Access Point)
    // Dùng synchronized để an toàn trong môi trường đa luồng
    public static synchronized AuctionManager getInstance() {
        if (instance == null) {
            instance = new AuctionManager(); // Chỉ khởi tạo 1 lần duy nhất
        }
        return instance;
    }

    // Đăng ký người dùng mới
    public void registerUser(User user) throws UserAlreadyExistsException {
        // Kiểm tra xem tên đăng nhập đã tồn tại chưa bằng containsKey của HashMap
        if (userDatabase.containsKey(user.getUsername())) {
            throw new UserAlreadyExistsException("Tên đăng nhập '" + user.getUsername() + "' đã tồn tại trong hệ thống!");
        }
        userDatabase.put(user.getUsername(), user);
        System.out.println("Đăng ký thành công tài khoản: " + user.getUsername());
    }

    // Đăng nhập hệ thống
    public User login(String username, String password) throws AuthenticationException {
        // Tìm User trong HashMap với độ phức tạp O(1)
        User user = userDatabase.get(username);
        if (user == null) {
            throw new AuthenticationException("Tài khoản không tồn tại!");
        }
        
        // Gọi hàm checkPassword từ lớp User, nếu sai nó sẽ tự ném ngoại lệ
        user.checkPassword(password);
        System.out.println("Đăng nhập thành công: " + username);
        return user;
    }

    // Khởi động một phiên đấu giá mới
    public void startNewAuction(Auction auction) {
        // Sử dụng ID của sản phẩm làm khóa quản lý phiên
        activeAuctions.put(auction.getItem().getId(), auction);
        System.out.println("Đã mở phiên đấu giá cho sản phẩm: " + auction.getItem().getName());
    }

    // Lấy thông tin một phiên đấu giá đang chạy
    public Auction getAuction(String itemId) {
        return activeAuctions.get(itemId);
    }

    // Cung cấp Getter và Setter để DataManager có thể lưu và tải dữ liệu từ File
    public Map<String, User> getUserDatabase() {
        return userDatabase;
    }

    public Map<String, Auction> getActiveAuctions() {
        return activeAuctions;
    }

    public void setData(Map<String, User> users, Map<String, Auction> auctions) {
        this.userDatabase = users;
        this.activeAuctions = auctions;
    }
}
# Kế hoạch BTL LTNC – Tuần 7
## Online Auction System

> Căn cứ: *Hướng dẫn Bài tập lớn – Lập trình nâng cao, Học kỳ II, 2025–2026*, mục **Tuần 7 – Đa luồng, Observer & Phát triển GUI**.
>
> Tuần 7 tập trung vào bài giảng **Lập trình đa luồng và song song**. Các công việc BTL gồm triển khai Observer, logic đấu giá, quản lý trạng thái, xử lý đấu giá đồng thời và tiếp tục tự học JavaFX.

---

## 1. Công việc bắt buộc trong tuần 7

### 1.1. Triển khai Observer Pattern

**Yêu cầu:** Triển khai Observer Pattern để notify khi có bid mới.

Mục tiêu trong hệ thống:

- Khi một Bidder đặt giá thành công, hệ thống phát thông báo về bid mới.
- Các thành phần đang theo dõi phiên đấu giá nhận được thông báo.
- Giao diện có thể sử dụng thông báo này để cập nhật thông tin phiên đấu giá.

Các thành phần cần thiết kế:

- **Subject:** Đối tượng quản lý danh sách Observer và phát thông báo.
- **Observer:** Thành phần đăng ký nhận thông báo.
- **Event:** Thông tin cần gửi khi có bid mới.

### 1.2. Xây dựng logic nghiệp vụ đấu giá

Tài liệu yêu cầu code logic nghiệp vụ gồm:

- Tạo phiên đấu giá.
- Đặt giá.
- Kiểm tra tính hợp lệ của bid.

#### A. Tạo phiên đấu giá

Seller tạo phiên đấu giá cho một sản phẩm. Nhóm cần xác định và kiểm tra:

- Sản phẩm được đưa ra đấu giá.
- Thời gian bắt đầu và kết thúc.
- Giá khởi điểm.
- Trạng thái ban đầu của phiên.

#### B. Đặt giá

Khi Bidder gửi một mức giá mới, hệ thống cần kiểm tra các điều kiện nghiệp vụ đã thống nhất, sau đó mới ghi nhận bid và cập nhật phiên đấu giá.

Các điều kiện cần xem xét:

- Người dùng đã đăng nhập và có quyền Bidder.
- Phiên đấu giá đang cho phép đặt giá.
- Giá đặt đáp ứng quy tắc giá của phiên.
- Bidder không phải Seller của sản phẩm đang được đấu giá.
- Số dư hoặc khoản tiền được giữ chỗ đáp ứng quy tắc thanh toán của nhóm.

### 1.3. Xây dựng logic chuyển trạng thái Auction

Tài liệu yêu cầu triển khai luồng trạng thái:

`OPEN → RUNNING → FINISHED → PAID/CANCELED`

| Trạng thái | Ý nghĩa |
|---|---|
| `OPEN` | Phiên đã được tạo, chưa bắt đầu |
| `RUNNING` | Phiên đang diễn ra |
| `FINISHED` | Phiên đã kết thúc |
| `PAID` | Phiên đã hoàn tất thanh toán |
| `CANCELED` | Phiên bị hủy |

Nhóm cần thống nhất điều kiện chuyển trạng thái và bảo đảm không cho phép chuyển trạng thái tùy tiện.

### 1.4. Xử lý đấu giá đồng thời (Concurrent Bidding)

Tài liệu yêu cầu xử lý concurrent bidding để tránh:

- **Race condition:** Nhiều luồng cùng truy cập và cập nhật dữ liệu dùng chung, khiến kết quả phụ thuộc vào thứ tự thực thi.
- **Lost update:** Một cập nhật bị ghi đè hoặc mất do các thao tác đồng thời.

Ví dụ: Hai Bidder cùng gửi giá mới cho một phiên đấu giá trong khoảng thời gian gần nhau. Hệ thống phải bảo đảm thao tác kiểm tra giá và cập nhật phiên được xử lý an toàn.

### 1.5. Sử dụng cơ chế đồng bộ

Tài liệu yêu cầu sử dụng:

- `synchronized`
- `ReentrantLock`

Nhóm cần xác định các thao tác quan trọng cần được bảo vệ, đặc biệt là thao tác đặt giá và cập nhật dữ liệu chung của một phiên đấu giá.

Phần kiểm tra điều kiện và cập nhật cần được xem xét như một nghiệp vụ thống nhất để tránh luồng khác xen vào giữa các bước.

### 1.6. Tiếp tục tự học JavaFX

Tài liệu yêu cầu tiếp tục học JavaFX và xây dựng các màn hình cơ bản:

1. **Login:** Màn hình đăng nhập.
2. **Danh sách:** Màn hình hiển thị danh sách.

Nhóm có thể triển khai giao diện song song với backend. Cần giữ phần giao diện tách biệt với logic nghiệp vụ để thuận lợi cho việc áp dụng MVC ở tuần 8.

---

## 2. Thứ tự triển khai đề xuất cho nhóm

| Thứ tự | Công việc | Kết quả mong muốn |
|---:|---|---|
| 1 | Rà soát và hoàn thiện `Auction` | Lớp phiên đấu giá có dữ liệu và hành vi cần thiết |
| 2 | Xây dựng logic tạo phiên | Có thể tạo phiên từ sản phẩm hợp lệ |
| 3 | Xây dựng logic đặt giá | Có thể kiểm tra và ghi nhận bid |
| 4 | Hoàn thiện chuyển trạng thái | Kiểm soát được vòng đời phiên đấu giá |
| 5 | Xử lý concurrent bidding | Tránh race condition và lost update |
| 6 | Tích hợp Observer | Phát thông báo khi có bid mới |
| 7 | Xây dựng GUI cơ bản | Có màn hình Login và danh sách |

### Gợi ý phân chia module

Có thể chia công việc song song theo module:

- **Backend – Auction:** Tạo phiên, trạng thái và quy tắc đấu giá.
- **Concurrency:** Nghiên cứu và bảo vệ thao tác đặt giá đồng thời.
- **Observer:** Thiết kế cơ chế đăng ký và nhận thông báo.
- **JavaFX:** Xây dựng màn hình Login và danh sách.
- **Integration/Test:** Tích hợp các phần và kiểm tra luồng hoạt động.

Nhóm cần thống nhất API giữa các module trước khi tích hợp.

---

## 3. Checklist kết quả cuối tuần 7

- [ ] Có thể tạo phiên đấu giá.
- [ ] Có thể đặt giá và kiểm tra tính hợp lệ.
- [ ] Cập nhật được giá hiện tại và người dẫn đầu.
- [ ] Có logic chuyển trạng thái `OPEN → RUNNING → FINISHED → PAID/CANCELED`.
- [ ] Có cơ chế xử lý nhiều Bidder đặt giá đồng thời.
- [ ] Có sử dụng `synchronized` hoặc `ReentrantLock` cho thao tác quan trọng.
- [ ] Observer nhận được thông báo khi có bid mới.
- [ ] Có màn hình Login bằng JavaFX.
- [ ] Có màn hình danh sách bằng JavaFX.
- [ ] Commit các phần việc thường xuyên lên GitHub.

---

## 4. Tài liệu tham khảo

- **Race Conditions:** https://www.youtube.com/watch?v=RMR75VzYoos

Có thể tiếp tục sử dụng tài liệu JavaFX đã được giới thiệu ở tuần 6.

---

## 5. Lưu ý khi thực hiện

1. Vận dụng kiến thức đa luồng và song song vào nghiệp vụ đặt giá.
2. Ưu tiên hoàn thiện luồng nghiệp vụ đặt giá trước khi tích hợp giao diện realtime.
3. Observer dùng để thông báo thay đổi; cơ chế đồng bộ vẫn cần được thiết kế riêng để bảo vệ dữ liệu.
4. Tiếp tục commit thường xuyên và review code của nhau.
5. Mỗi thành viên cần hiểu được phần code mình thực hiện và luồng hoạt động chung của hệ thống.

---

**Mục tiêu trọng tâm:** Cuối tuần 7, nhóm cần có nền tảng logic đấu giá hoạt động được, xử lý được các thao tác đặt giá đồng thời, thông báo được khi có bid mới và bắt đầu có hai màn hình JavaFX cơ bản.

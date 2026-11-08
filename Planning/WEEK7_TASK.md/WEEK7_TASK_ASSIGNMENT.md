# PHÂN CÔNG CÔNG VIỆC CHI TIẾT – TUẦN 7

## I. GIAI ĐOẠN 1: ĐỌC VÀ NẮM BẮT MÃ NGUỒN OOP HIỆN TẠI

**Tất cả 5 thành viên bắt buộc phải hoàn thành giai đoạn này trước khi bắt đầu công việc được giao.**

### Yêu cầu thực hiện

- Truy cập repository và chuyển sang branch của **Nguyễn Hà Huy**, nơi đang chứa phần code OOP hiện tại.
- Đọc và tìm hiểu toàn bộ cấu trúc mã nguồn đã được xây dựng.
- Nắm được vai trò, thuộc tính, phương thức và mối quan hệ giữa các lớp đối tượng.
- Hiểu cách các đối tượng phối hợp với nhau để thực hiện nghiệp vụ đấu giá.

### Các thành phần cần tìm hiểu

| Thành phần | Nội dung cần nắm |
|---|---|
| `User`, `Bidder`, `Seller` | Quản lý người dùng, người đấu giá và người bán. |
| `Item` | Thông tin sản phẩm được đưa lên đấu giá. |
| `Bid` | Thông tin một lượt đặt giá. |
| `Auction` | Quản lý phiên đấu giá, trạng thái và quy tắc đặt giá. |
| `AuctionManager`, `UserManager`, `ItemManager` | Quản lý và lưu trữ các đối tượng. |
| Các lớp `Service` | Xử lý nghiệp vụ và kiểm tra điều kiện trước khi thực hiện hành động. |
| Các `enum` | Quản lý trạng thái và các tập giá trị cố định. |

### Luồng nghiệp vụ cần hiểu

1. Tạo tài khoản và đăng nhập.
2. Người bán tạo sản phẩm.
3. Tạo phiên đấu giá cho sản phẩm.
4. Người dùng tham gia phiên đấu giá.
5. Người tham gia thực hiện đặt giá.
6. Hệ thống kiểm tra điều kiện và cập nhật giá hiện tại.
7. Người tham gia nhận thông báo khi có lượt đặt giá mới.
8. Phiên đấu giá kết thúc và xác định người chiến thắng.

**Lưu ý:** Không chỉ đọc tên lớp và phương thức mà phải hiểu cách các đối tượng phối hợp với nhau. Phần Backend và JavaFX sau này đều phải dựa trên cấu trúc OOP hiện có, tránh tự xây dựng các lớp nghiệp vụ trùng lặp.

---

## II. GIAI ĐOẠN 2: PHÂN CÔNG CÔNG VIỆC CHÍNH

### 1. BACKEND / SERVER

#### Nguyễn Hà Huy – Backend, Concurrency & Observer Pattern

**A. Hoàn thiện Backend**
- Rà soát và hoàn thiện phần nghiệp vụ đấu giá hiện có trong OOP.
- Đảm bảo các chức năng tạo phiên, quản lý trạng thái và quy tắc đấu giá hoạt động chính xác.
- Phối hợp thống nhất cách Backend cung cấp dữ liệu và chức năng cho JavaFX.

**B. Concurrency – Nghiên cứu và triển khai phương án riêng**
- Nghiên cứu và tự triển khai một phương án xử lý đa luồng cho thao tác đặt giá.
- Đảm bảo thao tác đặt giá an toàn khi nhiều người cùng đặt giá trong một phiên đấu giá.
- Xử lý các vấn đề Race Condition, Thread Safety và đồng bộ dữ liệu.
- Kiểm tra tính chính xác của giá hiện tại và người đang dẫn đầu khi có nhiều lượt đặt giá đồng thời.

**C. Observer Pattern**
- Thiết kế và triển khai Observer Pattern để thông báo khi có lượt đặt giá mới.
- Xây dựng cơ chế đăng ký (subscribe) và hủy đăng ký (unsubscribe) theo dõi phiên đấu giá.
- Khi có Bid mới, thông báo đến những người đang theo dõi phiên đấu giá.
- Xử lý các sự kiện cần thông báo:
  - Có người đặt giá mới.
  - Giá hiện tại thay đổi.
  - Người dẫn đầu thay đổi.
  - Phiên đấu giá kết thúc.
- Đảm bảo Observer hoạt động an toàn trong môi trường đa luồng.

**D. Phối hợp**
- Phối hợp với Vũ Hữu Duy để phân tích, so sánh hai phương án Concurrency.
- Cùng đánh giá ưu điểm, nhược điểm và kết quả kiểm thử của từng phương án.
- Thống nhất lựa chọn phương án phù hợp nhất hoặc kết hợp ưu điểm của cả hai.
- Tích hợp phương án Concurrency đã thống nhất với Observer vào Backend chính thức.

**Sản phẩm cần hoàn thành:**
- Phần Backend nghiệp vụ được hoàn thiện.
- Cơ chế Observer hoạt động.
- Một phương án Concurrency được nghiên cứu và triển khai độc lập.
- Kết quả so sánh và phương án Concurrency cuối cùng được thống nhất với Vũ Hữu Duy.

---

#### Vũ Hữu Duy – Concurrency Research & Implementation

**A. Nghiên cứu Concurrency**
- Nghiên cứu các vấn đề phát sinh khi nhiều người cùng đặt giá trong một phiên đấu giá.
- Tìm hiểu Race Condition, Critical Section, Thread Safety và các cơ chế đồng bộ trong Java.
- Tìm hiểu các phương pháp có thể sử dụng để bảo vệ thao tác đặt giá đồng thời.

**B. Triển khai phương án độc lập**
- Tự nghiên cứu và triển khai một phương án xử lý đa luồng (Concurrency) riêng cho thao tác đặt giá.
- Đảm bảo các lượt đặt giá được xử lý an toàn và dữ liệu không bị sai lệch.
- Xây dựng chương trình hoặc tình huống kiểm thử nhiều người đặt giá cùng lúc.
- Kiểm tra tính chính xác của giá hiện tại và người đang dẫn đầu sau khi xử lý đồng thời.
- Kiểm tra trường hợp nhiều lượt đặt giá được gửi gần như cùng một thời điểm.

**C. Phân tích và phối hợp**
- Ghi lại cách triển khai, ưu điểm, hạn chế và kết quả kiểm thử của phương án đã xây dựng.
- Phối hợp với Nguyễn Hà Huy để so sánh hai phương án Concurrency.
- Cùng đánh giá độ an toàn, tính đơn giản, khả năng mở rộng và hiệu quả của từng phương án.
- Thống nhất lựa chọn phương án phù hợp nhất hoặc kết hợp những ưu điểm của cả hai phương án để đưa vào Backend chính thức.

**Sản phẩm cần hoàn thành:**
- Một phương án Concurrency được nghiên cứu và triển khai độc lập.
- Chương trình kiểm thử đa luồng.
- Tài liệu phân tích ưu điểm, nhược điểm và kết quả kiểm thử để so sánh với phương án của Nguyễn Hà Huy.

### Quy trình phối hợp Concurrency giữa hai thành viên

1. Nguyễn Hà Huy và Vũ Hữu Duy cùng tìm hiểu cấu trúc OOP hiện tại, đặc biệt là `Auction`, `Bid` và `AuctionService`.
2. Mỗi người tự nghiên cứu và triển khai một phương án Concurrency riêng.
3. Cả hai cùng xây dựng các tình huống kiểm thử giống nhau để đánh giá hai phương án.
4. Phân tích, so sánh ưu điểm và nhược điểm của từng cách triển khai.
5. Thống nhất lựa chọn một phương án hoặc kết hợp những ưu điểm của cả hai.
6. Đưa phương án cuối cùng vào Backend chính thức và kiểm tra khả năng kết hợp với Observer.

**Nguyên tắc:** Không mặc định phương án của bất kỳ ai được chọn. Hai thành viên cùng nghiên cứu, triển khai, kiểm chứng và đưa ra quyết định dựa trên kết quả thực tế.

---

### 2. JAVAFX / USER INTERFACE

#### Trần Đức Phát – Login & Register UI

**Phụ trách giao diện xác thực người dùng.**

- Xây dựng màn hình Login.
- Xây dựng màn hình Register (đăng ký tài khoản).
- Thiết kế các trường nhập liệu: Username, Password, Email và các thông tin cần thiết.
- Xây dựng chức năng kiểm tra dữ liệu nhập vào.
- Hiển thị thông báo khi đăng nhập thành công hoặc thất bại.
- Kết nối giao diện với chức năng xác thực của Backend (`AuthService` hoặc lớp Service tương ứng).
- Xây dựng chức năng chuyển màn hình sau khi đăng nhập thành công.

**Sản phẩm cần hoàn thành:** Giao diện Login và Register hoạt động, có thể kết nối với Backend để xác thực tài khoản.

---

#### Nguyễn Quang Minh – Auction List UI

**Phụ trách màn hình danh sách các phiên đấu giá.**

- Xây dựng màn hình hiển thị danh sách các phiên đấu giá.
- Thiết kế thông tin hiển thị cho mỗi phiên:
  - Tên sản phẩm.
  - Giá khởi điểm.
  - Giá hiện tại.
  - Thời gian bắt đầu và kết thúc.
  - Trạng thái phiên đấu giá.
- Xây dựng chức năng tìm kiếm phiên đấu giá.
- Xây dựng chức năng lọc phiên đấu giá theo trạng thái (`OPEN`, `RUNNING`, `FINISHED`, ...).
- Xây dựng chức năng làm mới danh sách khi dữ liệu thay đổi.
- Kết nối giao diện với Backend để lấy danh sách phiên đấu giá.
- Xây dựng chức năng chọn một phiên đấu giá để chuyển sang màn hình chi tiết.

**Sản phẩm cần hoàn thành:** Màn hình danh sách phiên đấu giá, chức năng tìm kiếm, lọc và chuyển đến màn hình chi tiết.

---

#### Phạm Đình Quang – Auction Detail & Bidding UI

**Phụ trách màn hình chi tiết phiên đấu giá và thao tác đặt giá.**

- Xây dựng màn hình chi tiết của một phiên đấu giá.
- Hiển thị thông tin sản phẩm và thông tin phiên đấu giá.
- Hiển thị giá hiện tại và người đang dẫn đầu.
- Hiển thị thời gian còn lại của phiên đấu giá.
- Xây dựng khu vực nhập số tiền muốn đặt và nút xác nhận đặt giá.
- Kết nối chức năng đặt giá với Backend.
- Xây dựng khu vực hiển thị lịch sử các lượt đặt giá.
- Tích hợp cơ chế nhận thông báo từ Observer để cập nhật giá và lịch sử đấu giá khi có Bid mới.
- Hiển thị thông báo khi lượt đặt giá thành công hoặc thất bại.

**Sản phẩm cần hoàn thành:** Màn hình chi tiết phiên đấu giá, chức năng đặt giá và cập nhật thông tin khi có lượt đặt giá mới.

---

## III. GIAI ĐOẠN 3: TÍCH HỢP VÀ KIỂM THỬ

Sau khi hoàn thành các phần riêng biệt, cả nhóm tiến hành tích hợp theo các nội dung sau:

| Thành viên | Công việc |
|---|---|
| Nguyễn Hà Huy | Tích hợp Backend, Observer và phương án Concurrency đã thống nhất. |
| Vũ Hữu Duy | Hỗ trợ hoàn thiện và kiểm thử Concurrency, kiểm tra các tình huống đặt giá đồng thời. |
| Trần Đức Phát | Kiểm tra Login, Register và luồng xác thực người dùng. |
| Nguyễn Quang Minh | Kiểm tra danh sách phiên đấu giá, tìm kiếm, lọc và chuyển màn hình. |
| Phạm Đình Quang | Kiểm tra chi tiết phiên đấu giá, đặt giá và cập nhật thông báo. |

### Các luồng hoạt động cần kiểm thử

1. Đăng ký tài khoản → Đăng nhập thành công → Vào màn hình danh sách đấu giá.
2. Hiển thị danh sách phiên đấu giá → Tìm kiếm và lọc phiên.
3. Chọn một phiên đấu giá → Xem thông tin chi tiết.
4. Người dùng đặt giá → Backend kiểm tra điều kiện → Cập nhật giá hiện tại.
5. Observer phát thông báo → Những người đang theo dõi phiên đấu giá nhận được thông báo.
6. Nhiều người đặt giá đồng thời → Backend xử lý an toàn → Dữ liệu không bị sai lệch.
7. Phiên đấu giá kết thúc → Cập nhật trạng thái và hiển thị kết quả.

---

## IV. QUY ĐỊNH PHỐI HỢP VÀ LÀM VIỆC NHÓM

### 1. Quy định về mã nguồn

- Tất cả thành viên phải đọc và hiểu phần OOP hiện tại trước khi phát triển chức năng mới.
- Không tự ý thay đổi cấu trúc các lớp nghiệp vụ hiện có nếu chưa trao đổi với người phụ trách Backend.
- Không tự viết lại các quy tắc nghiệp vụ đấu giá trong JavaFX Controller.
- Các thao tác như đăng nhập, lấy danh sách phiên, đặt giá và nhận thông báo phải thông qua các lớp Backend/Service tương ứng.

### 2. Quy định phối hợp Backend và JavaFX

- Nguyễn Hà Huy chịu trách nhiệm chính trong việc thống nhất cấu trúc và cách sử dụng Backend.
- Backend cần thống nhất với ba thành viên JavaFX về:
  - Các phương thức cần gọi.
  - Dữ liệu đầu vào.
  - Dữ liệu trả về.
  - Cách xử lý kết quả thành công và thất bại.
  - Cách nhận thông báo khi dữ liệu thay đổi.
- Thành viên JavaFX tập trung xử lý giao diện và tương tác người dùng, không tự triển khai lại nghiệp vụ Backend.

### 3. Quy định Git

- Mỗi thành viên phát triển trên branch riêng.
- Thường xuyên cập nhật mã nguồn từ branch chính hoặc branch tích hợp theo thống nhất của nhóm.
- Hoàn thành chức năng thì tạo Pull Request để kiểm tra và tích hợp.
- Không tự ý push trực tiếp lên branch chính khi chưa thống nhất với nhóm.
- Khi cần thay đổi các lớp dùng chung, phải trao đổi với người phụ trách trước khi thực hiện.

---

## V. MỤC TIÊU CUỐI CÙNG

Hoàn thiện một ứng dụng đấu giá bằng Java có các chức năng:

- Đăng ký và đăng nhập tài khoản.
- Hiển thị, tìm kiếm và lọc các phiên đấu giá.
- Xem thông tin chi tiết phiên đấu giá.
- Tham gia và đặt giá trong phiên.
- Hỗ trợ nhiều người đặt giá đồng thời một cách an toàn.
- Tự động thông báo khi có lượt đặt giá mới.
- Quản lý trạng thái và kết quả phiên đấu giá.

**Yêu cầu chung:** Mã nguồn phải có cấu trúc rõ ràng, phân chia trách nhiệm hợp lý giữa các lớp OOP, Backend và JavaFX; các thành phần phải có khả năng tích hợp và hoạt động thống nhất.

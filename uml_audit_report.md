# Báo cáo kiểm tra UML - SmartLogis

## 1. Vấn đề kế thừa

Thiết kế ban đầu cho `RobotXepDo` kế thừa từ `NhanVien`:

```java
class RobotXepDo extends NhanVien
```

Cách này không hợp lý vì robot không phải là nhân viên. Robot không có các thuộc tính như `hoTen`, `maSoBHXH`, bảo hiểm xã hội,... Việc ép robot kế thừa nhân viên làm sai bản chất nghiệp vụ và vi phạm nguyên tắc Liskov Substitution.

### Đề xuất

Tách hành vi chung thành interface:

```java
interface IDonViVanChuyen {
    void diChuyenHang();
}

class Employee implements IDonViVanChuyen { }

class Robot implements IDonViVanChuyen { }
```

Trong UML:

```text
IDonViVanChuyen <|.. Employee
IDonViVanChuyen <|.. Robot
```

`Employee` và `Robot` không kế thừa nhau, chỉ cùng có khả năng vận chuyển hàng.

## 2. Vấn đề composition

Thiết kế ban đầu để `KhoHang` tự tạo và quản lý vòng đời của `DonHang`:

```java
this.danhSachDonHang.add(new DonHang("DH_001"));
```

Cách này giống composition, nhưng không đúng nghiệp vụ. Đơn hàng vẫn có thể tồn tại độc lập với kho hàng. Nếu kho bị xóa hoặc ngừng hoạt động, đơn hàng vẫn cần được lưu lại để xử lý, đối soát hoặc chuyển sang kho khác.

### Đề xuất

Không nên dùng composition giữa `KhoHang` và `DonHang`. Nên dùng association hoặc aggregation:

```text
KhoHang o-- DonHang
```

Hoặc:

```text
KhoHang --> DonHang
```

`DonHang` nên được tạo từ quy trình đặt hàng, sau đó mới được gán vào kho khi cần.

## 3. Kết luận

Thiết kế nên sửa theo hướng:

- `Robot` không kế thừa `NhanVien`.
- `Employee` và `Robot` cùng implement `IDonViVanChuyen`.
- `KhoHang` không tự tạo đơn hàng trong constructor.
- Quan hệ `KhoHang` - `DonHang` nên là association/aggregation, không phải composition.


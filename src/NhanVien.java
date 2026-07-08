import java.util.ArrayList;
import java.util.List;

/**
 * HỆ THỐNG SMARTLOGIS - THIẾT KẾ LỖI TỪ JUNIOR
 */

// LỖI 1: Thiết kế Lớp cha không phù hợp
class NhanVien {
    String hoTen;
    String maSoBHXH; // Robot không thể có thuộc tính này!

    public void diChuyenHang() {
        System.out.println("Nhân viên đang bê hàng...");
    }
}

// LỖI 1: Ép Robot kế thừa Nhân viên (Vi phạm nguyên tắc Liskov Substitution)
class RobotXepDo extends NhanVien {
    int dungLuongPin;

    @Override
    public void diChuyenHang() {
        System.out.println("Robot đang nâng hàng bằng càng cua...");
    }
}

class DonHang {
    String maDon;
    public DonHang(String maDon) { this.maDon = maDon; }
}

// LỖI 2: Quan hệ Composition sai nghiệp vụ
class KhoHang {
    String tenKho;
    // Khởi tạo cứng Đơn Hàng bên trong Kho (Thắt chặt vòng đời - Composition)
    // Nếu Kho bị hủy (Garbage collected), Đơn hàng cũng mất.
    private List<DonHang> danhSachDonHang;

    public KhoHang() {
        this.danhSachDonHang = new ArrayList<>();
        // Tự động sinh đơn hàng khi có kho mới (Sai logic thực tế)
        this.danhSachDonHang.add(new DonHang("DH_001"));
    }
}
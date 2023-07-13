create database QL_CaFe
go
use QL_CaFe
go

CREATE TABLE DoUong (
	MaDoUong INT PRIMARY KEY,
	TenDoUong NVARCHAR(150) NOT NULL,
	Gia FLOAT
)
CREATE TABLE KhoDoUong (
	MaDoUong INT NOT NULL PRIMARY KEY,
	SoLuong INT CONSTRAINT CK_SoLuong CHECK (SoLuong >= 0),
	FOREIGN KEY (MaDoUong) REFERENCES DoUong(MaDoUong)
)

CREATE TABLE NhanVien (
	MaNhanVien INT NOT NULL PRIMARY KEY,
	TENNHANVIEN NVARCHAR(100),
	ChucVu NVARCHAR(50),
	GioiTinh NVARCHAR(3) NOT NULL,
	DienThoai INT,
	Luong INT
)
CREATE TABLE TaiKhoan (
    MaTK INT NOT NULL PRIMARY KEY,
    MaNhanVien INT NOT NULL,
    TenTK NVARCHAR(50) NOT NULL,
    MatKhau NVARCHAR(50) NOT NULL,
    -- Thêm các cột thông tin người dùng khác nếu cần
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
)

CREATE TABLE KhachHang (
	MaKhachHang INT PRIMARY KEY,
	TenKH NVARCHAR(100),
	DiaChi NVARCHAR(150),
	DienThoai INT
)

CREATE TABLE HoaDon (
	MaHD INT PRIMARY KEY,
	MaNhanVien INT NOT NULL,
	MaKhachHang INT NOT NULL,
	NgayThanhToan DATETIME,
	FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
	FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
)

CREATE TABLE ChiTiet_HoaDon (
	MaHD INT NOT NULL,
	MaDoUong INT NOT NULL,
	TenDoUong NVARCHAR(30),
	Gia FLOAT,
	SoLuong INT,
	ThanhTien INT,
	PRIMARY KEY (MaHD, MaDoUong),
	FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
	FOREIGN KEY (MaDoUong) REFERENCES DoUong(MaDoUong)
)
insert into ChiTiet_HoaDon values
(1,2,N'Bạc xỉu',20000,2,40000),
(2,1,N'Cappuchino',10000,3,30000),
(3,4,N'Cà phê sữa',15000,2,30000)



INSERT into NHANVIEN (MaNhanVien,TENNHANVIEN, CHUCVU, GIOITINH, DIENTHOAI, Luong)
VALUES
(1, N'Đỗ Nguyễn Công Thành',N'Giám Đốc Chi Nhánh 03',N'Nam','0123456789',55000000),
(2, N'Lê Hoàng Huy Đào', N'Nhân Viên Phục Vụ', N'Nam', '01234567', 3000000),
(3, N'Hồ Phạm Trường An', N'Nhân Viên Pha Chế', N'Nam', '012345678', 2500000),
(4, N'Nguyễn Thông Thái', N'Nhân Viên Kế Toán', N'Nữ', '012345679', 4000000)
go

Insert into KHACHHANG (MaKhachHang,TenKH, DiaChi, DienThoai)
Values
(1, N'Phạm Anh Tuấn', N'Phường 9, Q.Tân Bình', '0123456789'),
(2, N'Nguyễn Trọng Đại', N'Phường Tây Thạnh, Q.Tân Bình', '0123456789'),
(3, N'Lê Quốc Khánh', N'Phường Tân Quy, Quận 7', '0123456789')
GO

insert into DoUong(MaDoUong,TenDoUong, Gia)
values
(1,N'Cappuchino', 10000),
(2,N'Bạc xỉu', 20000),
(3,N'Cà phê đen', 30000),
(4,N'Cà phê sữa', 15000),
(5,N'Latte nóng', 60000),
(6,N'Cam vắt', 10000),
(7,N'Sinh tố lắc', 35000),
(8,N'Cà phê xay và Orio', 45000),
(9,N'Cà phê xay Chocolate', 50000)
go

insert into KhoDoUong (MaDoUong, SoLuong)
values
(1,100),
(2,155),
(3,110),
(4,200),
(5,50),
(6,77),
(7,20),
(8,95),
(9,35)
go

-- Lấy MaNhanVien từ bảng NhânVien
DECLARE @MaNhanVien INT
SET @MaNhanVien = (SELECT TOP 1 MaNhanVien FROM NhanVien)
-- Thêm một hóa đơn mới vào bảng HoaDon với MaNhanVien đã chọn
INSERT INTO HoaDon (MaHD, MaNhanVien, MaKhachHang, NgayThanhToan)
VALUES
(1,@MaNhanVien , 1, GETDATE()),
(2, @MaNhanVien , 2, GETDATE()),
(3, @MaNhanVien , 1, GETDATE())


--insert into ChiTiet_HoaDon(MaHD, MaDoUong, Gia, SoLuong)
--values
--(1, 6, 10000, 2),
--(2, 4, 15000, 1),
--(2, 3, 30000, 1),
--(3, 2, 20000, 1),
--(3, 5, 60000, 2),
--(3, 7, 35000, 3)
--go

-- Dữ liệu cho bảng TaiKhoan
INSERT INTO TaiKhoan (MaNhanVien, MaTK, TenTK, MatKhau) VALUES 
(1,1, 'thai', '123'),
(2,2, 'dao', '123'),
(3,3, 'an','123'),
(4,4, 'thanh', '123')
GO

SELECT * FROM KhachHang
SELECT * FROM NHANVIEN
select * from HoaDon
select * from DoUong
select * from ChiTiet_HoaDon
select * from TaiKhoan
select * from KhoDoUong
go

--CREATE PROCEDURE dbo.Insert_ChiTiet_HoaDon
--    @MaDoUong INT,
--    @TenDoUong NVARCHAR(50),
--    @Gia FLOAT,
--    @SoLuong INT,
--    @ThanhTien FLOAT,
--    @MaHD INT OUTPUT
--AS
--BEGIN
--    SET @MaHD = (SELECT ISNULL(MIN(MaHD), 0) FROM HoaDon)
--    WHILE EXISTS (SELECT 1 FROM ChiTiet_HoaDon WHERE MaHD = @MaHD)
--    BEGIN
--        SET @MaHD = @MaHD
--    END
--    INSERT INTO ChiTiet_HoaDon (MaHD, MaDoUong, TenDoUong, Gia, SoLuong, ThanhTien) 
--    VALUES (@MaHD, @MaDoUong, @TenDoUong, @Gia, @SoLuong, @ThanhTien)
--END
--go

-- Procedure Thống Kê:


--Trigger kiểm tra số lượng
CREATE TRIGGER trg_KiemTraSoLuongKho
ON ChiTiet_HoaDon
FOR INSERT
AS
BEGIN
    DECLARE @MaHD INT, @MaDoUong INT, @SoLuong INT

    -- Lấy các giá trị từ bảng inserted
    SELECT @MaHD = MaHD, @MaDoUong = MaDoUong, @SoLuong = SoLuong
    FROM inserted

    -- Kiểm tra số lượng trong kho
    IF EXISTS (
        SELECT 1
        FROM KhoDoUong
        WHERE MaDoUong = @MaDoUong AND SoLuong = 0
    )
    BEGIN
        RAISERROR ('Sản phẩm trong kho đã hết hàng!', 16, 1)
        ROLLBACK
        RETURN
    END

    -- Cập nhật số lượng trong kho sau khi thanh toán
    UPDATE KhoDoUong
    SET SoLuong = SoLuong - @SoLuong
    WHERE MaDoUong = @MaDoUong
END

----top 5 nhân viên bán hàng trong hoá đơn
CREATE PROCEDURE GetTop5NhanVienBanHang
AS
BEGIN
    SELECT TOP 5 nv.MaNhanVien, nv.TENNHANVIEN, COUNT(hd.MaHD) AS SoHoaDonBan
    FROM NhanVien nv
    INNER JOIN HoaDon hd ON nv.MaNhanVien = hd.MaNhanVien
    GROUP BY nv.MaNhanVien, nv.TENNHANVIEN
    ORDER BY SoHoaDonBan DESC
END

EXEC GetTop5NhanVienBanHang

----top 5 sản phẩm bán chạy

CREATE PROCEDURE GetTop5SanPhamBanChay
AS
BEGIN
    SELECT TOP 5 TenDoUong, SUM(SoLuong) AS TongSoLuongBan
    FROM ChiTiet_HoaDon
    GROUP BY TenDoUong
    ORDER BY TongSoLuongBan DESC
END

EXEC GetTop5SanPhamBanChay

select * from douong
delete DoUong where MaDoUong = 10
select * from ChiTiet_HoaDon
delete ChiTiet_HoaDon where MaHD = 2
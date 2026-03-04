package com.sapporo.ramenbooking.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    
    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(min = 2, max = 100, message = "Tên phải từ 2-100 ký tự")
    private String customerName;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại không hợp lệ (0909xxxxxx)")
    private String customerPhone;
    
    @Email(message = "Email không hợp lệ")
    private String customerEmail;
    
    @NotNull(message = "Ngày đặt không được để trống")
    @Future(message = "Ngày đặt phải là ngày trong tương lai")
    private LocalDate bookingDate;
    
    @NotBlank(message = "Khung giờ không được để trống")
    private String timeSlotId;
    
    @NotNull(message = "Số người không được để trống")
    @Min(value = 1, message = "Số người tối thiểu là 1")
    @Max(value = 8, message = "Số người tối đa là 8")
    private Integer numberOfGuests;
}
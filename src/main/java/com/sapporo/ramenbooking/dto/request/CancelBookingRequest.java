package com.sapporo.ramenbooking.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelBookingRequest {

    @NotBlank(message = "Mã đặt chỗ không được để trống")
    private String bookingId;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại không hợp lệ")
    private String customerPhone;

    private String cancelReason;
}
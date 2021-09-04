package uz.pdp.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    private String name;
    private String phoneNumber;
    private Integer departmentId;
    private Integer addressId;
}

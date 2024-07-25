package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllQuantityInEquipRequest implements Serializable {
    private String stationId;
    private String equipId;

    public boolean IsEmptyHttpGetAllQuantityInEquipRequest() {
        return StringUtils.isEmpty(this.stationId) || StringUtils.isEmpty(this.equipId);
    }
}

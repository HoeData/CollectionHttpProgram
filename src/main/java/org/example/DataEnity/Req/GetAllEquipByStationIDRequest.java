package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllEquipByStationIDRequest implements Serializable {
    private String StationID;

    public boolean IsEmptyHttpGetAllEquipByStationID() {
        return StringUtils.isEmpty(this.StationID);
    }
}

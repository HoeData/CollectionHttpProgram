package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelEquipFromDaqStationRequest implements Serializable {
    private String szDAQSUUID;
    private String szDAQEquipID;

    public boolean IsEmptyHttpDelEquipFromDaqStationRequest() {
        return StringUtils.isEmpty(this.szDAQSUUID) || StringUtils.isEmpty(this.szDAQEquipID);
    }

}

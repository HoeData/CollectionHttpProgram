package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.DataEnity.Req.Entity.AddQuantityToEquips;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddQuantityToEquipRequest implements Serializable {
    private List<AddQuantityToEquips> addQuantityToEquips;

    public boolean IsEmptyHttpDelEquipFromDaqStationRequest() {
        return this.addQuantityToEquips.stream().filter((res) -> {
            return StringUtils.isEmpty(res.getVarMiniInfo().getSzDAQSUUID())  || StringUtils.isEmpty(res.getVarMiniInfo().getSzDAQQName()) || StringUtils.isEmpty(res.getVarMiniInfo().getSzDAQEID());
        }).count() > 0L;
    }
}

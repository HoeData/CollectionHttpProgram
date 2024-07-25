package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.DataEnity.Req.Entity.EditQuantityToEquips;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditQuantityToEquipRequest implements Serializable {
    private List<EditQuantityToEquips> editQuantityToEquipRequests;

    public boolean IsEmptyHttpEditQuantityToEquipRequest() {
        return this.editQuantityToEquipRequests.stream().filter((res) -> {
            return StringUtils.isEmpty(res.getVarMiniInfo().getSzDAQQName()) || StringUtils.isEmpty(res.getVarMiniInfo().getSzDAQQID()) || StringUtils.isEmpty(res.getVarMiniInfo().getSzDAQQName()) || StringUtils.isEmpty(res.getVarMiniInfo().getSzDAQEID());
        }).count() > 0L;
    }
}

package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.DataEnity.Req.Entity.DeleteQuantityFromEquips;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteQuantityFromEquipRequest implements Serializable {
    List<DeleteQuantityFromEquips> deleteQuantityFromEquipRequests;

    public boolean IsEmptyHttpDeleteQuantityFromEquipRequest() {
        return this.deleteQuantityFromEquipRequests.stream().filter((res) -> {
            return StringUtils.isEmpty(res.getSzDAQEquipID()) || StringUtils.isEmpty(res.getSzDAQSUUID()) || StringUtils.isEmpty(res.getSzDAQQID());
        }).count() > 0L;
    }
}

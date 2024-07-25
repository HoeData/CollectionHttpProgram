package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.DataEnity.Var.VarMiniInfoAddEquipToDaqStation;
import org.example.DataEnity.Var.VarParam;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEquipToDaqStationRequest implements Serializable {
    private VarMiniInfoAddEquipToDaqStation varMiniInfo;
    private VarParam varParam;

    public boolean IsEmptyHttpAddEquipToDaqStationRequest() {
        return StringUtils.isEmpty(this.varMiniInfo.getSzDAQEName()) || StringUtils.isEmpty(this.varMiniInfo.getSzDAQSUUID()) || StringUtils.isEmpty(this.varParam.getVarSPPoParam().getSzLibFileName()) || StringUtils.isEmpty(this.varParam.getVarSCommPluginParam().getSzLibFileName()) || this.varParam.getVarSPPoParam().getSeqAllParam().stream().filter((res) -> {
            return StringUtils.isEmpty(res.getSzParamName()) || StringUtils.isEmpty(res.getSzParamValue());
        }).count() > 0L || this.varParam.getVarSCommPluginParam().getSeqAllParam().stream().filter((res) -> {
            return StringUtils.isEmpty(res.getSzParamName()) || StringUtils.isEmpty(res.getSzParamValue());
        }).count() > 0L || this.varParam.getMapDPPoAndCommPlugin().stream().filter((res) -> {
            return StringUtils.isEmpty(res.getSzDPPOPluginObjID()) || res.getSeqDCommPluginObjID() != null && !res.getSeqDCommPluginObjID().isEmpty();
        }).count() > 0L;
    }

}
